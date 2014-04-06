package com.projetTDA.metier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;


public class Tournoi {
	
	private String nom_tournoi;
	private String date_creation;
	private String lib_sport;
	private HashMap<Equipe,Integer> classement;
	private ArrayList<Match> liste_matchs;
	

	public Tournoi(String nom_tournoi, String date_creation, String lib_sport,
			HashMap<Equipe,Integer> classement, ArrayList<Match> liste_matchs) {
		super();
		this.nom_tournoi = nom_tournoi;
		this.date_creation = date_creation;
		this.lib_sport = lib_sport;
		this.classement = classement;
		this.liste_matchs = liste_matchs;
	}

	public Tournoi(String nom_tournoi, String date_creation, String lib_sport,
			HashMap<Equipe,Integer> classement) {
		super();
		this.nom_tournoi = nom_tournoi;
		this.date_creation = date_creation;
		this.lib_sport = lib_sport;
		this.classement = classement;
		this.liste_matchs = new ArrayList<Match>();
	}
	
	public Tournoi(String nom_tournoi, String date_creation, String lib_sport) {
		super();
		this.nom_tournoi = nom_tournoi;
		this.date_creation = date_creation;
		this.lib_sport = lib_sport;
		this.classement = new HashMap<Equipe,Integer>();
		this.liste_matchs = new ArrayList<Match>();
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


	public HashMap<Equipe,Integer> getClassement() {
		return classement;
	}


	public void setClassement(HashMap<Equipe,Integer> classement) {
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
	
	
	public void ajouterEquipe(Equipe equipeTOAdd) {
		boolean trouve = false;
		String nomEquipeDoublon ="";
		if (classement.size() != 0) {
			for (Entry<Equipe, Integer> entry: classement.entrySet() ) {
				if (equipeTOAdd.getId_equipe() == entry.getKey().getId_equipe()) {
					trouve = true;
					nomEquipeDoublon= entry.getKey().getNom_equipe();
					break;
				}
			}
		}
			
			if(!trouve) {
				if (!classement.containsKey(equipeTOAdd)) {
					
					classement.put(equipeTOAdd, null);
				}
				else {
					System.out.println("Equipe {" + equipeTOAdd.getNom_equipe() + "(" + equipeTOAdd.getId_equipe()+ ")} déjà enregistrée pour ce tournoi, cette entrée ne sera pas mémorisée.");
				}
			}
			else {
				System.out.println("Une équipe possédant l'identifiant que vous essayez d'insérer existe déjà pour ce tournoi (" + nomEquipeDoublon + " avec l'identifiant " + equipeTOAdd.getId_equipe() + "), cette entrée ne sera pas mémorisée.");
			}
				
		}
	
	
	public void tirageAuSortMatch() {
		System.out.println("-----------------------------------  Début du tirage au sort  -----------------------------------");
		
		int nbEquipes = classement.size();
		int cpt = 0;
		System.out.println("          Nombre de participants : " + nbEquipes);
		
		try {
		
			if(nbEquipes >= 1) {  // on fait les tirages
				if (nbEquipes%2==0) { //nombre pair
					ArrayList<Integer> iDsEquipes = new ArrayList<Integer>();
					
					//On récupère les ids de toutes les équipes
					for (Entry<Equipe, Integer> entry: classement.entrySet() ) {
						iDsEquipes.add(entry.getKey().getId_equipe());
					}
					
					for(int i = 0;i<(nbEquipes/2);i++) {
						cpt++;
						//tirage premier numéro
						int indiceEquipe1DansTableau = tirerNombreAleatoire(iDsEquipes);
						//System.out.println("          Indice n°" + cpt + " tiré : " + indiceEquipe1DansTableau);
						int idEquipe1 = iDsEquipes.get(indiceEquipe1DansTableau);
						//System.out.println("          idEquipe1 : " + idEquipe1);
						iDsEquipes.remove(indiceEquipe1DansTableau);
						
						cpt++;
						int indiceEquipe2DansTableau = tirerNombreAleatoire(iDsEquipes);
						//System.out.println("          Indice n°" + cpt + " tiré : " + indiceEquipe2DansTableau);
						int idEquipe2 = iDsEquipes.get(indiceEquipe2DansTableau);
						//System.out.println("          idEquipe2 : " + idEquipe2);
						iDsEquipes.remove(indiceEquipe2DansTableau);
						
						//On créé le match
						//System.out.println("          Création du  match...");
						Match generatedMatch = new Match(getEquipe(idEquipe1),getEquipe(idEquipe2));
						//System.out.println("                    Match " + (i+1) + " : " + getEquipe(idEquipe1).getNom_equipe() + " VS " + getEquipe(idEquipe2).getNom_equipe());
						System.out.println("Tirage n°" + (i+1) + " : " + getEquipe(idEquipe1).getNom_equipe() + " VS " + getEquipe(idEquipe2).getNom_equipe());
						liste_matchs.add(generatedMatch);
					}
				}
				else {  //nombre impair
					System.out.println("          Exception : Nombre d'équipes impair, cas pas encore géré.");
				}
			}
			else if(nbEquipes < 1) {
					System.out.println("          Il n'y a aucune équipe enregistrée pour le moment.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------  Fin du tirage au sort  -----------------------------------");
	}

	private int tirerNombreAleatoire(ArrayList<Integer> iDsEquipes) {
		Random monRandom = new Random();
		int positionEquipeDansTableauTireeAuSort = monRandom.nextInt(iDsEquipes.size());
		return positionEquipeDansTableauTireeAuSort;
	}
	
	private Equipe getEquipe(int idEquipeRecherchee) {
		Equipe equipeRecherchee = null;
		for (Entry<Equipe, Integer> entry: classement.entrySet() ) {
			if (entry.getKey().getId_equipe()==idEquipeRecherchee) {
				equipeRecherchee = entry.getKey();
				break;
			}
		}
		return equipeRecherchee;
	}
}


	