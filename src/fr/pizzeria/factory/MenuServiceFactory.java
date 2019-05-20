package fr.pizzeria.factory;

import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ListerPizzaService;
import fr.pizzeria.service.MenuService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.PizzaMemoDao;
import fr.pizzeria.service.SupprimerPizzaService;

public class MenuServiceFactory {
	
	public MenuService factory (int user_choice) {
		
		MenuService liste = new ListerPizzaService () ;
		MenuService ajout = new AjouterPizzaService () ;
		MenuService modif = new ModifierPizzaService () ;
		MenuService suppr = new SupprimerPizzaService () ;
		MenuService choice = null ;
				
						// CHOIX 1. AFFICHAGE DE LA LISTE DE PIZZAS
			if (user_choice == 1) {     
			
			choice = liste ;
						
						// CHOIX 2. AJOUT D'UNE PIZZA
			} else if ( user_choice == 2) { 
			
			choice = ajout ;
			
						// CHOIX 3. MODIFICATION D'UNE PIZZA
			} else if ( user_choice == 3) {
			
			choice = modif ;
			
						// CHOIX 4. SUPPRESSION D'UNE PIZZA
			} else if ( user_choice == 4) {
			
			choice = suppr ;
			
						// MESSAGE POUR LE CAS OU L'OPTION SAISIE N'EST PAS DISPONIBLE
			} 
		
		return choice ;
	}
}
