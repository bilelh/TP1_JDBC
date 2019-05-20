package fr.pizzeria.service;
import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {
	
	Scanner questionUser = new Scanner(System.in);

	@Override
	public void executeUC(IPizzaDao pizzaDao) throws SavePizzaException {
		// TODO Auto-generated method stub
		
		System.out.println("Ajout d'une nouvelle pizza  ");
			// L'UTILISATEUR RENSEIGNE LE CODE, LE NOM ET LE PRIX DE LA NOUVELLE PIZZA
		System.out.println("Veuillez saisir le code:  ");
		String newCode = questionUser.nextLine();
		
		System.out.println("Veuillez saisir le nom (sans espace):  ");
		String newLibelle = questionUser.nextLine();
		
		System.out.println("Veuillez saisir le prix (avec une virgule):  ");
		double newPrix = questionUser.nextDouble();
		questionUser.nextLine();
		
			// AFFICHAGE DE LA LISTE DES CATEGORIES DISPONIBLES
		System.out.println(" **** Categories de pizza **** ");
		System.out.println("1 . Viande ");
		System.out.println("2 . Poisson ");
		System.out.println("3 . Sans Viande ");
		
			// L'UTILISATEUR RENSEIGNE LA CATEGORIE
		System.out.println("Veuillez choisir la categorie de la pizza (en toute lettre et en respectant les majuscules et minuscules):  ");
		String catChoice = questionUser.nextLine();
		
		CategoriePizza newCat = null  ;
		
			/* ON COMPARE LES DIFFERENTES CATEGORIES AU CHOIX DE L'UTILISATEUR 
			ET INITIALISE LA CATEGORIE SI LE CHOIX EST DISPONIBLE*/
		for (int i = 0 ; i < CategoriePizza.values().length ; i++){
			if ( CategoriePizza.values()[i].toString().compareTo(catChoice) == 0) {
				  newCat = CategoriePizza.values()[i] ;
			  }
		}
			  
			// EXCEPTION POUR PIZZA DEJA EXISTANTE
		if(pizzaDao.pizzaExists(newCode)) {
			throw new SavePizzaException(" ***************************** Le code de la pizza existe déjà ***************************** ");
		}	
			// EXEPTION POUR LA CATEGORIE NON EXISTANTE
		if(newCat == null) {
			throw new SavePizzaException(" ***************************** Cette categorie n'est pas disponible ***************************** ");
		}	
		
			// AJOUT DE LA NOUVELLE PIZZA
		Pizza newPizza = new Pizza (newCode , newLibelle , newPrix , newCat ) ;
		pizzaDao.saveNewPizza(newPizza);
		
	}
}
