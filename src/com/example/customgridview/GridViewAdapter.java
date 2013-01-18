package com.example.customgridview;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {
	
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GridModel> mGridList;
    private ArrayList<GridViewActionListener> listeners = new ArrayList<GridViewActionListener>();
    
    public GridViewAdapter(Context c, ArrayList<GridModel> mGridList) {
        this.mContext = c;
        this.mGridList = mGridList;
        this.mInflater = LayoutInflater.from(mContext);
    }

	public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        GridModel model = getItem(position);
        if(convertView==null){
        	
            grid = new View(mContext);
            grid=mInflater.inflate(R.layout.grid_image, parent, false);
            
        }else{
            grid = (View)convertView;
        }

        ImageView imgPhoto = (ImageView)grid.findViewById(R.id.imgPhoto);
        imgPhoto.setImageResource(model.getImage());
        
        ImageView imgDelete = (ImageView)grid.findViewById(R.id.imgDelete);
        if (position > 1) {
        	 imgDelete.setTag(model);
             imgDelete.setContentDescription(String.valueOf(position));
             imgDelete.setVisibility(View.VISIBLE);
             imgDelete.setOnClickListener(deleteListener);
		} else {
			imgPhoto.setFocusable(false);
			imgPhoto.setFocusableInTouchMode(false);
			imgDelete.setVisibility(View.INVISIBLE);
		}
        
		return grid;
	}

	public int getCount() {
		return mGridList.size();
	}

	public GridModel getItem(int position) {
		return (GridModel)mGridList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	OnClickListener deleteListener = new OnClickListener() {
		public void onClick(View view) {
			notifyListener((GridModel) view.getTag(), view.getContentDescription().toString());
		}
	};
	
	public void addListener(GridViewActionListener listener){
		listeners.add(listener);
	}

	private void notifyListener( GridModel object, String position){
		for (GridViewActionListener listener : listeners) {
			listener.onClickImageDelete(object, Integer.parseInt(position));
		}
	}
}
