package com.projetTDA.joueur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.projetTDA.metier.Joueur;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListerJoueurs extends ListActivity {

	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		Joueur ronaldo = new Joueur(1,"ronaldo","0");
		Joueur cafe = new Joueur(2,"cafe","7");
		
		Joueur zidane = new Joueur(3,"Zidane","6");
		Joueur petit = new Joueur(4,"petit","4");
		
		Joueur beckham = new Joueur(5,"beckham","5");
		Joueur owell = new Joueur(6,"owell","8");
		
		Joueur casillas = new Joueur(7,"casillas","2");
		Joueur raoul = new Joueur(8,"raoul","3");

		listeJoueur.add(ronaldo);
		listeJoueur.add(cafe);
		listeJoueur.add(zidane);
		listeJoueur.add(petit);
		listeJoueur.add(beckham);
		listeJoueur.add(owell);
		listeJoueur.add(casillas);
		listeJoueur.add(raoul);
		


		//boucle lecture avatar
//		int value;
//		try {
//			FileInputStream input = openFileInput(AVATARFile);
//			StringBuffer lu = new StringBuffer();
//
//			while((value = input.read()) != -1) {
//				if (value == '*' ){
//					avatarList.add(lu.toString());
//					System.out.println("lu="+lu.toString());
//					lu.delete(0,lu.length()); 
//					
//				}
//				else{
//				lu.append((char)value);
//				}
//				
//			}
//			if(input != null)
//				input.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//boucle lecture pseudo
//		int value2;
//		int compteur=0;
//		try {
//			FileInputStream input2 = openFileInput(PSEUDOFile);
//			StringBuffer lu = new StringBuffer();
//
//			while((value2 = input2.read()) != -1) {
//				if (value2 == '*' ){
//
//					pseudoList.add(lu.toString());
//					System.out.println("tab="+pseudoList.get(compteur));
//					System.out.println("lu2="+lu.toString());
//					lu.delete(0,lu.length()); 
//				}
//				else{
//				lu.append((char)value2);
//				}
//			}
//
//			if(input2 != null)
//				input2.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		setListAdapter(new JoueurArrayAdapterPseudo(this, pseudoList));
		
//		à garder
//		setListAdapter(new JoueurArrayAdapter(this, avatarList,pseudoList));
		
		
		setListAdapter(new JoueurArrayAdapter(this, listeJoueur));

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		try{
//			// Création du flux bufférisé sur un FileReader, immédiatement suivi par un 
//			// try/finally, ce qui permet de ne fermer le flux QUE s'il le reader
//			// est correctement instancié (évite les NullPointerException)
//			BufferedReader buff = new BufferedReader(new FileReader(AVATARFile));
//			try {
//			String line;
//			// Lecture du fichier ligne par ligne. Cette boucle se termine
//			// quand la méthode retourne la valeur null.
//			while ((line = buff.readLine()) != null) {
//			System.out.println(line);
//			avatarList.add(line);
//			System.out.println("avatarList="+avatarList.get(0));
//			System.out.println("avatarList1="+avatarList.get(1));
//			//faites ici votre traitement
//			}
//			} finally {
//			// dans tous les cas, on ferme nos flux
//			buff.close();
//			}
//			} catch (IOException ioe) {
//			// erreur de fermeture des flux
//			System.out.println("Erreur --" + ioe.toString());
//			}
//		setListAdapter(new JoueurArrayAdapter(this, avatarList));
//
}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	}

}

//	ListView vue = null;
//
//	private String PSEUDO = "pseudo.txt";
//	private String chaine ="";
//	ArrayList<String> avatar = new ArrayList<String>();
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//				setContentView(R.layout.lister_joueurs);
//
//		try {
//			FileInputStream input = openFileInput(PSEUDO);
//			int value;
//			// On utilise un StringBuffer pour construire la chaîne au fur et à mesure
//			StringBuffer lu = new StringBuffer();
//			// On lit les caractères les uns après les autres
//
//			while((value = input.read()) != -1) {
//				// On écrit dans le fichier le caractère lu
//				lu.append((char)value);
//				
//			}
//			Toast.makeText(ListerJoueurs.this,lu.toString(), Toast.LENGTH_SHORT).show();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}




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
