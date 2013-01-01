package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.AlbumListController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MusicAlbumFragment extends AbsListFragment {
	
	private static final int MENU_UPDATE_LIBRARY = 302;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setController(new AlbumListController());
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.albumgrid, container, false);
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		menu.add(0, MENU_UPDATE_LIBRARY, 0, "Update Library").setIcon(R.drawable.menu_refresh);
		super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_UPDATE_LIBRARY:
			((AlbumListController)controller).updateLibrary();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
