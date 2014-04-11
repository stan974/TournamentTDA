package com.projetTDA.joueur;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.projetTDA.bdd.DataAccessLayer;
import com.projetTDA.metier.Joueur;

public class ListerJoueurs extends ListActivity {

	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
	String pseudo = "";
	String avatar = "";
	String idJoueur;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DataAccessLayer myDAL = new DataAccessLayer(ListerJoueurs.this);
		listeJoueur = myDAL.getListeJoueurs();
		System.out.println("liste="+listeJoueur);
		setListAdapter(new JoueurArrayAdapter(this, listeJoueur));

}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Joueur j = listeJoueur.get(position);
		String avatar2 = j.getAvatar();
		String pseudo2 = j.getPseudo();
		String id2 = String.valueOf(j.getId_joueur()); 
		System.out.println("pseudoListé="+pseudo2);
		System.out.println("avatarListé="+avatar2);
		System.out.println("idListé="+id2);
		Intent to_ModifierJoueurActivity = new Intent(ListerJoueurs.this, ModifierJoueur.class);

		to_ModifierJoueurActivity.putExtra(pseudo, pseudo2);
		to_ModifierJoueurActivity.putExtra(avatar, avatar2);
		to_ModifierJoueurActivity.putExtra(idJoueur, id2);
		startActivity(to_ModifierJoueurActivity);
	}

}

