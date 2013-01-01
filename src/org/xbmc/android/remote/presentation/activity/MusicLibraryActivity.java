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

import org.xbmc.android.remote.R;
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
import org.xbmc.android.widget.slidingtabs.SlidingTabHost;
import org.xbmc.api.business.IEventClientManager;
import org.xbmc.eventclient.ButtonCodes;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MusicLibraryActivity extends Activity {
	
	protected ActionBar actionBar;
	
	private static final int MENU_NOW_PLAYING = 301;

	private static final int MENU_REMOTE = 303;
	
    private static class TabListener implements ActionBar.TabListener {
    	boolean isAdded = false;
        private Fragment mFragment;
        private final String mTag;

        public TabListener(String tag, Fragment fragment) {
            mFragment = fragment;
            mTag = tag;
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
        	Log.v(mTag, "onTabSelected " +  isAdded);
            if (!isAdded) {
                ft.add(android.R.id.content, mFragment, mTag);
                isAdded = true;
            } else {
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        	Log.v(mTag, "onTabUnselected");
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
        	Log.v(mTag, "onTabReselected");
            // User selected the already selected tab. Usually do nothing.
        }
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MusicAlbumFragment albumFragment = new MusicAlbumFragment();
		MusicArtistFragment artistFragment = new MusicArtistFragment();
		MusicGenreFragment genreFragment = new MusicGenreFragment();
		MusicCompilationFragment compilationFragment = new MusicCompilationFragment();
		MusicFilesFragment filesFragment = new MusicFilesFragment();

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		
		actionBar.addTab(actionBar.newTab().setText("Albums").setTabListener(new TabListener("Albums", albumFragment)));
		actionBar.addTab(actionBar.newTab().setText("Artists").setTabListener(new TabListener("Artists", artistFragment)));
		actionBar.addTab(actionBar.newTab().setText("Genres").setTabListener(new TabListener("Genres", genreFragment)));
		actionBar.addTab(actionBar.newTab().setText("Compilations").setTabListener(new TabListener("Compilations", compilationFragment)));
		actionBar.addTab(actionBar.newTab().setText("Files").setTabListener(new TabListener("Files", filesFragment)));
	

		/*// assign the gui logic to each tab
		mHandler = new Handler();
		mAlbumController = new AlbumListController();
		mAlbumController.findMessageView(findViewById(R.id.albumlist_outer_layout));
//		mAlbumController.setGrid((GridView)findViewById(R.id.albumlist_grid));

		mFileController = new FileListController();
		mFileController.findMessageView(findViewById(R.id.filelist_outer_layout));
		
		mArtistController = new ArtistListController();
		mArtistController.findMessageView(findViewById(R.id.artists_outer_layout));

		mGenreController = new MusicGenreListController();
		mGenreController.findMessageView(findViewById(R.id.genres_outer_layout));

		mCompilationsController = new AlbumListController();
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
		menu.add(0, MENU_REMOTE, 0, "Remote control").setIcon(R.drawable.menu_remote);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
		case MENU_NOW_PLAYING:
			startActivity(new Intent(this,  NowPlayingActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
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
