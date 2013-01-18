package com.example.customgridview;

public class GridModel {
	
	private int tag;
	private int image;
	private boolean boolIsServerImage;
	
	public GridModel(int tag, int iImageUrl, boolean isServerImage) {
		this.tag = tag;
		this.image = iImageUrl;
		this.boolIsServerImage = isServerImage;
	}
	
	public int getTag(){
		return tag;
	}
	
	public int getImage(){
		return image;
	}
	
	public boolean checkIsServerImage(){
		return boolIsServerImage;
	}
}
