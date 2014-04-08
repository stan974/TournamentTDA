package com.projetTDA.joueur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListerJoueurs extends ListActivity {
	static final ArrayList<String> JOUEUR = 
			new ArrayList<String>(Arrays.asList("R.drawable.avatar1","R.drawable.avatar2","R.drawable.avatar3","R.drawable.avatar4","R.drawable.avatar5","R.drawable.avatar6","R.drawable.avatar7","R.drawable.avatar8","R.drawable.avatar9","R.drawable.avatar10"));
	String AVATARFile = "avatar.txt";
	ArrayList<String> avatarList = new ArrayList<String>();
	//	Integer[] mThumbIds = {
	//            R.drawable.avatar1, R.drawable.avatar2,
	//            R.drawable.avatar3, R.drawable.avatar4,
	//            R.drawable.avatar5, R.drawable.avatar6,
	//            R.drawable.avatar7, R.drawable.avatar8,
	//            R.drawable.avatar9, R.drawable.avatar10,
	//            R.drawable.avatar1, R.drawable.avatar2,
	//            R.drawable.avatar3, R.drawable.avatar4,
	//            R.drawable.avatar5, R.drawable.avatar6,
	//            R.drawable.avatar7, R.drawable.avatar8,
	//            R.drawable.avatar9, R.drawable.avatar10
	//    };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int value;


		try {
			FileInputStream input = openFileInput(AVATARFile);
			StringBuffer lu = new StringBuffer();

			while((value = input.read()) != -1) {
				// On écrit dans le fichier le caractère lu
				lu.append((char)value);
			}
		avatarList.add(lu.toString());
			System.out.println("lu="+lu.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setListAdapter(new JoueurArrayAdapter(this, avatarList));
		
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
