package fr.pizzeria.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaJdbcDao implements IPizzaDao {
	
	Scanner questionUser = new Scanner(System.in);
	
	Connection connection ;
	
	public void connecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza" , "root" , "") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void deconnecter() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List <Pizza> findAllPizzas() {
		
		connecter() ;
		
		List <Pizza> listPizza = new ArrayList ();
		
		try {
			PreparedStatement selectPizzaSt = (PreparedStatement) connection.prepareStatement("SELECT * FROM pizzas");
			
			ResultSet resultats = selectPizzaSt.executeQuery();
			Pizza pizza = null ;
		
			while (resultats.next()) {
				int id = resultats.getInt("ID");
				String code = resultats.getString("CODE") ;
				String libelle = resultats.getString("LIBELLE") ;
				double prix = resultats.getDouble("PRIX") ;
				String categorie = resultats.getString("CATEGORIE") ;
				
				
				if (categorie.equals("Viande")) {
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.VIANDE);
					
				} else if (categorie.equals("Sans Viande")) {
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.SANS_VIANDE);
					
				} else if (categorie.equals("Poisson")) {
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.POISSON);
					
				} 
				listPizza.add(pizza);
				
			}
			
			resultats.close();
			selectPizzaSt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		deconnecter() ;
		
		return listPizza ;
		
	}
	
	public void saveNewPizza(Pizza pizza) {
		//listPizza.add(pizza) ;
		connecter() ;
		
		
		try {
			PreparedStatement newPizzaSt = (PreparedStatement) connection.prepareStatement("INSERT INTO pizzas VALUES(? , ? , ? , ? , ?)");
			newPizzaSt.setInt(1,pizza.id);
			newPizzaSt.setString(2, pizza.code);
			newPizzaSt.setString(3, pizza.libelle);
			newPizzaSt.setDouble(4, pizza.prix);
			
			if (pizza.getCat().equals("Viande")) {
				newPizzaSt.setString(5, "Viande");
				
			} else if (pizza.getCat().equals("Sans Viande")) {
				newPizzaSt.setString(5, "Sans Viande");
				
			} else if (pizza.getCat().equals("Poisson")) {
				newPizzaSt.setString(5, "Viande");
				
			} 
			
			newPizzaSt.executeUpdate();
			
			newPizzaSt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		deconnecter() ;
	}
	
	public void updatePizza(String codePizza, Pizza pizza) {
		
		connecter() ;
		System.out.println(pizza.id);
		//findPizzaByCode(codePizza);
		
		try {
			PreparedStatement updatePizzaSt = (PreparedStatement) connection.prepareStatement
					("UPDATE pizzas SET   CODE = ? , LIBELLE = ? , PRIX = ? , CATEGORIE = ? WHERE CODE = ?");
			//updatePizzaSt.setInt(1,pizza.id);
			updatePizzaSt.setString(1, pizza.code);
			updatePizzaSt.setString(2, pizza.libelle);
			updatePizzaSt.setDouble(3, pizza.prix);
			
			
			if (pizza.getCat().equals("Viande")) {
				updatePizzaSt.setString(4, "Viande");
				
			} else if (pizza.getCat().equals("Sans Viande")) {
				updatePizzaSt.setString(4, "Sans Viande");
				
			} else if (pizza.getCat().equals("Poisson")) {
				updatePizzaSt.setString(4, "Viande");
				
			} 
			updatePizzaSt.setString(5, codePizza);
			
			updatePizzaSt.executeUpdate();
			updatePizzaSt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		deconnecter() ;
		
	}
	
	public void deletePizza(String codePizza) {
		connecter() ;
		
		PreparedStatement deletePizzaSt;
		try {
			deletePizzaSt = (PreparedStatement) connection.prepareStatement("DELETE FROM pizzas WHERE CODE = ?");
			deletePizzaSt.setString(1, codePizza);
			deletePizzaSt.executeUpdate();
			
			deletePizzaSt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		deconnecter() ;
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		
		connecter() ;
		
		Pizza pizza = null ;
		int id = 0 ;
		String code = "" ;
		String libelle = "" ;
		double prix = 0 ;
		String categorie = null ;
		
		try {
			PreparedStatement selectPizzaSt = (PreparedStatement) connection.prepareStatement("SELECT * FROM pizzas WHERE CODE = ?");
			selectPizzaSt.setString(1, codePizza);
			ResultSet resultats = selectPizzaSt.executeQuery();
			
			while (resultats.next()) {
				
				id = resultats.getInt("ID");
				code = resultats.getString("CODE") ;
				libelle = resultats.getString("LIBELLE") ;
				prix = resultats.getDouble("PRIX") ;
				categorie = resultats.getString("CATEGORIE") ;
				
				switch(categorie) {
				case "Viande":
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.VIANDE);
					break ;
				case "Poisson":
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.POISSON);
					break ;
				case "Sans Viande":
					pizza = new Pizza(id, code , libelle , prix , CategoriePizza.SANS_VIANDE);
					break ;
				default:
					System.out.println("Cette Categorie n'est pas disponible");
				}
				
				resultats.close();
				selectPizzaSt.close();
					
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deconnecter() ;
		
		return pizza ;
		
	}
	
	public boolean pizzaExists(String codePizza) {
		
		
		connecter() ;
		PreparedStatement selectPizzaSt;
		try {
			selectPizzaSt = (PreparedStatement) connection.prepareStatement("SELECT * FROM pizzas WHERE CODE = ?");
			selectPizzaSt.setString(1, codePizza);
			ResultSet resultats = selectPizzaSt.executeQuery();
		
			if(resultats.next()) {
				
				resultats.close();
				selectPizzaSt.close();
			
				deconnecter() ;
			
				return true ;
			}		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	} 
}
