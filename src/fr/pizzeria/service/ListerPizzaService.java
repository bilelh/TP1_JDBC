package fr.pizzeria.service;
import fr.pizzeria.model.Pizza;

public class ListerPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemoDao pizzaDao) {
		// TODO Auto-generated method stub
		
		System.out.println("Liste des pizzas  ");
		
		for ( int i = 0 ; i < pizzaDao.findAllPizzas().size() ; i++) {
			
			Pizza pizza = (Pizza) pizzaDao.findAllPizzas().get(i) ;
			
			System.out.println( " ==> " + pizza.code + " -> " + pizza.libelle + " ( " + String.format("%.2f" , pizza.prix) + "€ )" + "  categorie : " + pizza.cat.toString());
		
		}
	}
}
