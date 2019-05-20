package fr.pizzeria.service;
import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {
	
	Scanner questionUser = new Scanner(System.in);

	@Override
	public void executeUC(IPizzaDao pizzaDao) throws UpdatePizzaException {
		// TODO Auto-generated method stub
		
		System.out.println("Mise à jour d'une pizza  ");
		
			// CHOIX DU CODE PIZZA PAR L'UTILISATEUR
		System.out.println("Veuillez choisir le code de la pizza à modifier:  ");
		
		String modif_code = questionUser.nextLine();
		
		
			// EXCEPTION POUR CODE INEXISTANT
		if(!pizzaDao.pizzaExists(modif_code)) {
			throw new UpdatePizzaException(" ***************************** La pizza à modifier n'existe pas ***************************** ");
		}

			// MESSAGE POUR PIZZA EXISTANTE
		System.out.println("la pizza existe  ");
	
			// L'UTILISATEUR RENSEIGNE LE CODE, LE NOM ET LE PRIX DE LA NOUVELLE PIZZA
		System.out.println("Veuillez saisir le nouveau code:  ");
		String newCode = questionUser.nextLine();
		
		System.out.println("Veuillez saisir le nouveau nom (sans espace):  ");
		String newLibelle = questionUser.nextLine();
		
		System.out.println("Veuillez saisir le nouveau prix (avec une virgule):  ");
		double newPrix = questionUser.nextDouble();
		questionUser.nextLine();
		
			// AFFICHAGE DE LA LISTE DES CATEGORIES DISPONIBLES
		System.out.println(" **** Categories de pizza **** ");
		System.out.println("1 . Viande ");
		System.out.println("2 . Poisson ");
		System.out.println("3 . Sans Viande ");
		
			// L'UTILISATEUR RENSEIGNE LA CATEGORIE
		System.out.println("Veuillez choisir la categorie de la pizza (en toute lettre):  ");
		String catChoice = questionUser.nextLine();
		
			// INITIAISATION D'UNE CategoriePizza
		CategoriePizza newCat = null  ;
		
			/* ON COMPARE LES DIFFERENTES CATEGORIES AU CHOIX DE L'UTILISATEUR 
			ET INITIALISE LA CATEGORIE SI LE CHOIX EST DISPONIBLE*/
		for (int i = 0 ; i < CategoriePizza.values().length ; i++){
			
			if ( CategoriePizza.values()[i].toString().compareTo(catChoice) == 0) {
				
				  newCat = CategoriePizza.values()[i] ;
				  
			  }
		}
		
			// EXEPTION POUR LA CATEGORIE NON EXISTANTE
		if(newCat == null) {
			throw new UpdatePizzaException(" ***************************** Cette categorie n'est pas disponible *****************************");
		}	
		
			// MISE A JOUR DE LA PIZZA
		Pizza newPizza = new Pizza (newCode , newLibelle , newPrix , newCat) ;
		pizzaDao.updatePizza(modif_code, newPizza);
		
	}
}

