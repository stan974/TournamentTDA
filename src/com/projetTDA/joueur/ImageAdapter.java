package com.projetTDA.joueur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.projetTDA.R;

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
	    	Button imageButton;
	        if (convertView == null) {  // si pas recyclé, initialise les attributs
	        	imageButton = new Button(mContext);
	        	imageButton.setLayoutParams(new GridView.LayoutParams(85, 85));
	        	imageButton.setPadding(8, 8, 8, 8);
	        	imageButton.setFocusable(false);
	        	imageButton.setClickable(false);
	        } else {
	            imageButton = (Button) convertView;
	        }
	        imageButton.setBackgroundResource(mThumbIds[position]);
	        imageButton.setId(position);
	        return imageButton;
	    }

	    // reference à notre base image
	    private Integer[] mThumbIds = {
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
