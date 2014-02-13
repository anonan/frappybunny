package com.frappyrabbit;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.WindowManager;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
	private MainThread thread;
	private static String TAG  = "MainGame";
	private Rabbit rabbit;
	private Floor floor;
	private Danger[] dangers;
	private int screenWidth;
	private int screenHeight;
	Random random;
	Bitmap background;
	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		thread = new MainThread(getHolder(),this);
		rabbit = new Rabbit(BitmapFactory.decodeResource(getResources(), R.drawable.bunny),100,100,5,3,7);
		
		DisplayMetrics metrics =context.getResources().getDisplayMetrics();
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		floor = new Floor(BitmapFactory.decodeResource(getResources(), R.drawable.ground),0,screenWidth,screenHeight);
		
		Bitmap dangerBitmap = (BitmapFactory.decodeResource(getResources(), R.drawable.skytube));
		background = (BitmapFactory.decodeResource(getResources(), R.drawable.bg));
		random = new Random();
		dangers = new Danger[2];
		dangers[0] = new Danger(dangerBitmap,1301,screenWidth,screenHeight);
		dangers[1] = new Danger(dangerBitmap,1800,screenWidth,screenHeight);
		
		this.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeTop() {
            	
    			if(rabbit.dead){
    				//resetData();
    				
    			}
    			else
    			rabbit.setTouched(true);
            }
            public void onSwipeRight() {
            	
            }
            public void onSwipeLeft() {
            	
            }
            public void onSwipeBottom() {
            	
            }
        });
		//dangers[2] = new Danger(dangerBitmap,2300,screenWidth,screenHeight);
	}
			

	void resetData(){
		rabbit = new Rabbit(BitmapFactory.decodeResource(getResources(), R.drawable.bunny_jump),100,100,5,3,7);
		
		
		floor = new Floor(BitmapFactory.decodeResource(getResources(), R.drawable.ground),0,screenWidth,screenHeight);
		
		Bitmap dangerBitmap = (BitmapFactory.decodeResource(getResources(), R.drawable.floor_rotate));
		random = new Random();
		dangers = new Danger[2];
			dangers[0] = new Danger(dangerBitmap,1301,screenWidth,screenHeight);
			dangers[1] = new Danger(dangerBitmap,1800,screenWidth,screenHeight);
		//	dangers[2] = new Danger(dangerBitmap,2500,screenWidth,screenHeight);
			
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(!thread.isAlive()){
			thread.setRunning(true);
			thread.start();
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Surface is being destroyed ");

		thread.setRunning(false);
		boolean retry = true;
		while(retry){
			try{
				thread.join();
				retry = false;
			}
			catch (InterruptedException e){
				
			}
		}
		Log.d(TAG, "Thread was shut down cleanly ");

	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			if(rabbit.dead){
				resetData();
				
			}
			return true;
		}
		else if(event.getAction()==MotionEvent.ACTION_UP){
			Log.v(TAG,"AU");
			rabbit.setTouched(false);
			return true;
		}
		else return super.onTouchEvent(event);
	}
	
	
	
	
	@Override
	protected void onDraw(Canvas canvas){
		if(canvas!=null){
			canvas.drawColor(Color.BLUE);
			canvas.drawBitmap(background, 0, 0, null);
			rabbit.draw(canvas);
			floor.draw(canvas);
			for(int i=0;i<dangers.length;i++){
				dangers[i].draw(canvas);
			}
		}
		
	}
	
	public void update(){
		if(rabbit!=null)rabbit.run(System.currentTimeMillis());
		if(floor!=null)floor.update();
		int lastDanger=0;
		for(int i=0;i<dangers.length;i++){
			dangers[i].update();
			int position = dangers[i].getX()+dangers[i].getWidth();
			if(position>lastDanger)lastDanger = position;
		}
		
		for(int i=0;i<dangers.length;i++){
			if(dangers[i].getX()+dangers[i].getWidth()< 0 ){
				dangers[i].setX(lastDanger+400);
				//dangers[i].setY(random.nextInt((screenHeight-100)) +50);
				
			}
				
		}
		
		if(rabbit.getY()+rabbit.getSpriteHeight()>screenHeight - floor.getHeight()){
			rabbit.setY(floor.getY()-rabbit.getSpriteHeight());
			//floor.stop = true;
//			for(int i=0;i<dangers.length;i++){
//				dangers[i].stop = true;
//			}
		}
	}
}
