package com.frappyrabbit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Rabbit {
	private Bitmap bitmap;
	private int x;
	private int y;
	
	private Rect sourceRect;
	private int frameNr;
	private int currentFrame;
	private long frameTicker;
	private int framePeriod;
	private int frameRow;
	private int frameColumn;
	private int spriteWidth;
	private int spriteHeight;
	float gravity = 0.5f;
	float velocityY;
	boolean jumping;
	boolean dead;
	int frame;
	
	int rabbitState;
	final int STATE_WALK = 0;
	final int STATE_JUMP = 1;
	final int STATE_CROUCH = 2;
	
	private static final int JUMP_VELOCITY = 20;
	
	
	boolean touched;
	public Rabbit(Bitmap bitmap, int x, int y,int fps,int frameColumn,int frameRow) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		
		currentFrame = 0;
		frameNr = frameColumn*frameRow;
		spriteWidth = bitmap.getWidth()/frameColumn;
		spriteHeight = bitmap.getHeight()/frameRow;
		Log.v("HEIGHT",""+spriteHeight+"/"+bitmap.getHeight()+"/"+frameRow+"/"+ spriteWidth);
		sourceRect = new Rect(0,0,spriteWidth,spriteHeight);
		framePeriod = 1000/fps;
		frameTicker = 0;
		jumping=false;
		dead = false;
		frame = 0;
		this.frameRow = frameRow;
		this.frameColumn = frameColumn;
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
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		
		this.touched = touched;
		if(touched)velocityY = -50.0f;;
	}

	public void run(long gameTime) {
		
		if(dead)return;
//		
//		if (touched) {//HJump
//			velocityY = -50.0f;
//			Log.v("TA","JUMPPPPPPP");
//		} else {
//		//	y++;
//		}
		
		y += velocityY;      // Apply vertical velocity to X position
	    velocityY += gravity;        // Apply gravity to vertical velocity
		
	    if(velocityY< -6.0f){
	    	velocityY = -6.0f;
	    }
	    
	   
	    
		//image process
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {//looping
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		
		
		
		this.sourceRect.left = (int) ((currentFrame%frameColumn) * spriteWidth);
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
		
		this.sourceRect.top = (int) (Math.floor(currentFrame/frameColumn) * spriteHeight);
		this.sourceRect.bottom = this.sourceRect.top + spriteHeight;
		
	}

	public void draw(Canvas canvas) {
		Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
	}

	public void die(){
		dead = true;
	}
	
	public int getSpriteHeight(){
	return	spriteHeight;
	}
}
