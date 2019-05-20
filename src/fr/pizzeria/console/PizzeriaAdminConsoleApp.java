package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.factory.MenuServiceFactory;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ListerPizzaService;
import fr.pizzeria.service.MenuService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.PizzaMemoDao;
import fr.pizzeria.service.SupprimerPizzaService;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws PizzaException {
		// TODO Auto-generated method stub
		
							// INITIALISATION DE LA LISTE DE PIZZA
		PizzaMemoDao pizza = new PizzaMemoDao() ;
		pizza.initialisation();
		
							// SCANNER QUI PERMET DE RECUPERER LA SAISIE CLAVIER
		Scanner questionUser = new Scanner(System.in);

							// AFFICHAGE DE LA LISTE DES OPTIONS
		System.out.println("***** Pizzeria Administration *****  " );
		System.out.println("1. Lister les pizzas  ");
		System.out.println("2. Ajouter une nouvelle pizza  ");
		System.out.println("3. Mettre à jour une pizza  ");
		System.out.println("4. Supprimer une pizza  ");
		System.out.println("99. Sortir  ");
		
							// L'UTILISATEUR CHOISIT L'OPTION
		System.out.println("Veuillez faire votre choix");
		int user_choice = questionUser.nextInt();
		
							// INITIALISATION DE LA FACTORY
		MenuServiceFactory choice = new MenuServiceFactory () ;
		
		
		while (user_choice != 99) {
			if ((user_choice != 1) || (user_choice != 2) || (user_choice != 3) || (user_choice != 4)) {	
				
						// APPEL DE LA FACTORY
				try {
					
					choice.factory(user_choice).executeUC(pizza) ;
					
				} catch(PizzaException pizzaException) {
					
					System.out.println(pizzaException.getMessage());
					
				}
				
						// AFFICHAGE DE LA LISTE DES OPTIONS 
				System.out.println("***** Pizzeria Administration *****  ");
				System.out.println("1. Lister les pizzas  ");
				System.out.println("2. Ajouter une nouvelle pizza  ");
				System.out.println("3. Mettre à jour une pizza  ");
				System.out.println("4. Supprimer une pizza  ");
				System.out.println("99. Sortir  ");
				
						// L'UTILISATEUR CHOISIT L'OPTION
				System.out.println("Veuillez faire votre choix");
				user_choice = questionUser.nextInt();
			
			} else {
						// MESSAGE POUR CHOIX NON DISPONIBLE
				System.out.println("Je n'ai pas compris, Veuillez recommencer ");
				
						// L'UTILISATEUR CHOISIT L'OPTION
				System.out.println("Veuillez faire votre choix");
				user_choice = questionUser.nextInt();
			}
		}
					//MESSAGE DE FIN POUR LE CHOIX 99.
				System.out.println("Aurevoir !!! ") ;
		
	}
			 		
}


