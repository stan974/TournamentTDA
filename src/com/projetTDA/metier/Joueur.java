package com.projetTDA.metier;

public class Joueur {
	
	
	private int id_joueur;
	private String pseudo;
	private String avatar;
	
	
	public Joueur(int id_joueur, String pseudo, String avatar) {
		super();
		this.id_joueur = id_joueur;
		this.pseudo = pseudo;
		this.avatar = avatar;
	}


	public Joueur() {
		super();
	}


	public int getId_joueur() {
		return id_joueur;
	}


	public void setId_joueur(int id_joueur) {
		this.id_joueur = id_joueur;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("|");
		sb.append(this.id_joueur);
		sb.append("|");
		sb.append(this.pseudo);
		sb.append("|");
		sb.append(this.avatar);
		
		
		return sb.toString();		
	}
	
}
