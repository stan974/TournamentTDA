package com.projetTDA.joueur;




import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.projetTDA.DataBaseHelper;
import com.projetTDA.bdd.DataAccessLayer;
import com.projetTDA.metier.Joueur;
import com.projetTDA.tournamentbuilder.R;

public class CreerJoueur extends Activity {

	private EditText editText;
	private Button buttonEnvoyer;
	
	Joueur j = new Joueur();
	int bool=1;
	//permet de simuler une activation ou désactivation d'icone
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creer_joueur);
		

		ImageAdapter imageAdapter = new ImageAdapter(this);
		
		final GridView gridview = (GridView) findViewById(R.id.avatarView);
		gridview.setAdapter(imageAdapter);
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

//				if (bool % 2 == 0 ){
//					v.setBackgroundResource(R.drawable.bg_vide);
//					bool+=1;
//				}
//				else{
					v.setBackgroundResource(R.drawable.bg);
					bool+=1;
//				}
				
				j.setAvatar(String.valueOf(position)); //assigne la position de l'avatar à la variable avatar
			}
		});

		//récupération de l'EditText grâce à son ID
		editText = (EditText) findViewById(R.id.EditTextPseudo);

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
								Toast.makeText(CreerJoueur.this,"Veuillez séléctionner un avatar", Toast.LENGTH_LONG).show();	
							}
							else if (j.getPseudo() == null || j.getPseudo().equals("") ){
								Toast.makeText(CreerJoueur.this,"Veuillez saisir un pseudo", Toast.LENGTH_LONG).show();	
							}
							else {

								erreurBoolean = true;
								  DataBaseHelper myDB = new DataBaseHelper(v.getContext());
								  
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
								  
								DataAccessLayer myDAL = new DataAccessLayer(v.getContext());
								myDAL.ajouterJoueurBDD(j);
								List<Joueur> listeJoueur = myDAL.getListeJoueurs();
								System.out.println(listeJoueur.get(0));
								System.out.println(listeJoueur.get(1));
								Toast.makeText(v.getContext(),"le joueur " + j.getPseudo() + " a bien été créé ! ", Toast.LENGTH_LONG).show();
								
								finish();
							}
						}       
					}     		
				}
				);
	}
}
