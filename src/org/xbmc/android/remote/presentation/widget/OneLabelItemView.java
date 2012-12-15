package org.xbmc.android.remote.presentation.widget;

import org.xbmc.android.remote.R;
import org.xbmc.api.business.CoverResponse;
import org.xbmc.api.business.IManager;
import org.xbmc.api.type.ThumbSize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OneLabelItemView extends AbstractItemView {

	private ImageView posterView;
	private TextView titleView;
	
	/**
	 * Draws text labels for portrait text view
	 * <pre>
	 * ,-----.
	 * |     |
	 * |     | title (big)
	 * |     |
	 * `-----'
	 * </pre>
     */
	
	public OneLabelItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context, manager, width, defaultCover, selection, ThumbSize.SMALL, fixedSize);
		
		final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    inflater.inflate(R.layout.one_label_item, this, true);
	    
	    posterView = (ImageView) findViewById(R.id.oli_poster);
	    titleView = (TextView) findViewById(R.id.oli_title);
	}
	
	public OneLabelItemView(Context context, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		this(context, null, width, defaultCover, selection, fixedSize);
	}
	
	public void reset() {
	}
	
	public void setCover(Bitmap cover) {
		/* HACK for compat */
		titleView.setText(title);
		
		posterView.setImageBitmap(cover);
	}
}