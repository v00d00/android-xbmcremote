package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.presentation.controller.MusicGenreListController;

import android.os.Bundle;

public class MusicGenreFragment extends AbsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setController(new MusicGenreListController());
		super.onCreate(savedInstanceState);
	}
}
