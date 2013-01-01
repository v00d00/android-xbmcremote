package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.presentation.controller.FileListController;
import org.xbmc.api.type.MediaType;

import android.os.Bundle;

public class MusicFilesFragment extends AbsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setController(new FileListController(MediaType.MUSIC));
		super.onCreate(savedInstanceState);
	}
}
