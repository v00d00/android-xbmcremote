package org.xbmc.android.remote.presentation.widget;

import org.xbmc.api.business.CoverResponse;
import org.xbmc.api.business.IManager;
import org.xbmc.api.type.ThumbSize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.RelativeLayout;

public abstract class AbstractItemView extends RelativeLayout {
	
	public static final int MSG_UPDATE_COVER = -1;
	protected final CoverResponse coverResponse;
	
	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_UPDATE_COVER:
					setCover((Bitmap)msg.obj);
				break;
			}
		};
	};
	
	protected int position;
	protected String title;
	
	public AbstractItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, int thumbSize, boolean fixedSize) {
		super(context);
		
		if (manager != null)
			coverResponse = new CoverResponse(context, manager, defaultCover, ThumbSize.SMALL, handler);
		else
			coverResponse = null;
	}
	
	public AbstractItemView(Context context, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		this(context, null, width, defaultCover, selection, 0, fixedSize);
	}

	public abstract void reset();
	public abstract void setCover(Bitmap cover);

	public CoverResponse getResponse() {
		return coverResponse;
	}
	
	public boolean hasBitmap() {
		return true;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}