/*
 *      Copyright (C) 2005-2009 Team XBMC
 *      http://xbmc.org
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XBMC Remote; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

package org.xbmc.android.remote.presentation.controller;

import java.util.ArrayList;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.business.ManagerFactory;
import org.xbmc.android.remote.presentation.activity.ListActivity;
import org.xbmc.android.remote.presentation.widget.OneLabelItemView;
import org.xbmc.android.util.ImportUtilities;
import org.xbmc.api.business.DataResponse;
import org.xbmc.api.business.IVideoManager;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Artist;
import org.xbmc.api.type.ThumbSize;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

public class ActorListController extends ListController implements IController {
	
	private static final int mThumbSize = ThumbSize.SMALL;
	public static final int TYPE_ALL = 1;
	public static final int TYPE_MOVIE = 2;
	public static final int TYPE_TVSHOW = 3;
	public static final int TYPE_EPISODE = 4;
	
	private boolean mLoadCovers = false;
	private final int mType;
	
	private IVideoManager mVideoManager;
	
	public ActorListController(int type) {
		mType = type;
	}
	
	public void onCreate(Activity activity, Handler handler, AbsListView list) {
		
		mVideoManager = ManagerFactory.getVideoManager(this);
		
		if (!isCreated()) {
			super.onCreate(activity, handler, list);
			final String sdError = ImportUtilities.assertSdCard();
			mLoadCovers = sdError == null;
			if (!mLoadCovers) {
				Toast toast = Toast.makeText(activity, sdError + " Displaying place holders only.", Toast.LENGTH_LONG);
				toast.show();
			}
			
			mFallbackBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.person_black_small);
			setupIdleListener();
			
			final String title = mType == TYPE_MOVIE ? "Movie " : mType == TYPE_TVSHOW ? "TV " : "" + "Actors";
			DataResponse<ArrayList<Actor>> response = new DataResponse<ArrayList<Actor>>() {
				public void run() {
					if (value.size() > 0) {
						setTitle(title + " (" + value.size() + ")");
						((AdapterView<ListAdapter>) mList).setAdapter(new ActorAdapter(mActivity, value));
					} else {
						setTitle(title);
						setNoDataMessage("No actors found.", R.drawable.icon_artist_dark);
					}
				}
			};
			
			mList.setOnKeyListener(new ListControllerOnKeyListener<Artist>());			
			
			showOnLoading();
			setTitle(title + "...");			
			switch (mType) {
				case TYPE_ALL:
					mVideoManager.getActors(response, mActivity.getApplicationContext());
					break;
				case TYPE_MOVIE:
					mVideoManager.getMovieActors(response, mActivity.getApplicationContext());
					break;
				case TYPE_TVSHOW:
					mVideoManager.getTvShowActors(response, mActivity.getApplicationContext());
					break;
				case TYPE_EPISODE:
					break;
			}
			
			mList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					if(isLoading()) return;
					Intent nextActivity;
					final Actor actor = (Actor)mList.getAdapter().getItem(((OneLabelItemView)view).getPosition());
					nextActivity = new Intent(view.getContext(), ListActivity.class);
					if(mType == TYPE_TVSHOW)
						nextActivity.putExtra(ListController.EXTRA_LIST_CONTROLLER, new TvShowListController());
					else
						nextActivity.putExtra(ListController.EXTRA_LIST_CONTROLLER, new MovieListController());
					nextActivity.putExtra(ListController.EXTRA_ACTOR, actor);
					mActivity.startActivity(nextActivity);
				}
			});
		}
	}
		
	private class ActorAdapter extends ArrayAdapter<Actor> {
		ActorAdapter(Activity activity, ArrayList<Actor> items) {
			super(activity, 0, items);
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			final OneLabelItemView view;
			if (convertView == null) {
				view = new OneLabelItemView(mActivity, mVideoManager, parent.getWidth(), mFallbackBitmap, mList.getSelector(), true);
			} else {
				view = (OneLabelItemView)convertView;
			}
			final Actor actor = this.getItem(position);
			view.reset();
			view.setPosition(position);
			view.setTitle(actor.name);
			
			if (mLoadCovers) {
				if(mVideoManager.coverLoaded(actor, mThumbSize)){
					view.setCover(mVideoManager.getCoverSync(actor, mThumbSize));
				}else{
					view.setCover(null);
					view.getResponse().load(actor, !mPostScrollLoader.isListIdle());
				}
			}

			return view;
		}
	}
	private static final long serialVersionUID = 4360738733222799619L;

	@Override
	public void onContextItemSelected(MenuItem item) {
		// No context menus in here.
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// No context menus in here.
	}

	public void onActivityPause() {
		if (mVideoManager != null) {
			mVideoManager.setController(null);
		}
		super.onActivityPause();
	}

	public void onActivityResume(Activity activity) {
		super.onActivityResume(activity);
		if (mVideoManager != null) {
			mVideoManager.setController(this);
		}
	}
}
