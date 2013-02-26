package org.xbmc.android.remote.presentation.widget;

import org.xbmc.api.business.IManager;
import org.xbmc.api.type.ThumbSize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;

public class GridPosterItemView extends AbstractItemView {
		
	public GridPosterItemView(Context context, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context, width, defaultCover, selection, fixedSize);
	}
	
	public GridPosterItemView(Context context, IManager manager, int width, Bitmap defaultCover, Drawable selection, boolean fixedSize) {
		super(context, manager, width, defaultCover, selection, ThumbSize.MEDIUM, fixedSize);
	}
	
	protected void onDraw(Canvas canvas) {
	}

	@Override
	public void reset() {
	}

	@Override
	public void setCover(Bitmap cover) {		
	}
}