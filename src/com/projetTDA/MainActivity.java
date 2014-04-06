package com.projetTDA;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.projetTDA.joueur.GererJoueur;
import com.projetTDA.metier.Equipe;
import com.projetTDA.metier.Joueur;
import com.projetTDA.metier.Match;
import com.projetTDA.metier.Tournoi;
import com.projetTDA.statistique.Statistique;
import com.projetTDA.tournamentbuilder.R;
import com.projetTDA.tournoi.GererTournoi;


public class MainActivity extends ActionBarActivity {
//	private TextView texte = null;
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
		  
		  final Button gererTournoi = (Button) findViewById(R.id.gererTournoiButton);
		  gererTournoi.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, GererTournoi.class);
			startActivity(intent);
			}
		});
		  
		  
		  final Button gererJoueur = (Button) findViewById(R.id.gererJoueurButton);
		  gererJoueur.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, GererJoueur.class);
			startActivity(intent);
			}
		});
		  
		  final Button statistique = (Button) findViewById(R.id.statistiqueButton);
		  statistique.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Statistique.class);
			startActivity(intent);
			}
		}); 
		  
		  
		  Joueur ronaldo = new Joueur(1,"ronaldo","img/ronaldo.jpeg");
			Joueur cafe = new Joueur(2,"cafe","img/cafe.jpeg");
			
			Joueur zidane = new Joueur(3,"Zidane","img/Zidane.jpeg");
			Joueur petit = new Joueur(4,"petit","img/petit.jpeg");
			
			Joueur beckham = new Joueur(5,"beckham","img/beckham.jpeg");
			Joueur owell = new Joueur(6,"owell","img/owell.jpeg");
			
			Joueur casillas = new Joueur(7,"casillas","img/casillas.jpeg");
			Joueur raoul = new Joueur(8,"raoul","img/raoul.jpeg");
			
			Equipe bresil = new Equipe(26,"Bresil");
			bresil.ajoutJoueur(ronaldo);
			bresil.ajoutJoueur(cafe);
			
			Equipe france = new Equipe(33,"France");
			france.ajoutJoueur(zidane);
			france.ajoutJoueur(petit);
			
			Equipe angleterre = new Equipe(15,"Angleterre");
			angleterre.ajoutJoueur(beckham);
			angleterre.ajoutJoueur(owell);
			
			Equipe espagne = new Equipe(34,"Espagne");
			espagne.ajoutJoueur(casillas);
			espagne.ajoutJoueur(raoul);
			
			Equipe maroc = new Equipe(40,"Maroc");
			maroc.ajoutJoueur(casillas);
			maroc.ajoutJoueur(beckham);
			
			Equipe usa = new Equipe(66,"Etats-Unis");
			usa.ajoutJoueur(ronaldo);
			usa.ajoutJoueur(petit);
			
			Tournoi coupeDuMonde = new Tournoi("Coupe du monde","Mars 2014","Foot");
			
			coupeDuMonde.ajouterEquipe(bresil);
			coupeDuMonde.ajouterEquipe(france);
			coupeDuMonde.ajouterEquipe(angleterre);
			coupeDuMonde.ajouterEquipe(espagne);
			coupeDuMonde.ajouterEquipe(maroc);
			//coupeDuMonde.ajouterEquipe(maroc);
			coupeDuMonde.ajouterEquipe(usa);
			coupeDuMonde.tirageAuSortMatch();
			
					
		  
		  
		  
		  
		   }

}
