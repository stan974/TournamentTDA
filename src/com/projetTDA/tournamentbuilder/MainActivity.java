package com.projetTDA.tournamentbuilder;

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
		  
		  
		   }

}
