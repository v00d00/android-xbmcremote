package org.xbmc.android.remote.presentation.widget;

import org.xbmc.android.remote.R;
import org.xbmc.api.business.IManager;

import android.content.Context;
import android.graphics.Bitmap;
<<<<<<< HEAD
=======
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint.Align;
>>>>>>> master
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class FiveLabelsItemView extends AbstractItemView {

	public Bitmap posterOverlay;
	public String subtitle;
	public String subtitleRight;
	public String bottomtitle;
	public String bottomright;

	private ImageView posterView;
	private TextView titleView;
	private TextView subtitleView;
	private TextView subtitleRightView;
	private TextView bottomtitleView;
	private TextView bottomrightView;

	public FiveLabelsItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context, manager, width, defaultCover, selection, width, fixedSize);
		
		final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    inflater.inflate(R.layout.five_labels_item, this, true);
	    
	    posterView = (ImageView) findViewById(R.id.fli_poster);
	    titleView = (TextView) findViewById(R.id.fli_title);
	    subtitleView = (TextView) findViewById(R.id.fli_subtitle);
	    subtitleRightView = (TextView) findViewById(R.id.fli_subtitleRight);
	    bottomtitleView = (TextView) findViewById(R.id.fli_bottomtitle);
	    bottomrightView = (TextView) findViewById(R.id.fli_bottomRight);
	}
	
	public void reset() {
	}
	
	public void setCover(Bitmap cover) {
		/* HACK for compat */
		titleView.setText(title);
		subtitleView.setText(subtitle);
		subtitleRightView.setText(subtitleRight);
		bottomtitleView.setText(bottomtitle);
		bottomrightView.setText(bottomright);
		
		posterView.setImageBitmap(cover);
	}
}