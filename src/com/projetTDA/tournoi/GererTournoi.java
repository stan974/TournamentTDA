package com.projetTDA.tournoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.projetTDA.R;

public class GererTournoi extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		  
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.gerer_tournoi);
			 final Button creerTournoi = (Button) findViewById(R.id.creerTournoiButton);
			  creerTournoi.setOnClickListener(new OnClickListener() {
						
			  @Override
			  public void onClick(View v) {
				Intent intent = new Intent(GererTournoi.this, CreerTournoi.class);
				startActivity(intent);
				}
			});
	}
}
