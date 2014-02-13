package com.frappyrabbit;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Danger {
	private Bitmap bitmap;
	private int x;
	private int y;
	private int width;
	private int height;
	private int screenWidth,screenHeight;
	boolean stop;
	Random rand;
	Danger(Bitmap bitmap,int x,int screenWidth,int screenHeight){
		this.bitmap = bitmap;
		this.x = x;
		rand  = new Random();
		
		this.setWidth(bitmap.getWidth());
		this.setHeight(bitmap.getHeight());
		this.screenWidth = screenWidth;
		this.setScreenHeight(screenHeight);
		this.y = screenHeight - 47 - width;
		stop =false;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
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
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public void update(){
		if(stop)return;
		x-=10;
		
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y , null);
	//	canvas.drawBitmap(bitmap, x, y - height - 150, null);
	//	canvas.drawBitmap(bitmap, x, y + 150, null);
		Paint p = new Paint();
		p.setColor(0xFFFFFF);
		canvas.drawCircle(10,19,100,p);
	}
}
