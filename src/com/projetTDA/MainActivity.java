package com.projetTDA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.projetTDA.bdd.DataAccessLayer;
import com.projetTDA.joueur.GererJoueur;
import com.projetTDA.metier.Joueur;
import com.projetTDA.statistique.Statistique;
import com.projetTDA.tournoi.GererTournoi;


public class MainActivity extends ActionBarActivity {
//	private TextView texte = null;
	Button mBTN_gererTournoi = null;
	Button mBTN_gererJoueur  = null;
	Button mBTN_statistiques = null;
//	String PSEUDOFile = "pseudo.txt";
//	String AVATARFile = "avatar.txt";
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
		  
		  
		  DataBaseHelper myDB;
		  myDB = new DataBaseHelper(this);
		  
		  try {
			  
			  myDB.createDataBase();
			  
		  } catch (IOException ioe) {
			  
			  throw new Error("Enable to create database");
			  
		  }
		  
		  try {
			  
			  myDB.openDataBase();
		  } catch (SQLException sqle) {
			  
			  throw sqle;
			  
		  }
		  
		  DataAccessLayer myDAL = new DataAccessLayer(this);
		  
//		  Joueur j1 = new Joueur(0,"J1","4");
//		  Joueur j2 = new Joueur(0,"J2","5");
//		  Joueur j3 = new Joueur(0,"J3","3");
//		  Joueur j4 = new Joueur(0,"J4","8");
//		  Joueur j5 = new Joueur(0,"J5","7");
//		  
//		  myDAL.ajouterJoueurBDD(j1);
//		  myDAL.ajouterJoueurBDD(j2);
//		  myDAL.ajouterJoueurBDD(j3);
//		  myDAL.ajouterJoueurBDD(j4);
//		  myDAL.ajouterJoueurBDD(j5);
		  
		  List<Joueur> joueurs = new ArrayList<Joueur>(myDAL.getListeJoueurs());
		  
		  String tmp = "";
		  for(Joueur j : joueurs) {tmp += j.getId_joueur() + "|" + j.getPseudo() + "|" + j.getAvatar() + "\n"; }		  
		  Toast.makeText(this, tmp, Toast.LENGTH_LONG).show();
		 // deleteFile(PSEUDOFile);
		 // deleteFile(AVATARFile);
		  
		  mBTN_gererTournoi = (Button) findViewById(R.id.BTN_gererTournoi);
		  mBTN_gererTournoi.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent to_GererTournoiActivity = new Intent(MainActivity.this, GererTournoi.class);
			startActivity(to_GererTournoiActivity);
			}
		});
		  
		  
		  mBTN_gererJoueur = (Button) findViewById(R.id.BTN_gererJoueur);
		  mBTN_gererJoueur.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent to_GererJoueurActivity = new Intent(MainActivity.this, GererJoueur.class);
			startActivity(to_GererJoueurActivity);
			}
		});
		  
		  mBTN_statistiques = (Button) findViewById(R.id.BTN_statistiques);
		  mBTN_statistiques.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent to_StatistiquesActivity = new Intent(MainActivity.this, Statistique.class);
			startActivity(to_StatistiquesActivity);
			}
		}); 
		  
		  
//		  	Joueur ronaldo = new Joueur(1,"ronaldo","img/ronaldo.jpeg");
//			Joueur cafe = new Joueur(2,"cafe","img/cafe.jpeg");
//			
//			Joueur zidane = new Joueur(3,"Zidane","img/Zidane.jpeg");
//			Joueur petit = new Joueur(4,"petit","img/petit.jpeg");
//			
//			Joueur beckham = new Joueur(5,"beckham","img/beckham.jpeg");
//			Joueur owell = new Joueur(6,"owell","img/owell.jpeg");
//			
//			Joueur casillas = new Joueur(7,"casillas","img/casillas.jpeg");
//			Joueur raoul = new Joueur(8,"raoul","img/raoul.jpeg");
//			
//			Equipe bresil = new Equipe(26,"Bresil");
//			bresil.ajoutJoueur(ronaldo);
//			bresil.ajoutJoueur(cafe);
//			
//			Equipe france = new Equipe(33,"France");
//			france.ajoutJoueur(zidane);
//			france.ajoutJoueur(petit);
//			
//			Equipe angleterre = new Equipe(15,"Angleterre");
//			angleterre.ajoutJoueur(beckham);
//			angleterre.ajoutJoueur(owell);
//			
//			Equipe espagne = new Equipe(34,"Espagne");
//			espagne.ajoutJoueur(casillas);
//			espagne.ajoutJoueur(raoul);
//			
//			Equipe maroc = new Equipe(40,"Maroc");
//			maroc.ajoutJoueur(casillas);
//			maroc.ajoutJoueur(beckham);
//			
//			Equipe usa = new Equipe(66,"Etats-Unis");
//			usa.ajoutJoueur(ronaldo);
//			usa.ajoutJoueur(petit);
//			
//			Tournoi coupeDuMonde = new Tournoi("Coupe du monde","Mars 2014","Foot");
//			
//			coupeDuMonde.ajouterEquipe(bresil);
//			coupeDuMonde.ajouterEquipe(france);
//			coupeDuMonde.ajouterEquipe(angleterre);
//			coupeDuMonde.ajouterEquipe(espagne);
//			coupeDuMonde.ajouterEquipe(maroc);
//			//coupeDuMonde.ajouterEquipe(maroc);
//			coupeDuMonde.ajouterEquipe(usa);
//			coupeDuMonde.tirageAuSortMatch();
//			
//					
//		  
//		  
//		  
		  
		   }

}
