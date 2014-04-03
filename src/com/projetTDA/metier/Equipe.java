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
		this.liste_joueur = liste_joueur;
	}


	public Equipe() {
		super();
		this.liste_joueur=new ArrayList<Joueur>();
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
