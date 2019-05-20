package fr.pizzeria.service;
import java.util.List;

import fr.pizzeria.model.Pizza;

public class ListerPizzaService extends MenuService {

	@Override
	public void executeUC(IPizzaDao pizzaDao) {
		// TODO Auto-generated method stub
		
		System.out.println("Liste des pizzas  ");
		
		List <Pizza> mesPizza = pizzaDao.findAllPizzas() ;
		
		try {
			
			for ( int i = 0 ; i < pizzaDao.findAllPizzas().size() ; i++) {
			
				System.out.println( " ==> " + mesPizza.get(i).code + " -> " + mesPizza.get(i).libelle + " ( " + String.format("%.2f" , mesPizza.get(i).prix) + "€ )" + "  categorie : " + mesPizza.get(i).cat.toString());
			} 
			
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	}
}
