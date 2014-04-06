package com.projetTDA.metier;
import java.util.ArrayList;
import java.util.HashMap;


public class Tournoi {
	
	private String nom_tournoi;
	private String date_creation;
	private String lib_sport;
	private HashMap<Integer, Equipe> classement;
	private ArrayList<Match> liste_matchs;
	

	public Tournoi(String nom_tournoi, String date_creation, String lib_sport,
			HashMap<Integer, Equipe> classement, ArrayList<Match> liste_matchs) {
		super();
		this.nom_tournoi = nom_tournoi;
		this.date_creation = date_creation;
		this.lib_sport = lib_sport;
		this.classement = classement;
		this.liste_matchs = liste_matchs;
	}

	public Tournoi() {
		super();
		
		this.liste_matchs=new ArrayList<Match>();
	}

	public String getNom_tournoi() {
		return nom_tournoi;
	}

	public void setNom_tournoi(String nom_tournoi) {
		this.nom_tournoi = nom_tournoi;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getLib_sport() {
		return lib_sport;
	}

	public void setLib_sport(String lib_sport) {
		this.lib_sport = lib_sport;
	}

	public HashMap<Integer, Equipe> getClassement() {
		return classement;
	}

	public void setClassement(HashMap<Integer, Equipe> classement) {
		this.classement = classement;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		//sb.append("|");
		sb.append(this.nom_tournoi);
		sb.append("\n");
		sb.append(this.date_creation);
		sb.append("\n");
		sb.append(this.lib_sport);
		sb.append("\n");
		sb.append("\n");
		sb.append("Classement:");
		sb.append(this.classement);
		sb.append("\n");
		sb.append("\n");
		sb.append(this.liste_matchs);

		
		return sb.toString();	
	}
	
}
