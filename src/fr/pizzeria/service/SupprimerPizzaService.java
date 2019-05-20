package fr.pizzeria.service;
import java.util.Scanner;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaService extends MenuService {
	
	Scanner questionUser = new Scanner(System.in);

	@Override
	public void executeUC(IPizzaDao pizzaDao) throws DeletePizzaException {
		// TODO Auto-generated method stub
		
		System.out.println("Suppression d'une pizza ");
		
			// CHOIX DU CODE PIZZA PAR L'UTILISATEUR
		System.out.println("Veuillez choisir le code de la pizza à supprimer:  ");
		String suppr_code = questionUser.nextLine();
		
			// EXCEPTION POUR CODE INEXISTANT
		if(!pizzaDao.pizzaExists(suppr_code)) {
			
			throw new DeletePizzaException(" ***************************** La pizza à supprimer n'existe pas ***************************** ");
			
		}
			// SUPPRESSION DE LA PIZZA
		pizzaDao.deletePizza(suppr_code) ;
		
	}
}
