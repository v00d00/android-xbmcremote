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

package org.xbmc.android.remote.presentation.activity;

import java.util.ArrayList;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.business.Command;
import org.xbmc.android.remote.business.ManagerFactory;
import org.xbmc.android.remote.presentation.controller.AlbumListController;
import org.xbmc.android.remote.presentation.controller.ArtistListController;
import org.xbmc.android.remote.presentation.controller.FileListController;
import org.xbmc.android.remote.presentation.controller.MusicGenreListController;
import org.xbmc.android.remote.presentation.controller.RemoteController;
import org.xbmc.android.remote.presentation.fragment.MusicAlbumFragment;
import org.xbmc.android.remote.presentation.fragment.MusicArtistFragment;
import org.xbmc.android.remote.presentation.fragment.MusicCompilationFragment;
import org.xbmc.android.remote.presentation.fragment.MusicFilesFragment;
import org.xbmc.android.remote.presentation.fragment.MusicGenreFragment;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.presentation.INotifiableController;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MusicLibraryActivity extends FragmentActivity implements INotifiableController {

	protected ActionBar actionBar;

	private static final int MENU_NOW_PLAYING = 301;
	private static final int MENU_UPDATE_LIBRARY = 302;
	private static final int MENU_REMOTE = 303;

	public static class MusicFragmentAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> list = new ArrayList<Fragment>();

        public MusicFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
        	Log.d("Position: ", String.valueOf(position));
            return list.get(position);
        }

        void addFragment(Fragment fragment) {
        	list.add(fragment);
        }
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.musiclibrary);

		MusicFragmentAdapter adapter = new MusicFragmentAdapter(getSupportFragmentManager());

	    adapter.addFragment(new MusicAlbumFragment());
		adapter.addFragment(new MusicArtistFragment());
		adapter.addFragment(new MusicGenreFragment());
		adapter.addFragment(new MusicCompilationFragment());
		adapter.addFragment(new MusicFilesFragment());

		ViewPager pager = (ViewPager) findViewById(R.id.musicpager);
	    pager.setAdapter(adapter);
	    pager.setCurrentItem(0);

		/*actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);

		actionBar.addTab(actionBar.newTab().setText("Albums").setTabListener(new TabListener("Albums", albumFragment)));
		actionBar.addTab(actionBar.newTab().setText("Artists").setTabListener(new TabListener("Artists", artistFragment)));
		actionBar.addTab(actionBar.newTab().setText("Genres").setTabListener(new TabListener("Genres", genreFragment)));
		actionBar.addTab(actionBar.newTab().setText("Compilations").setTabListener(new TabListener("Compilations", compilationFragment)));
		actionBar.addTab(actionBar.newTab().setText("Files").setTabListener(new TabListener("Files", filesFragment)));*/


		/*// assign the gui logic to each tab
		mHandler = new Handler();
		mAlbumController = new AlbumListController();
		mAlbumController.findTitleView(findViewById(R.id.albumlist_outer_layout));
		mAlbumController.findMessageView(findViewById(R.id.albumlist_outer_layout));
//		mAlbumController.setGrid((GridView)findViewById(R.id.albumlist_grid));

		mFileController = new FileListController();
		mFileController.findTitleView(findViewById(R.id.filelist_outer_layout));
		mFileController.findMessageView(findViewById(R.id.filelist_outer_layout));

		mArtistController = new ArtistListController();
		mArtistController.findTitleView(findViewById(R.id.artists_outer_layout));
		mArtistController.findMessageView(findViewById(R.id.artists_outer_layout));

		mGenreController = new MusicGenreListController();
		mGenreController.findTitleView(findViewById(R.id.genres_outer_layout));
		mGenreController.findMessageView(findViewById(R.id.genres_outer_layout));

		mCompilationsController = new AlbumListController();
		mCompilationsController.findTitleView(findViewById(R.id.compilations_outer_layout));
		mCompilationsController.findMessageView(findViewById(R.id.compilations_outer_layout));
		mCompilationsController.setCompilationsOnly(true);*/

	}

	/*private void initTab(String tabId) {
		if (tabId.equals("tab_albums")) {
			mAlbumController.onCreate(MusicLibraryActivity.this, mHandler, (ListView)findViewById(R.id.albumlist_list));
		}
		if (tabId.equals("tab_files")) {
			mFileController.onCreate(MusicLibraryActivity.this, mHandler, (ListView)findViewById(R.id.filelist_list));
		}
		if (tabId.equals("tab_artists")) {
			mArtistController.onCreate(MusicLibraryActivity.this, mHandler, (ListView)findViewById(R.id.artists_list));
		}
		if (tabId.equals("tab_genres")) {
			mGenreController.onCreate(MusicLibraryActivity.this, mHandler, (ListView)findViewById(R.id.genres_list));
		}
		if (tabId.equals("tab_compilations")) {
			mCompilationsController.onCreate(MusicLibraryActivity.this, mHandler, (ListView)findViewById(R.id.compilations_list));
		}
	}

	*/
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		menu.add(0, MENU_NOW_PLAYING, 0, "Now playing").setIcon(R.drawable.menu_nowplaying);
		switch (mTabHost.getCurrentTab()) {
			case 0:
				mAlbumController.onCreateOptionsMenu(menu);
				break;
			case 1:
				mArtistController.onCreateOptionsMenu(menu);
				break;
			case 2:
				mGenreController.onCreateOptionsMenu(menu);
				break;
			case 3:
				mCompilationsController.onCreateOptionsMenu(menu);
				break;
			case 4:
				mFileController.onCreateOptionsMenu(menu);
				break;
		}
		menu.add(0, MENU_UPDATE_LIBRARY, 0, "Update Library").setIcon(R.drawable.menu_refresh);
		menu.add(0, MENU_REMOTE, 0, "Remote control").setIcon(R.drawable.menu_remote);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// first, process individual menu events
		switch (mTabHost.getCurrentTab()) {
		case 0:
			mAlbumController.onOptionsItemSelected(item);
			break;
		case 1:
			mArtistController.onOptionsItemSelected(item);
			break;
		case 2:
			mGenreController.onOptionsItemSelected(item);
			break;
		case 3:
			mCompilationsController.onOptionsItemSelected(item);
			break;
		case 4:
			mFileController.onOptionsItemSelected(item);
			break;
		}
		
		// then the generic ones.
		switch (item.getItemId()) {
		case MENU_REMOTE:
			final Intent intent;
			if (getSharedPreferences("global", Context.MODE_PRIVATE).getInt(RemoteController.LAST_REMOTE_PREFNAME, -1) == RemoteController.LAST_REMOTE_GESTURE) {
				intent = new Intent(this, GestureRemoteActivity.class);
			} else {
				intent = new Intent(this, RemoteActivity.class);
			}
			intent.addFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
			return true;
		case MENU_UPDATE_LIBRARY:
			mAlbumController.updateLibrary();
			return true;
		case MENU_NOW_PLAYING:
			startActivity(new Intent(this,  NowPlayingActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onWrongConnectionState(int state, INotifiableManager manager,
			Command<?> source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void runOnUI(Runnable action) {
		super.runOnUiThread(action);
	}

	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		IEventClientManager client = ManagerFactory.getEventClientManager(mAlbumController);
		switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_UP:
				client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_PLUS, false, true, true, (short)0, (byte)0);
				return true;
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				client.sendButton("R1", ButtonCodes.REMOTE_VOLUME_MINUS, false, true, true, (short)0, (byte)0);
				return true;
		}
		client.setController(null);
		return super.onKeyDown(keyCode, event);
	}*/
}
