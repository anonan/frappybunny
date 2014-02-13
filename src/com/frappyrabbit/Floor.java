package com.frappyrabbit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;

public class Floor {
	private Bitmap bitmap;
	private int x;
	private int y;
	private int width;
	private int height;
	private int screenWidth,screenHeight;
	boolean stop;
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	Floor(Bitmap bitmap,int x,int screenWidth,int screenHeight){
		this.bitmap = bitmap;
		this.x = x;
		this.y = screenHeight - bitmap.getHeight();
		this.setWidth(bitmap.getWidth());
		this.setHeight(bitmap.getHeight());
		this.screenWidth = screenWidth;
		this.setScreenHeight(screenHeight);
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void update(){
		if(stop)return;
		x-=10;
		if(x+(2*bitmap.getWidth())<screenWidth){
			x = 0;
		}
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
		if(x+bitmap.getWidth()<screenWidth){
			canvas.drawBitmap(bitmap, x+bitmap.getWidth(), y, null);
		}
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
}
