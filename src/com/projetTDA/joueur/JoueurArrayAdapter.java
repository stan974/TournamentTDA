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

import com.projetTDA.R;
import com.projetTDA.metier.Joueur;

public class JoueurArrayAdapter extends ArrayAdapter<Joueur> {
	private final Context context;
	private final ArrayList<Joueur> listeDeJoueur;

	//ArrayList<String> pseudoList = new ArrayList<String>();

	public JoueurArrayAdapter(Context context, ArrayList<Joueur> listeDeJoueur) {
		super(context, R.layout.lister_joueurs, listeDeJoueur);
		this.context = context;
		this.listeDeJoueur = listeDeJoueur;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.lister_joueurs, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

			Joueur j = listeDeJoueur.get(position);
			System.out.println("pseudo="+j.getPseudo());
			System.out.println("	avatar="+j.getAvatar());
			textView.setText(j.getPseudo());
			String avatar = "R.drawable.avatar"+j.getAvatar();
			for( int compteur=0;compteur<10;compteur++){
				if (avatar.equals(JOUEURString.get(compteur))) {
					imageView.setImageResource(JOUEURInteger.get(compteur));
				}
			}
			
			
		return rowView;

	}
	
// à garder
	static final ArrayList<String> JOUEURString = 
			new ArrayList<String>(Arrays.asList("R.drawable.avatar1","R.drawable.avatar2","R.drawable.avatar3","R.drawable.avatar4","R.drawable.avatar5","R.drawable.avatar6","R.drawable.avatar7","R.drawable.avatar8","R.drawable.avatar9","R.drawable.avatar10"));


	static final ArrayList<Integer> JOUEURInteger = 
			new ArrayList<Integer>(Arrays.asList(R.drawable.avatar1,R.drawable.avatar2,R.drawable.avatar3,R.drawable.avatar4,R.drawable.avatar5,R.drawable.avatar6,R.drawable.avatar7,R.drawable.avatar8,R.drawable.avatar9,R.drawable.avatar10));
}