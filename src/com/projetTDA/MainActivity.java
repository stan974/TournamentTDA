package com.projetTDA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.projetTDA.bdd.DataAccessLayer;
import com.projetTDA.joueur.GererJoueur;
import com.projetTDA.metier.Equipe;
import com.projetTDA.metier.Joueur;
import com.projetTDA.metier.Match;
import com.projetTDA.metier.Tournoi;
import com.projetTDA.statistique.Statistique;
import com.projetTDA.tournoi.GererTournoi;


public class MainActivity extends ActionBarActivity {
//	private TextView texte = null;
	private Button mBTN_gererTournoi = null;
	private Button mBTN_gererJoueur  = null;
	private Button mBTN_statistiques = null;
//	String PSEUDOFile = "pseudo.txt";
//	String AVATARFile = "avatar.txt";
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);

		  DataAccessLayer myDAL = new DataAccessLayer(this);
		  /* CLEAR */
		  
		  myDAL.clearBDD();
		  
		  /* JOUEURS */
		  
		  Joueur j1 = new Joueur(0,"Ando","1");
		  Joueur j2 = new Joueur(0,"Lionel","10");
		  Joueur j3 = new Joueur(0,"Stan","9");
		  Joueur j4 = new Joueur(0,"Guilhem","7");
		  
		  j1 = myDAL.ajouterJoueurBDD(j1);
		  j2 = myDAL.ajouterJoueurBDD(j2);
		  j3 = myDAL.ajouterJoueurBDD(j3);
		  j4 = myDAL.ajouterJoueurBDD(j4);
		  
		
		  /* EQUIPES */
		  ArrayList<Joueur> list_e1 = new ArrayList<Joueur>();
		  ArrayList<Joueur> list_e2 = new ArrayList<Joueur>();
		  list_e1.add(j2);
		  list_e1.add(j4);
		  list_e2.add(j1);
		  list_e2.add(j3);
		  Equipe e1 = new Equipe(0, "OM", list_e1);
		  Equipe e2 = new Equipe(0, "LE MANS", list_e2);
		  
		  e1 = myDAL.ajouterEquipeBDD(e1);		  
		  e2 = myDAL.ajouterEquipeBDD(e2);
		  
		  List<Equipe> equipes = new ArrayList<Equipe>(myDAL.getListeEquipes());
		 String tmp = "";
		  for(Equipe e : equipes)
		  {
			  tmp += e.getId_equipe() + "|" + e.getNom_equipe() + "\n";
			  for(Joueur j : e.getListe_joueur())
			  {
				  tmp += "\t" + j.getId_joueur() + "|" + j.getPseudo() + "|" + j.getAvatar() + "\n";
			  }
			  tmp += "--------\n";
		  }		  
		  Toast.makeText(this, tmp, Toast.LENGTH_LONG).show();
		  
		  /* TOURNOI */
		  
		  Tournoi tournoi = new Tournoi();
		  
		  tournoi.setNom_tournoi("Europa League");
		  
		  SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy");
		  Date currentTime_1 = new Date();
		  String dateString = formatter.format(currentTime_1);
		  tournoi.setDate_creation(dateString);		  
		  
		  tournoi.setIs_championnat(false);
		  
		  tournoi.setLib_sport("Pétanque");
		  
		  HashMap<Equipe, Integer> classement = new HashMap<Equipe, Integer>();
		  classement.put(e1, Integer.valueOf(0));
		  classement.put(e2, Integer.valueOf(0));
		  tournoi.setClassement(classement);
		  
		  ArrayList<Match> list_matchs = new ArrayList<Match>();
		  list_matchs.add(new Match(e1, e2, null, null, 1));
		  tournoi.setListe_matchs(list_matchs);
		  
		  tournoi = myDAL.ajouterTournoiBDD(tournoi);
		  
		  tmp = "";
		  for(Tournoi t : myDAL.getListeTournoi())
		  {
			  tmp += t.toString();
			  tmp += "\n--------\n";
		  }
		  System.out.println("#1");
		  System.out.println(tmp);
		  
		  /* TOURNOI - MODIF */
	
		  tournoi.setNom_tournoi("Champions League");
		  tournoi.setLib_sport("Foot");
		  myDAL.majTournoiBDD(tournoi);
		  
		  tournoi.getListe_matchs().get(0).setScore1(Integer.valueOf(1));
		  tournoi.getListe_matchs().get(0).setScore2(Integer.valueOf(3));
		  myDAL.majMatchBDD(tournoi);
		  
		  tmp = "";
		  for(Tournoi t : myDAL.getListeTournoi())
		  {
			  tmp += t.toString();
			  tmp += "\n--------\n";
		  }
		  System.out.println("#2");
		  System.out.println(tmp);
		  
		  
		  
		  
		  
		  
		  
		  
//		  DataBaseHelper myDB = new DataBaseHelper(this);
		  
//		  try {
//			  
//			  myDB.createDataBase();
//			  
//		  } catch (IOException ioe) {
//			  
//			  throw new Error("Enable to create database");
//			  
//		  }
//		  
//		  try {
//			  
//			  myDB.openDataBase();
//		  } catch (SQLException sqle) {
//			  
//			  throw sqle;
//			  
//		  }
		  
//		  List<Joueur> joueurs = new ArrayList<Joueur>(myDAL.getListeJoueurs());
		  
//		  String tmp = "";
//		  for(Joueur j : joueurs) {tmp += j.getId_joueur() + "|" + j.getPseudo() + "|" + j.getAvatar() + "\n"; }		  
//		  Toast.makeText(this, tmp, Toast.LENGTH_LONG).show();
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
		  mBTN_gererJoueur.performClick();
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
  }

}
