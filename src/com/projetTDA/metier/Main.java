package com.projetTDA.metier;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

	public static void main(String[] args) {

		// TEST JOUEUR
		System.out.println("TEST JOUEUR");
		Joueur lionel = new Joueur(10,"keff","img/lionel");
		System.out.println(lionel);
		
		Joueur guilhem = new Joueur(11,"guigui","img/guilhem");
		System.out.println(guilhem);
		
		Joueur stan = new Joueur(12,"stan","img/stan");
		System.out.println(stan);
		
		Joueur anthony = new Joueur(13,"anthony","img/anthony");
		System.out.println(anthony);
		
		
		// TEST EQUIPE
		System.out.println("\nTEST EQUIPE");
		ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();
		listeJoueur.add(lionel);
		listeJoueur.add(guilhem);
		Equipe winners = new Equipe(2,"winners",listeJoueur);
		System.out.println(winners);
		
		ArrayList<Joueur> listeJoueur2 =new ArrayList<Joueur>();
		listeJoueur2.add(stan);
		listeJoueur2.add(anthony);
		Equipe loosers = new Equipe(1,"loosers",listeJoueur2);
		System.out.println(loosers);
		
		
		// TEST MATCH
		System.out.println("TEST MATCH");
		//System.out.println("\n\t");
		Match match1 = new Match(winners, loosers, 3, 1);
		System.out.println("\t");
		System.out.println(match1);
		
		// TEST TOURNOI
		System.out.println("TEST TOURNOI");
		
		HashMap classement_equipe=new HashMap<Integer,Equipe>();
		classement_equipe.put(10, winners);
			
		ArrayList<Match> listeMatch=new ArrayList<Match>();
		listeMatch.add(match1);
			

		Tournoi tournoi1= new Tournoi("tournoi1", "02/04/2014", "foot", classement_equipe, listeMatch);
		
		System.out.println("\t");
		System.out.println(tournoi1);

	}

}