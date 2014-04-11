package com.projetTDA.joueur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.projetTDA.metier.Joueur;
import com.projetTDA.tournamentbuilder.R;

public class ModifierJoueur extends ActionBarActivity {

	private EditText editText;
	private Button buttonEnvoyer;
	String avatar="";
	String pseudo="";
	
	Joueur j = new Joueur();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifier_joueur);
		Intent intent = getIntent();
		int bool;
		//transmet par défaut les valeurs d'avatar et de pseudo existant
		j.setPseudo(intent.getStringExtra(pseudo));
		j.setAvatar(intent.getStringExtra(avatar));
//		System.out.println("pseudoModifié="+j.getPseudo());
//		System.out.println("avatarModifié="+j.getAvatar());
		
		
		ImageAdapter imageAdapter = new ImageAdapter(this);
		
		final GridView gridview = (GridView) findViewById(R.id.avatarView);
		
		gridview.setAdapter(imageAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				v.setBackgroundResource(R.drawable.bg);
				j.setAvatar(String.valueOf(position)); //assigne la position de l'avatar à la variable avatar
			}
		});

		//récupération de l'EditText grâce à son ID
		editText = (EditText) findViewById(R.id.EditTextPseudo);
		editText.setText(intent.getStringExtra(pseudo));

		//récupération du bouton grâce à son ID
		buttonEnvoyer = (Button) findViewById(R.id.ButtonEnvoyer);

		//applique un listener d'évenement au clique sur le bouton
		buttonEnvoyer.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						//récupère le texte écrit dans l'EditText
						String pseudo = editText.getText().toString();
						Boolean erreurBoolean = false;

						if (pseudo != null){
							j.setPseudo(pseudo);
						}
						//vérifie si le pseudo et l'avatar sont saisis
						if ( erreurBoolean == false) {
							if (j.getAvatar() == null || j.getAvatar().equals("") ){
								Toast.makeText(ModifierJoueur.this,"Veuillez séléctionner un avatar", Toast.LENGTH_LONG).show();	
							}
							else if (j.getPseudo() == null || j.getPseudo().equals("") ){
								Toast.makeText(ModifierJoueur.this,"Veuillez saisir un pseudo", Toast.LENGTH_LONG).show();	
							}
							else {

								erreurBoolean = true;
							
								Toast.makeText(ModifierJoueur.this,"le joueur " + j.getPseudo() + " a bien été modifié ! ", Toast.LENGTH_LONG).show();				        
								finish();
							}
						}       
					}     		
				}
				);
	}
}
