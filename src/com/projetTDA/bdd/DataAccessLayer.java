package com.projetTDA.bdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.projetTDA.DataBaseHelper;
import com.projetTDA.metier.Equipe;
import com.projetTDA.metier.Joueur;

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
	 * @return le code retour : positif OK , négatif KO
	 */
	public long ajouterJoueurBDD(Joueur joueur) {
		
		// On crée un conteneur pour les paires ( nom_colonne , valeur_du_champ )
		ContentValues values = new ContentValues();
		// On ajoute les paires au conteneur
		values.put(DataBaseHelper.JOUEUR_COL_PSEUDO, joueur.getPseudo());
		values.put(DataBaseHelper.JOUEUR_COL_AVATAR, joueur.getAvatar());

		// On ajoute la joueur à la table JOUEUR
		return myDataBaseHelper.getMyDataBase().insert(DataBaseHelper.TABLE_JOUEUR, null, values);
		
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
	 * @return le code retour : -2 requête échouée, -1 aucune ligne remontée, 0 OK
	 */
	public long ajouterEquipeBDD(Equipe equipe) {
		
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
				return -1;
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
			return -2;
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

		return 0;
		
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

}
