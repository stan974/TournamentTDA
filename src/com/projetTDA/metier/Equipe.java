package com.projetTDA.metier;
import java.util.ArrayList;
import java.util.List;


public class Equipe {

	private int id_equipe;
	private String nom_equipe;
	private List<Joueur> liste_joueur;
	
	
	public Equipe(int id_equipe, String nom_equipe, List<Joueur> liste_joueur) {
		super();
		this.id_equipe = id_equipe;
		this.nom_equipe = nom_equipe;
		if(liste_joueur != null)
		{
			this.liste_joueur = liste_joueur;
		}
		else
		{
			this.liste_joueur = new ArrayList<Joueur>();
		}
	}
	
	public Equipe(Equipe copy) {
		super();
		this.id_equipe = copy.id_equipe;
		this.nom_equipe = copy.nom_equipe;
		this.liste_joueur = new ArrayList<Joueur>(copy.liste_joueur);
	}

	public Equipe() {
		super();
		this.liste_joueur = new ArrayList<Joueur>();
	}


	public int getId_equipe() {
		return id_equipe;
	}


	public void setId_equipe(int id_equipe) {
		this.id_equipe = id_equipe;
	}


	public String getNom_equipe() {
		return nom_equipe;
	}


	public void setNom_equipe(String nom_equipe) {
		this.nom_equipe = nom_equipe;
	}	
	
	public List<Joueur> getListe_joueur() {
		return liste_joueur;
	}


	public void setListe_joueur(List<Joueur> liste_joueur) {
		this.liste_joueur = liste_joueur;
	}


	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("|");
		sb.append(this.id_equipe);
		sb.append("|");
		sb.append(this.nom_equipe);

		for (Joueur j : liste_joueur)			
		{
			sb.append("\n\t").append(j);
		}
		
		return sb.toString();		
	}	
	
}
