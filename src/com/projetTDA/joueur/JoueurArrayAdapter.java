package com.projetTDA.joueur;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projetTDA.tournamentbuilder.R;

public class JoueurArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;

	
	public JoueurArrayAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.lister_joueurs, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.lister_joueurs, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		//à modifier la textview pour afficher les pseudos
		textView.setText(values.get(position));
		// Change icon based on name
		String s = values.get(position);
//		String t = ""+JOUEURString.get(1);
		System.out.println("S="+s);
//		System.out.println("TAB="+JOUEURString.get(1).toString() );
//		System.out.println("TAB2="+Integer.toString(JOUEURString.get(1)));
//		System.out.println("val="+values);
//		System.out.println(String.valueOf(Jou.get(1)));
	//	System.out.println(Integer.decode("R.drawable.avatar2"));
		  
		for( int i=0;i<10;i++){
			if (s.equals(JOUEURString.get(i))) {
				imageView.setImageResource(JOUEURInteger.get(i));
			}
		}

		return rowView;

	}
	static final ArrayList<String> JOUEURString = 
            new ArrayList<String>(Arrays.asList("R.drawable.avatar1","R.drawable.avatar2","R.drawable.avatar3","R.drawable.avatar4","R.drawable.avatar5","R.drawable.avatar6","R.drawable.avatar7","R.drawable.avatar8","R.drawable.avatar9","R.drawable.avatar10"));
 
			
	static final ArrayList<Integer> JOUEURInteger = 
            new ArrayList<Integer>(Arrays.asList(R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8,R.drawable.avatar9,R.drawable.avatar10));
}