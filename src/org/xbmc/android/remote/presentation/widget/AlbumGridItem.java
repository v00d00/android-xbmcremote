package org.xbmc.android.remote.presentation.widget;

import org.xbmc.android.remote.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlbumGridItem extends RelativeLayout {
	
	private ImageView cover;
	private TextView title;
	private int position;

	public AlbumGridItem(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.album_grid_item, this, true);
		
		cover = (ImageView) findViewById(R.id.album_grid_image);
		title = (TextView) findViewById(R.id.album_grid_text);
		
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				LayoutParams.MATCH_PARENT, 
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 128, getResources().getDisplayMetrics()));
		setLayoutParams(lp);
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setCover(Bitmap bitmap) {
		cover.setImageBitmap(bitmap);
	}
	
	public void setTitle(String text) {
		title.setText(text);
	}
}
