package com.projetTDA.metier;
import java.util.ArrayList;
import java.util.HashMap;


public class Tournoi {
	
	private int id_tournoi;
	private String nom_tournoi;
	private String date_creation;
	private String lib_sport;
	private Boolean is_championnat;
	private HashMap<Equipe, Integer> classement;
	private ArrayList<Match> liste_matchs;
	

	public Tournoi(int id_tournoi, String nom_tournoi, String date_creation, String lib_sport, Boolean is_championnat,
			HashMap<Equipe, Integer> classement, ArrayList<Match> liste_matchs) {
		super();
		this.id_tournoi = id_tournoi;
		this.nom_tournoi = nom_tournoi;
		this.date_creation = date_creation;
		this.lib_sport = lib_sport;
		this.is_championnat = is_championnat;
		this.classement = new HashMap<Equipe, Integer>(classement);
		this.liste_matchs = new ArrayList<Match>(liste_matchs);
	}

	public Tournoi() {
		super();
		this.liste_matchs = new ArrayList<Match>();
		this.classement = new HashMap<Equipe, Integer>();
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

	public HashMap<Equipe, Integer> getClassement() {
		return classement;
	}

	public void setClassement(HashMap<Equipe, Integer> classement) {
		this.classement = classement;
	}
	
	public ArrayList<Match> getListe_matchs() {
		return liste_matchs;
	}

	public void setListe_matchs(ArrayList<Match> liste_matchs) {
		this.liste_matchs = liste_matchs;
	}

	public int getId_tournoi() {
		return id_tournoi;
	}

	public Boolean getIs_championnat() {
		return is_championnat;
	}

	public void setIs_championnat(Boolean is_championnat) {
		this.is_championnat = is_championnat;
	}

	public void setId_tournoi(int id_tournoi) {
		this.id_tournoi = id_tournoi;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.id_tournoi);
		sb.append("\n");
		sb.append(this.nom_tournoi);
		sb.append("\n");
		sb.append(this.date_creation);
		sb.append("\n");
		sb.append(this.lib_sport);
		sb.append("\n");
		sb.append(this.is_championnat);
		sb.append("\n");
		sb.append("\n");
		sb.append("Classement :\n");
		for(Equipe equipe : classement.keySet())
		{
			sb.append(classement.get(equipe)).append(" point(s) - ").append(equipe.getNom_equipe()).append("\n");
		}
		sb.append("\n");
		sb.append("\n");
		sb.append("Matchs :\n\n");
		for(Match m : liste_matchs)
		{
			sb.append(m.getEquipe1().getNom_equipe()).append(" ").append(m.getScore1());
			sb.append(" - ");
			sb.append(m.getScore2()).append(" ").append(m.getEquipe2().getNom_equipe());
		}
		
		return sb.toString();	
	}
	
}
