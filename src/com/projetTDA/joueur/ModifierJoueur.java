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

import com.projetTDA.R;
import com.projetTDA.bdd.DataAccessLayer;
import com.projetTDA.metier.Joueur;

public class ModifierJoueur extends ActionBarActivity {

	private EditText editText;
	private Button buttonEnvoyer;

	Joueur j = new Joueur();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifier_joueur);
		Intent intent2 = getIntent();
Button imageButton;
		j.setAvatar(intent2.getStringExtra("avatar"));
		int avatar2 = Integer.parseInt(j.getAvatar());
		int identifiant = Integer.parseInt(intent2.getStringExtra("idJoueur"));
		j.setId_joueur(identifiant);
		
		GridView gridview = (GridView) findViewById(R.id.avatarView);

//		System.out.println("avatar2 = "+avatar2);
//		System.out.println("j.getAvatar = "+j.getAvatar());

		gridview.setAdapter(new ImageAdapter(this));
//		gridview.setSelection(2);
//		gridview.requestFocusFromTouch();
//		gridview.setSelection(2);
		System.out.println("count :: "+gridview.getCount());
		gridview.setDrawSelectorOnTop(false);
//		imageButton = (Button) findViewById(R.id.imageButtonSelector);
//		View myAvatar = new View(this);
//		myAvatar = gridview.getChildAt(gridview.getChildCount());
//		Button myAvatar = (Button) findViewById(getResources().getIdentifier("button_normal_green", "drawable", getPackageName()));
//		if (imageButton != null)
//			imageButton.performClick();
//		else
//		System.out.println("NULL !!!");
		//ImageView myAvatar = (ImageView) getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()));
//		ImageView myAvatar = gridview.getChildAt(avatar2);
//		System.out.println("id drawable = "+getResources().getIdentifier(name, "drawable", getPackageName()));
//		 ImageView myAvatar = (ImageView) findViewById(getResources().getIdentifier(name, "drawable", getPackageName()));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				position=position+1;
				j.setAvatar(String.valueOf(position)); //assigne la position de l'avatar à la variable avatar
				position=position-1;
//				System.out.println("avatarModifié1="+j.getAvatar());
//				
			}
		});

//		imageButton.setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						System.out.println("clikééééé");
//					}
//					});
		
		//récupération de l'EditText grâce à son ID
		editText = (EditText) findViewById(R.id.EditTextPseudo);
		editText.setText(intent2.getStringExtra("pseudo"));

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
							System.out.println("PseudoModifié1="+j.getPseudo());
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
								DataAccessLayer myDAL = new DataAccessLayer(v.getContext());
//								idJoueur = j.getId_joueur();
								myDAL.majJoueurBDD(j);
								System.out.println("pseudoModifié="+j.getPseudo());
								System.out.println("avatarModifié="+j.getAvatar());
								System.out.println("idModifié="+j.getId_joueur());
								Toast.makeText(ModifierJoueur.this,"le joueur " + j.getPseudo() + " a bien été modifié ! ", Toast.LENGTH_LONG).show();				        
								finish();
								//recharge l'activité lister Joueur
								Intent acti = new Intent(ModifierJoueur.this, ListerJoueurs.class);
								startActivity(acti);
							}
						}       
					}     		
				}
				);

	}
}
