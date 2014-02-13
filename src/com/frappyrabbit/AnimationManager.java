package com.frappyrabbit;

import java.util.Vector;
//Help to decide what frame will be show


class Animation{
	String name;
	int[] frameList;
	int[] eachFrameTime;
	int currentFrame;
	Animation(String name,int[]frameList){
		this.name = name;
		this.frameList = frameList;
	}
}
public class AnimationManager {
	int width;
	int height;
	int frame;
	int frameWidth;
	int frameHeight;
	Vector<Animation> animationList;
	
	public AnimationManager() {
		// TODO Auto-generated constructor stub
		animationList = new Vector<Animation>();
	}
	
	void makeNewAnimation(String name,int[] frameList){
		animationList.add(new Animation(name,frameList));
	}
	
	void runAnimation(String name,int time){
		for(int i=0;i<animationList.size();i++){
			if(animationList.get(i).name.equals(name)){
				
				break;
			}
		}
	}
}
