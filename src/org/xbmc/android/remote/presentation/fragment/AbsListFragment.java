package org.xbmc.android.remote.presentation.fragment;

import org.xbmc.android.remote.R;
import org.xbmc.android.remote.presentation.controller.ListController;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;

public class AbsListFragment extends Fragment {
	protected ListController controller;
	protected Handler handler;
	
	public AbsListFragment() {}
	
	public void setController(ListController controller) {
		this.controller = controller;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if (handler == null)
			handler = new Handler();
		
		if (controller != null) {
			controller.onCreate(getActivity(), handler, ((AbsListView) ((FrameLayout) getView()).getChildAt(0)));		
		}
		
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.albumgrid, container, false);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		controller.onCreateOptionsMenu(menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public void onPause() {
		controller.onActivityPause();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		controller.onActivityResume(getActivity());
		super.onResume();
	}
}
