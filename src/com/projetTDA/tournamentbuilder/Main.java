import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {

		// TEST JOUEUR
		System.out.println("TEST JOUEUR");
		Joueur lionel = new Joueur(10,"keff","img/lionel");
		System.out.println(lionel);
		
		Joueur guilhem = new Joueur(11,"guigui","img/guilhem");
		System.out.println(guilhem);
		
		
		// TEST EQUIPE
		System.out.println("\nTEST EQUIPE");
		ArrayList<Joueur> listeJoueur=new ArrayList<Joueur>();
		listeJoueur.add(lionel);
		listeJoueur.add(guilhem);
		Equipe winners = new Equipe(2,"winners",listeJoueur);
		System.out.println(winners);
		
	}

}