package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemoDao implements IPizzaDao {
	
	Scanner questionUser = new Scanner(System.in);
	
	public List listPizza = new ArrayList ();
	
	public void initialisation () {
		
		listPizza.add(new Pizza (0 ,"PEP" , "pépéroni" , 12.50 , CategoriePizza.VIANDE)) ;
		listPizza.add(new Pizza (1 ,"MAR" , "Margherita" , 14.00 , CategoriePizza.SANS_VIANDE)) ;
		listPizza.add(new Pizza (2 ,"REIN" , "La Reine" , 11.50 , CategoriePizza.VIANDE)) ;
		listPizza.add(new Pizza (3 ,"FRC" , "La 4 Fromage" , 12.00 , CategoriePizza.SANS_VIANDE)) ;
		listPizza.add(new Pizza (4 ,"CAN" , "La Cannibale" , 12.50 , CategoriePizza.VIANDE)) ;
		listPizza.add(new Pizza (5 ,"SAV" , "La Savoyarde" , 13.00 , CategoriePizza.VIANDE)) ;
		listPizza.add(new Pizza (6 ,"ORI" , "L'Orientale" , 13.50 , CategoriePizza.VIANDE)) ;
		listPizza.add(new Pizza (7 ,"IND" , "L'Indienne" , 14.00 , CategoriePizza.VIANDE)) ; 
		
	}
	
	
	public List <Pizza> findAllPizzas() {
		
		return listPizza ;
		
	}
	
	public void saveNewPizza(Pizza pizza) {
		
		listPizza.add(pizza) ;
		
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		
		Pizza pizza_by_code ;
		
		for ( int i = 0 ; i < listPizza.size() ; i++ ) {
			pizza_by_code = (Pizza) listPizza.get(i) ;
			if (pizza_by_code.code.compareTo(codePizza) == 0) {
				listPizza.remove(listPizza.get(i)) ;
			}
		}
		
		listPizza.add(pizza) ;
		
	}
	
	public void deletePizza(String codePizza) {
		
		Pizza pizza_by_code ;
		
		for ( int i = 0 ; i < listPizza.size() ; i++ ) {
			
			pizza_by_code = (Pizza) listPizza.get(i) ;
			
			if (pizza_by_code.code.compareTo(codePizza) == 0) {
				
				listPizza.remove(listPizza.get(i)) ;
				
			}
		}
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		
		int indice = 0 ;
		Pizza pizza ;
		
		for ( int i = 0 ; i < listPizza.size() ; i++ ) {
			
			pizza = (Pizza) listPizza.get(i) ;
			
			if (pizza.code.compareTo(codePizza) == 0) {
				
				indice = i ;
				
			}
		}
		
		return (Pizza) listPizza.get(indice) ;
		
	}
	
	public boolean pizzaExists(String codePizza) {
		
		Pizza pizza_by_code ;
		boolean exist = false ;
		int test = 0 ;
		
		for ( int i = 0 ; i < listPizza.size() ; i++ ) {
			
			pizza_by_code = (Pizza) listPizza.get(i) ;
			
			if (pizza_by_code.code.contains(codePizza) == true) {
				test +=1 ;
				
			}
		}
		
		if (test !=0) {
			
			exist = true ;
			
		}
		
		return exist ;
		
	}
}
