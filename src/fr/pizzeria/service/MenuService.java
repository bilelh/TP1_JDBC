package fr.pizzeria.service;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public abstract class MenuService {

	public abstract void executeUC (IPizzaDao pizzaDao) throws PizzaException ;
	
}
