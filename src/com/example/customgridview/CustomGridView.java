package com.example.customgridview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class CustomGridView extends Activity implements GridViewActionListener {

	public GridViewAdapter mCampaignGridAdapter;
	public ArrayList<GridModel> mCampaignGridList = new ArrayList<GridModel>();
	private Integer[] mThumbIds = { R.drawable.ic_launcher,R.drawable.ic_action_search, R.drawable.ic_launcher,R.drawable.ic_action_search, 
			R.drawable.ic_launcher,R.drawable.ic_action_search, R.drawable.ic_launcher,R.drawable.ic_action_search};
	private int iGridElementsSize = 0; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		initializeViews();
	}

	private void initializeViews() {

		iGridElementsSize = mThumbIds.length;

		mCampaignGridList.add(new GridModel(0, R.drawable.addvideo, false));
		mCampaignGridList.add(new GridModel(1, R.drawable.photos_add_hover_button, false));
		//Add new model to grid list
		for(int i=0; i<iGridElementsSize; i++) {
			mCampaignGridList.add(new GridModel(i+2, mThumbIds[i], true));
		}

		mCampaignGridAdapter = new GridViewAdapter(this, mCampaignGridList);
		mCampaignGridAdapter.addListener(this);
		GridView gridview = (GridView) findViewById(R.id.gridView); 
		gridview.setAdapter(mCampaignGridAdapter);
		gridview.setNumColumns(4);		
	}

	public void onClickAdd(View view) {
		int lastPos = mCampaignGridList.size();
		mCampaignGridList.add(new GridModel(lastPos, R.drawable.close_icon, false));
		mCampaignGridAdapter.notifyDataSetChanged();
	}

	public void onClickImageDelete(GridModel model, int position) {
		mCampaignGridList.remove(position);
		mCampaignGridAdapter.notifyDataSetChanged();
		Toast.makeText(getApplicationContext(), "Image at position "+position+" is deleted ...and image id is =="+model.getTag(), Toast.LENGTH_SHORT).show();
	}
}
