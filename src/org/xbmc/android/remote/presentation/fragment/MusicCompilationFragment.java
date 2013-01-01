package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.presentation.controller.AlbumListController;

import android.os.Bundle;

public class MusicCompilationFragment extends AbsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setController(new AlbumListController(true));
		super.onCreate(savedInstanceState);
	}
}
