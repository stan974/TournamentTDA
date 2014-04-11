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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
//		Joueur ronaldo = new Joueur(1,"ronaldo","1");
//		Joueur cafe = new Joueur(2,"cafe","7");
//		
//		Joueur zidane = new Joueur(3,"Zidane","6");
//		Joueur petit = new Joueur(4,"petit","4");
//		
//		Joueur beckham = new Joueur(5,"beckham","5");
//		Joueur owell = new Joueur(6,"owell","8");
//		
//		Joueur casillas = new Joueur(7,"casillas","2");
//		Joueur raoul = new Joueur(8,"raoul","3");
//
//		listeJoueur.add(ronaldo);
//		listeJoueur.add(cafe);
//		listeJoueur.add(zidane);
//		listeJoueur.add(petit);
//		listeJoueur.add(beckham);
//		listeJoueur.add(owell);
//		listeJoueur.add(casillas);
//		listeJoueur.add(raoul);
//		
		DataAccessLayer myDAL = new DataAccessLayer(ListerJoueurs.this);
//		listeJoueur = myDAL.getListeJoueurs();
		
		setListAdapter(new JoueurArrayAdapter(this, listeJoueur));

}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Joueur j = listeJoueur.get(position);
		String avatar2 = j.getAvatar();
		String pseudo2 = j.getPseudo();
		System.out.println("pseudoListé="+j.getPseudo());
		System.out.println("avatarListé="+j.getAvatar());
		Intent to_ModifierJoueurActivity = new Intent(ListerJoueurs.this, ModifierJoueur.class);
		to_ModifierJoueurActivity.putExtra(avatar, avatar2);
		to_ModifierJoueurActivity.putExtra(pseudo, pseudo2);
		startActivity(to_ModifierJoueurActivity);
	}

}

