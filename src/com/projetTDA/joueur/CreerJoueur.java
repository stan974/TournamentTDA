package com.projetTDA.joueur;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class CreerJoueur extends ActionBarActivity {

	private EditText editText;
	private Button buttonEnvoyer;
	private String PSEUDO = "pseudo.txt";

	Joueur j = new Joueur();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creer_joueur);
		
		ImageAdapter imageAdapter = new ImageAdapter(this);
		
		GridView gridview = (GridView) findViewById(R.id.avatarView);
		gridview.setAdapter(imageAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				position = position+1;
				Toast.makeText(CreerJoueur.this, "l'avatar " + position + " est selectionné", Toast.LENGTH_SHORT).show();
				j.setAvatar(String.valueOf(position)); //assigne la position de l'avatar à la variable avatar
				position = position-1;

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
								//écrit le joueur dans un fichier
								FileOutputStream output = null;        

								try {
									//deleteFile(PSEUDO); //à décocher quand on a besoin de supprimer le fichier local
									output = openFileOutput(PSEUDO, MODE_APPEND);
									output.write(j.getPseudo().getBytes()); //écrit int
									output.write(",".getBytes());
									output.write(j.getAvatar().getBytes());
									output.write("\r\n".getBytes());

									if(output != null)

										output.close();
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}

								Toast.makeText(CreerJoueur.this,"le joueur " + j.getPseudo() + " a bien été créé ! ", Toast.LENGTH_LONG).show();				        
								finish();
							}
						}       
					}     		
				}
				);
	}
}