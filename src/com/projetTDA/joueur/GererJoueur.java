package com.projetTDA.joueur;

import com.projetTDA.tournamentbuilder.R;
import com.projetTDA.tournamentbuilder.R.id;
import com.projetTDA.tournamentbuilder.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GererJoueur extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		  
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.gerer_joueur);
		  
		  final Button creerJoueur = (Button) findViewById(R.id.creerJoueurButton);
		  creerJoueur.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(GererJoueur.this, CreerJoueur.class);
			startActivity(intent);
			}
		});
		  	  
		  final Button modifierJoueur = (Button) findViewById(R.id.modifierJoueurButton);
		  modifierJoueur.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(GererJoueur.this, ModifierJoueur.class);
			startActivity(intent);
			}
		});
		  
		  final Button listerJoueurs = (Button) findViewById(R.id.listerJoueursButton);
		  listerJoueurs.setOnClickListener(new OnClickListener() {
					
		  @Override
		  public void onClick(View v) {
			Intent intent = new Intent(GererJoueur.this, ListerJoueurs.class);
			startActivity(intent);
			}
		});
	}
}
