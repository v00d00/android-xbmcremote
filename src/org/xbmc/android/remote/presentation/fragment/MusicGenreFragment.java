package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.MusicGenreListController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MusicGenreFragment extends AbsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setController(new MusicGenreListController());
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.albumgrid, container, false);
	}
}
