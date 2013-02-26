package org.xbmc.android.remote.presentation.widget;

import org.xbmc.android.remote.R;
import org.xbmc.api.business.IManager;
import org.xbmc.api.type.ThumbSize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreeLabelsItemView extends AbstractItemView {

	public Bitmap posterOverlay;
	public String subtitle;
	public String subsubtitle;
	
	private ImageView posterView;
	private TextView titleView;
	private TextView subtitleView;
	private TextView bottomtitleView;
	
	
	/**
	 * Draws text labels for portrait text view
	 * <pre>
	 * ,-----. 
	 * |     | title (big)
	 * |     | subtitle
	 * |     | bottomtitle
	 * `-----'
	 * </pre>
     */
	
	public ThreeLabelsItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context, manager, width, defaultCover, selection, width, fixedSize);
		
		final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    inflater.inflate(R.layout.three_labels_item, this, true);
	    
	    posterView = (ImageView) findViewById(R.id.tli_poster);
	    titleView = (TextView) findViewById(R.id.tli_title);
	    subtitleView = (TextView) findViewById(R.id.tli_subtitle);
	    bottomtitleView = (TextView) findViewById(R.id.tli_bottomtitle);
	}
	
	public void reset() {
	}
	
	public void setCover(Bitmap cover) {
		/* HACK for compat */
		titleView.setText(title);
		subtitleView.setText(subtitle);
		bottomtitleView.setText(subsubtitle);
		
		posterView.setImageBitmap(cover);
	}
}