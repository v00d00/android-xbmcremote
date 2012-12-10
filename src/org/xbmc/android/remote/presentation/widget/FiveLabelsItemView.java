package org.xbmc.android.remote.presentation.widget;

import org.xbmc.android.remote.R;
import org.xbmc.api.business.CoverResponse;
import org.xbmc.api.business.IManager;
import org.xbmc.api.type.ThumbSize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FiveLabelsItemView extends RelativeLayout {
	
	public String title;
	public int position;
	public Bitmap posterOverlay;
	public String subtitle;
	public String subtitleRight;
	public String bottomtitle;
	public String bottomright;
	
	private CoverResponse coverResponse;

	private ImageView posterView;
	private TextView titleView;
	private TextView subtitleView;
	private TextView subtitleRightView;
	private TextView bottomtitleView;
	private TextView bottomrightView;
	
	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case AbstractItemView.MSG_UPDATE_COVER:
					setCover((Bitmap)msg.obj);
				break;
			}
		};
	};
	
	public FiveLabelsItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context);
		
		if (manager != null)
			coverResponse = new CoverResponse(context, manager, defaultCover, ThumbSize.SMALL, handler);
		
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

	public CoverResponse getResponse() {
		return coverResponse;
	}
	
	/**
	 * Draws text labels for portrait text view
	 * <pre>
	 * ,-----. 
	 * |     | title (big)
	 * |     | subtitle           subtitleRight
	 * |     | bottomtitle          bottomRight
	 * `-----'
	 * </pre>
	 * @param canvas Canvas to draw on
	
 */

}