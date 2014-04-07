package com.projetTDA.joueur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.projetTDA.tournamentbuilder.R;

public class ListerJoueurs extends ActionBarActivity {
	ListView vue = null;

	private String PSEUDO = "pseudo.txt";
	private String chaine ="";
	ArrayList<String> avatar = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				setContentView(R.layout.lister_joueurs);

		try {
			FileInputStream input = openFileInput(PSEUDO);
			int value;
			// On utilise un StringBuffer pour construire la chaîne au fur et à mesure
			StringBuffer lu = new StringBuffer();
			// On lit les caractères les uns après les autres

			while((value = input.read()) != -1) {
				// On écrit dans le fichier le caractère lu
				lu.append((char)value);
				
			}
			Toast.makeText(ListerJoueurs.this,lu.toString(), Toast.LENGTH_SHORT).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
		
		
		
		
//				// Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
//				// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
//				// est correctement instancié (évite les NullPointerException)
//				try{
//					InputStream ips=new FileInputStream(PSEUDO); 
//					InputStreamReader ipsr=new InputStreamReader(ips);
//					BufferedReader br=new BufferedReader(ipsr);
//					String ligne;
//					while ((ligne=br.readLine())!=null){
//						System.out.println(ligne);
//						avatar.add(ligne);
//						chaine+=ligne+"\n";
//					}
//					br.close(); 
//				}		
//				catch (Exception e){
//					System.out.println(e.toString());
//			}
//	
//			//			Toast.makeText(ListerJoueurs.this, lu.toString(), Toast.LENGTH_SHORT).show();
//			//			System.out.println(lu);
//			//
//			//			
//			
//			setListAdapter(new JoueurArrayAdapter(this, avatar));
//			//	}
//	
//	
//			//	static final String[] MOBILE_OS = 
//			//            new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
//			//
//			//	@Override
//			//	public void onCreate(Bundle savedInstanceState) {
//			//		super.onCreate(savedInstanceState);
//	
//			//		setListAdapter(new JoueurArrayAdapter(this, MOBILE_OS));
//	
//		}
//	
//		@Override
//		protected void onListItemClick(ListView l, View v, int position, long id) {
//	
//			//get selected items
//			String selectedValue = (String) getListAdapter().getItem(position);
//			Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
//	
//		}
//	
//	}
