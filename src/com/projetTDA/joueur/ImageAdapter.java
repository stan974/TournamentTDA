package com.projetTDA.joueur;

import com.projetTDA.tournamentbuilder.R;
import com.projetTDA.tournamentbuilder.R.drawable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return mThumbIds.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mThumbIds[position]);
	        return imageView;
	    }

	    // reference à notre base image
	    private Integer[] mThumbIds = {
	            R.drawable.avatar1, R.drawable.avatar2,
	            R.drawable.avatar3, R.drawable.avatar4,
	            R.drawable.avatar5, R.drawable.avatar6,
	            R.drawable.avatar7, R.drawable.avatar8,
	            R.drawable.avatar9, R.drawable.avatar10,
	            R.drawable.avatar1, R.drawable.avatar2,
	            R.drawable.avatar3, R.drawable.avatar4,
	            R.drawable.avatar5, R.drawable.avatar6,
	            R.drawable.avatar7, R.drawable.avatar8,
	            R.drawable.avatar9, R.drawable.avatar10
	    };
	    
	   public Integer[] getThumbIds(){
	    	return mThumbIds;
	    }
	}
