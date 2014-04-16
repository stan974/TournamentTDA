package com.projetTDA.bdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.projetTDA.metier.Equipe;
import com.projetTDA.metier.Joueur;
import com.projetTDA.metier.Match;
import com.projetTDA.metier.Tournoi;
import com.projetTDA.DataBaseHelper;

/**
 * Couche d'accès aux données. Propose des méthodes pour interagir avec la base de données.
 * @author Guilou & Lio
 *
 */
public class DataAccessLayer {
		
	/* ---------------------------------------------------------- */
	/*                        ATTRIBUTS                           */
	/* ---------------------------------------------------------- */

	/**
	 * DataBase Helper
	 */
	private DataBaseHelper myDataBaseHelper = null;	
	
	/**
	 * L'activité appelante
	 */
	private Context myContext = null;
	
	/* ---------------------------------------------------------- */
	/*                        CONSTRUCTEUR                        */
	/* ---------------------------------------------------------- */
	
	/**
	 * Constructeur.
	 * @param context l'activité appelante
	 */
	public DataAccessLayer(Context context) {
		
		// On crée un DataBaseHelper
		myContext = context;
		myDataBaseHelper = new DataBaseHelper(myContext);
		
		// On crée la base de donnée si elle n'existe pas
		try {
			  
			myDataBaseHelper.createDataBase();
			  
		} catch (IOException ioe) {
			  
			  throw new Error("Enable to create database");
			  
		}
		
		// On ouvre la base de donnée (en READ & WRITE)
		try {
			  
			myDataBaseHelper.openDataBase();
			
		} catch (SQLException sqle) {
			  
			  throw sqle;
			  
		}
		
	}
	
	/* ---------------------------------------------------------- */
	/*                          METHODES                          */
	/* ---------------------------------------------------------- */
	
		/* ------ */
		/* JOUEUR */
		/* ------ */
	
	/**
	 * Ajoute un joueur à la table JOUEUR de la base de données.
	 * @param joueur le joueur à ajouter
	 * @return le joueur inséré en base ou null si erreur pendant insertion
	 */
	public Joueur ajouterJoueurBDD(Joueur joueur) {
		
		// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
		ContentValues values = new ContentValues();
		// On ajoute les paires au conteneur
		values.put(DataBaseHelper.JOUEUR_COL_PSEUDO, joueur.getPseudo());
		values.put(DataBaseHelper.JOUEUR_COL_AVATAR, joueur.getAvatar());

		// On ajoute la joueur à la table JOUEUR
		long ret = myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_JOUEUR, null, values);
		
		if(ret < 0) return null;
		
		int id_nouveau = getListeJoueurs().get(getListeJoueurs().size() - 1).getId_joueur();
		
		return new Joueur(id_nouveau, joueur.getPseudo(), joueur.getAvatar());
		
	}
	
	/**
	 * Met à jour un joueur dans la table JOUEUR de la base de données.
	 * @param joueur le joueur avec ses nouvelles valeurs
	 * @return le code retour : positif OK , négatif KO
	 */
	public int majJoueurBDD(Joueur joueur) {
		
		// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
		ContentValues values = new ContentValues();
		// On ajoute les paires au conteneur
		values.put(DataBaseHelper.JOUEUR_COL_PSEUDO, joueur.getPseudo());
		values.put(DataBaseHelper.JOUEUR_COL_AVATAR, joueur.getAvatar());

		// On met à jour tous les champs à jour sauf l'identifiant pour le joueur concerné
		return myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_JOUEUR, values, DataBaseHelper.JOUEUR_COL_ID + " = " + joueur.getId_joueur(), null);
		
	}
	
	/**
	 * Supprime un joueur de la table JOUEUR de la base de données.
	 * @param id_joueur l'identifiant du joueur à supprimer
	 * @return le code retour : positif OK , négatif KO
	 */
	public int supprimerJoueurBDD(int id_joueur) {
		
		// On supprime le joueur qui correspont à l'identifiant en paramètre
		return myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_JOUEUR, DataBaseHelper.JOUEUR_COL_ID + " = " + id_joueur, null);
		
	}
	
	/**
	 * Retourne la liste de tous les joueurs.
	 * @return la liste contenant tous les joueurs ou null si la requête ne remonte aucune ligne ou échoue
	 */
	public ArrayList<Joueur> getListeJoueurs() {
		
		// Variable à retourner
		ArrayList<Joueur> toReturn = new ArrayList<Joueur>();
		
		// On fait une requête sur tous les champs de la table JOUEUR
		// La réponse est contenue dans le curseur
		Cursor c = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_JOUEUR, new String[] {DataBaseHelper.JOUEUR_COL_ID,DataBaseHelper.JOUEUR_COL_PSEUDO,DataBaseHelper.JOUEUR_COL_AVATAR}, null, null, null, null, null);
		
		// La requête a t-elle réussi ?
		if(c != null)
		{
			// La requête remonte t-elle des lignes ?
			if(c.getCount() == 0)
			{
				return null;
			}
			else
			{
				// On se place sur la premier élément du curseur
				c.moveToFirst();
				// On crée un nouvel objet avec les informations de l'élément du curseur
				Joueur first = new Joueur(c.getInt(0), c.getString(1), c.getString(2));
				// On ajoute le premier objet à la liste à retourner 
				toReturn.add(first);
				
				// Tant qu'il y reste des éléments dans le curseur ...
				while(c.moveToNext())
				{
					// On crée un nouvel objet avec les informations de l'élément en cours du curseur
					// On ajoute l'objet créé à la liste à retourner
					toReturn.add(new Joueur(c.getInt(0), c.getString(1), c.getString(2)));
				}
			}
		}
		else
		{
			return null;
		}
		
		return toReturn;
		
	}
		/* ------ */
		/* EQUIPE */
		/* ------ */
	
	/**
	 * Ajoute une équipe à la table EQUIPE de la base de données.
	 * Ajoute aussi une ligne dans la table EQUIPE_JOUEUR.
	 * @param equipe l'équipe à ajouter
	 * @return l'équipe insérée en base ou null si erreur pendant insertion
	 */
	public Equipe ajouterEquipeBDD(Equipe equipe) {
		
		// L'identifiant de l'éqiupe qu'on va créer
		int id_equipe_creee;
		// On crée un conteneur pour la paire ( nom_colonne , valeur_du_champ )
		ContentValues valuesEquipe = new ContentValues();
		// On ajoute la paire au conteneur
		valuesEquipe.put(DataBaseHelper.EQUIPE_COL_NOM, equipe.getNom_equipe());
		
		// On ajoute l'équipe à la base de données
		myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_EQUIPE, null, valuesEquipe);
		
		// On recherche l'identifiant de l'équipe créée
		// Il correspond au plus grand identifiant de la table EQUIPE
		Cursor c = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_EQUIPE, new String[] {DataBaseHelper.EQUIPE_COL_ID}, null, null, null, null, DataBaseHelper.EQUIPE_COL_ID);
		
		// La requête a t-elle réussi ?
		if(c != null)
		{
			// La requête remonte t-elle des lignes ?
			if(c.getCount() == 0)
			{
				return null;
			}
			else
			{
				// On récupère le dernier identifiant (le plus grand)
				c.moveToLast();
				// C'est l'identifiant de l'équipe crée
				id_equipe_creee = c.getInt(0);
			}
		}
		else
		{
			return null;
		}
			
		// Pour chaque joueur de l'équipe on ajoute une ligne à la table EQUIPE_JOUEUR
		for(Joueur j : equipe.getListe_joueur())
		{
			// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
			ContentValues valuesEquipeJoueur = new ContentValues();
			// On ajoute les paires au conteneur
			valuesEquipeJoueur.put(DataBaseHelper.EQUIPE_JOUEUR_COL_ID_EQUIPE, id_equipe_creee);
			valuesEquipeJoueur.put(DataBaseHelper.EQUIPE_JOUEUR_COL_ID_JOUEUR, j.getId_joueur());
			
			// On ajoute une ligne à la table EQUIPE_JOUEUR
			myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_EQUIPE_JOUEUR, null, valuesEquipeJoueur);
		}

		equipe.setId_equipe(id_equipe_creee);
		
		return equipe;
		
	}
	
	/**
	 * Met à jour une équipe dans la table EQUIPE de la base de données.
	 * @param equipe l'équipe avec ses nouvelles valeurs
	 * @return le code retour : positif OK , négatif KO
	 */
	public int majEquipeBDD(Equipe equipe) {
		
		// On crée un conteneur pour la paire ( nom_colonne , valeur_du_champ )
		ContentValues values = new ContentValues();
		// On ajoute la paire au conteneur
		values.put(DataBaseHelper.EQUIPE_COL_NOM, equipe.getNom_equipe());

		// On met à jour tous les champs à jour sauf l'identifiant pour l'équipe concernée
		return myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_EQUIPE, values, DataBaseHelper.EQUIPE_COL_ID + " = " + equipe.getId_equipe(), null);
		
	}
	
	/**
	 * Supprime une équipe de la table EQUIPE de la base de données.
	 * Supprime aussi toutes les lignes de la table EQUIPE_JOUEUR qui sont concernées par l'équipe à supprimer.
	 * @param id_equipe l'identifiant de l'équipe à supprimer
	 * @return 0 OK, -1 suppression échouée (EQUIPE), -2 suppression échouée (EQUIPE_JOUEUR)
	 */
	public int supprimerEquipeBDD(int id_equipe) {
		
		int ret1 = myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_EQUIPE, DataBaseHelper.EQUIPE_COL_ID + " = " + id_equipe, null);
		int ret2 = myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_EQUIPE_JOUEUR, DataBaseHelper.EQUIPE_JOUEUR_COL_ID_EQUIPE + " = " + id_equipe, null);
		
		if(ret1 < 0) return -1;
		if(ret2 < 0) return -2;
		
		return 0;
		
	}

	public List<Equipe> getListeEquipes() {
		
		ArrayList<Equipe> toReturn = new ArrayList<Equipe>();
		
		Cursor c1 = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_EQUIPE, new String[] {DataBaseHelper.EQUIPE_COL_ID,DataBaseHelper.EQUIPE_COL_NOM}, null, null, null, null, null);
		
		if(c1 == null)
		{
			return null;
		}
		
		if(c1.getCount() == 0)
		{
			return null;
		}
		
		c1.moveToFirst();
		Equipe first = new Equipe(c1.getInt(0), c1.getString(1), null);
		toReturn.add(first);
		
		while(c1.moveToNext())
		{
			toReturn.add(new Equipe(c1.getInt(0), c1.getString(1), null));
		}
		
		Cursor c2 = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_EQUIPE_JOUEUR, new String[] {DataBaseHelper.EQUIPE_JOUEUR_COL_ID_EQUIPE,DataBaseHelper.EQUIPE_JOUEUR_COL_ID_JOUEUR}, null, null, null, null, null);
		
		if(c2 == null)
		{

			return null;
		}
			
		if(c2.getCount() == 0)
		{
			return null;
		}
		
		List<Joueur> listeJoueur = getListeJoueurs();
				
		if(listeJoueur == null)
		{
			return null;
		}
		
		c2.moveToFirst();
		
		for(Equipe e : toReturn)
		{
			if(e.getId_equipe() == c2.getInt(0))
			{				
				for(Joueur j : listeJoueur)
				{
					if(j.getId_joueur() == c2.getInt(1))
					{
						
						e.getListe_joueur().add(j);
					}
				}
			}
		}
		
		while(c2.moveToNext())
		{
			for(Equipe e : toReturn)
			{
				if(e.getId_equipe() == c2.getInt(0))
				{
					for(Joueur j : listeJoueur)
					{
						if(j.getId_joueur() == c2.getInt(1))
						{
							e.getListe_joueur().add(j);
						}
					}
				}				
			}
		}
		
		return toReturn;
		
	}
	
		/* -------- */
		/* TOURNOIS */
		/* -------- */

	public Tournoi ajouterTournoiBDD(Tournoi tournoi) {
	
		// L'identifiant du tournoi qu'on va créer
		int id_tournoi_cree;
		// On crée un conteneur pour la paire ( nom_colonne , valeur_du_champ )
		ContentValues valuesTournoi = new ContentValues();
		// On ajoute la paire au conteneur
		valuesTournoi.put(DataBaseHelper.TOURNOI_COL_NOM, tournoi.getNom_tournoi());
		valuesTournoi.put(DataBaseHelper.TOURNOI_COL_SPORT, tournoi.getLib_sport());
		valuesTournoi.put(DataBaseHelper.TOURNOI_COL_DATE, tournoi.getDate_creation());
		valuesTournoi.put(DataBaseHelper.TOURNOI_COL_CHAMPIONNAT, tournoi.getIs_championnat());
		
		// On ajoute le tournoi à la base de données
		myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_TOURNOI, null, valuesTournoi);
		
		// On recherche l'identifiant du tournoi créé
		// Il correspond au plus grand identifiant de la table TOURNOIS
		Cursor c = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_TOURNOI, new String[] {DataBaseHelper.TOURNOI_COL_ID}, null, null, null, null, DataBaseHelper.TOURNOI_COL_ID);
		
		// La requête a t-elle réussi ?
		if(c != null)
		{
			// La requête remonte t-elle des lignes ?
			if(c.getCount() == 0)
			{
				return null;
			}
			else
			{
				// On récupère le dernier identifiant (le plus grand)
				c.moveToLast();
				// C'est l'identifiant de l'équipe crée
				id_tournoi_cree = c.getInt(0);
				tournoi.setId_tournoi(id_tournoi_cree);
			}
		}
		else
		{
			return null;
		}
			
		// Pour chaque équipe du tournoi on ajoute une ligne à la table CLASSEMENT
		for(Equipe e : tournoi.getClassement().keySet())
		{
			// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
			ContentValues valuesClassement = new ContentValues();
			// On ajoute les paires au conteneur
			valuesClassement.put(DataBaseHelper.CLASSEMENT_COL_ID_TOURNOI, id_tournoi_cree);
			valuesClassement.put(DataBaseHelper.CLASSEMENT_COL_ID_EQUIPE, e.getId_equipe());
			valuesClassement.put(DataBaseHelper.CLASSEMENT_COL_POINTS, 0);
			
			// On ajoute une ligne à la table CLASSEMENT
			myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_CLASSEMENT, null, valuesClassement);
		}
		
		// Pour chaque matchs du tournoi on ajoute une ligne à la table MATCH
		for(Match m : tournoi.getListe_matchs())
		{
			// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
			ContentValues valuesMatch = new ContentValues();
			// On ajoute les paires au conteneur
			valuesMatch.put(DataBaseHelper.MATCH_COL_ID_TOURNOI, id_tournoi_cree);
			valuesMatch.put(DataBaseHelper.MATCH_COL_ID_EQUIPE_1, m.getEquipe1().getId_equipe());
			valuesMatch.put(DataBaseHelper.MATCH_COL_ID_EQUIPE_2, m.getEquipe2().getId_equipe());
			
			// On ajoute une ligne à la table MATCH
			myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_MATCH, null, valuesMatch);
		}
	
		return tournoi;
	
	}
	
	public int majTournoiBDD(Tournoi tournoi) {
		
		// On crée un conteneur pour la paire ( nom_colonne , valeur_du_champ )
		ContentValues values = new ContentValues();
		// On ajoute la paire au conteneur
		values.put(DataBaseHelper.TOURNOI_COL_NOM, tournoi.getNom_tournoi());
		values.put(DataBaseHelper.TOURNOI_COL_SPORT, tournoi.getLib_sport());

		// On met à jour le nom et le libellé du sport du tournoi concerné
		return myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_TOURNOI, values, DataBaseHelper.TOURNOI_COL_ID + " = " + tournoi.getId_tournoi(), null);
		
	}
	
	public int majMatchBDD(Tournoi tournoi)
	{	
		int ret = 0;
		
		for(Match m : tournoi.getListe_matchs())
		{
			ContentValues valuesMatch = new ContentValues();
			
			valuesMatch.put(DataBaseHelper.MATCH_COL_SCORE_EQUIPE_1, m.getScore1());
			valuesMatch.put(DataBaseHelper.MATCH_COL_SCORE_EQUIPE_2, m.getScore2());
			
			myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_MATCH, valuesMatch, DataBaseHelper.MATCH_COL_ID_TOURNOI + " = " + tournoi.getId_tournoi() + " AND " + DataBaseHelper.MATCH_COL_ID_EQUIPE_2 + " = " + m.getEquipe2().getId_equipe() + " AND " + DataBaseHelper.MATCH_COL_ID_EQUIPE_1 + " = " + m.getEquipe1().getId_equipe(), null);
			
			//
			
			int ajout1 = 0;
			int ajout2 = 0;
			
			if(m.getScore1() > m.getScore2()) {ajout1 = 3; ajout2 = 0;}
			else if(m.getScore1() == m.getScore2()) {ajout1 = 1; ajout2 = 1;}
			else {ajout1 = 0; ajout2 = 3;}			
			
			ContentValues valuesClassement1 = new ContentValues();
			valuesClassement1.put(DataBaseHelper.CLASSEMENT_COL_POINTS, tournoi.getClassement().get(m.getEquipe1()).intValue() + ajout1);
			
			ContentValues valuesClassement2 = new ContentValues();
			valuesClassement2.put(DataBaseHelper.CLASSEMENT_COL_POINTS, tournoi.getClassement().get(m.getEquipe2()).intValue() + ajout2);
						 
			myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_CLASSEMENT, valuesClassement1, DataBaseHelper.CLASSEMENT_COL_ID_TOURNOI + " = " + tournoi.getId_tournoi() + " AND " + DataBaseHelper.CLASSEMENT_COL_ID_EQUIPE + " = " + m.getEquipe1().getId_equipe(), null);
			myDataBaseHelper.getMyDataBase().update(DataBaseHelper.TABLE_CLASSEMENT, valuesClassement2, DataBaseHelper.CLASSEMENT_COL_ID_TOURNOI + " = " + tournoi.getId_tournoi() + " AND " + DataBaseHelper.CLASSEMENT_COL_ID_EQUIPE + " = " + m.getEquipe2().getId_equipe(), null);
		}
		
		return ret;
		
	}
	
	public int supprimerTournoiBDD(int id_tournoi) {
		
		int ret1 = myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_TOURNOI, DataBaseHelper.TOURNOI_COL_ID + " = " + id_tournoi, null);
		int ret2 = myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_CLASSEMENT, DataBaseHelper.CLASSEMENT_COL_ID_TOURNOI + " = " + id_tournoi, null);
		int ret3 = myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_MATCH, DataBaseHelper.MATCH_COL_ID_TOURNOI + " = " + id_tournoi, null);
		
		if(ret1 < 0) return -1;
		if(ret2 < 0) return -2;
		if(ret3 < 0) return -3;
		
		return 0;
		
	}
	
	public List<Tournoi> getListeTournoi() {
		
		ArrayList<Tournoi> toReturn = new ArrayList<Tournoi>();
		
		Cursor c1 = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_TOURNOI, new String[] {DataBaseHelper.TOURNOI_COL_ID,DataBaseHelper.TOURNOI_COL_NOM,DataBaseHelper.TOURNOI_COL_SPORT,DataBaseHelper.TOURNOI_COL_DATE,DataBaseHelper.TOURNOI_COL_CHAMPIONNAT}, null, null, null, null, null);
		
		if(c1 == null)
		{
			return null;
		}
		
		if(c1.getCount() == 0)
		{
			return null;
		}
		
		c1.moveToFirst();
		Tournoi first = new Tournoi();
		first.setId_tournoi(c1.getInt(0));
		first.setNom_tournoi(c1.getString(1));
		first.setLib_sport(c1.getString(2));
		first.setDate_creation(c1.getString(3));
		Boolean bool = (c1.getInt(4) != 0);
		first.setIs_championnat(bool);
		
		toReturn.add(first);
		
		while(c1.moveToNext())
		{
			Tournoi others = new Tournoi();
			others.setId_tournoi(c1.getInt(0));
			others.setNom_tournoi(c1.getString(1));
			others.setLib_sport(c1.getString(2));
			others.setDate_creation(c1.getString(3));
			bool = (c1.getInt(4) != 0);
			others.setIs_championnat(bool);
			
			toReturn.add(others);
		}
		
		Cursor c2 = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_CLASSEMENT, new String[] {DataBaseHelper.CLASSEMENT_COL_ID_TOURNOI,DataBaseHelper.CLASSEMENT_COL_ID_EQUIPE,DataBaseHelper.CLASSEMENT_COL_POINTS}, null, null, null, null, DataBaseHelper.CLASSEMENT_COL_POINTS + " DESC");
		
		if(c2 == null)
		{

			return null;
		}
			
		if(c2.getCount() == 0)
		{
			return null;
		}
		
		List<Equipe> listeEquipe = getListeEquipes();
				
		if(listeEquipe == null)
		{
			return null;
		}
		
		c2.moveToFirst();
		
		for(Tournoi t : toReturn)
		{
			if(t.getId_tournoi() == c2.getInt(0))
			{				
				for(Equipe e : listeEquipe)
				{
					if(e.getId_equipe() == c2.getInt(1))
					{						
						t.getClassement().put(e, c2.getInt(2));
					}
				}
			}
		}
		
		while(c2.moveToNext())
		{
			for(Tournoi t : toReturn)
			{
				if(t.getId_tournoi() == c2.getInt(0))
				{				
					for(Equipe e : listeEquipe)
					{
						if(e.getId_equipe() == c2.getInt(1))
						{						
							t.getClassement().put(e, c2.getInt(2));
						}
					}
				}
			}
		}
		
		Cursor c3 = myDataBaseHelper.getMyDataBase().query(DataBaseHelper.TABLE_MATCH, new String[] {DataBaseHelper.MATCH_COL_ID_TOURNOI,DataBaseHelper.MATCH_COL_ID_EQUIPE_1,DataBaseHelper.MATCH_COL_ID_EQUIPE_2,DataBaseHelper.MATCH_COL_SCORE_EQUIPE_1,DataBaseHelper.MATCH_COL_SCORE_EQUIPE_2,DataBaseHelper.MATCH_COL_RANG}, null, null, null, null, null);
		
		if(c3 == null)
		{

			return null;
		}
			
		if(c3.getCount() == 0)
		{
			return null;
		}
		
		c3.moveToFirst();
		
		for(Tournoi t : toReturn)
		{
			if(t.getId_tournoi() == c3.getInt(0))
			{	
				Equipe e1 = null;
				Equipe e2 = null;
				
				for(Equipe e : listeEquipe)
				{
					if(e.getId_equipe() == c3.getInt(1))
					{
						e1 = e;
					}
					else if(e.getId_equipe() == c3.getInt(2))
					{
						e2 = e;
					}
				}
				
				if(e1 == null || e2 == null)
				{
					return null;
				}
				
				t.getListe_matchs().add(new Match(e1, e2, c3.getInt(3), c3.getInt(4), c3.getInt(5)));
			}
		}
		
		while(c2.moveToNext())
		{
			for(Tournoi t : toReturn)
			{
				if(t.getId_tournoi() == c3.getInt(0))
				{	
					Equipe e1 = null;
					Equipe e2 = null;
					
					for(Equipe e : listeEquipe)
					{
						if(e.getId_equipe() == c3.getInt(1))
						{
							e1 = e;
						}
						else if(e.getId_equipe() == c3.getInt(2))
						{
							e2 = e;
						}
					}
					
					if(e1 == null || e2 == null)
					{
						return null;
					}
					
					t.getListe_matchs().add(new Match(e1, e2, c3.getInt(3), c3.getInt(4), c3.getInt(5)));
				}
			}
		}
		
		return toReturn;
		
	}
	
	public void clearBDD()
	{
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_JOUEUR, null, null);
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_EQUIPE, null, null);
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_EQUIPE_JOUEUR, null, null);
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_TOURNOI, null, null);
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_CLASSEMENT, null, null);
		myDataBaseHelper.getMyDataBase().delete(DataBaseHelper.TABLE_MATCH, null, null);
	}

}
