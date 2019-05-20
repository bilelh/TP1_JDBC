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
			
		
			while (resultats.next()) {
				int id = resultats.getInt("ID");
				String code = resultats.getString("CODE") ;
				String libelle = resultats.getString("LIBELLE") ;
				double prix = resultats.getDouble("PRIX") ;

				Pizza pizza = new Pizza(id, code , libelle , prix );
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
		
		PreparedStatement newPizzaSt;
		try {
			newPizzaSt = (PreparedStatement) connection.prepareStatement("INSERT INTO pizzas");
			newPizzaSt.setInt(1,pizza.id);
			newPizzaSt.setString(2, pizza.code);
			newPizzaSt.setString(3, pizza.libelle);
			newPizzaSt.setDouble(4, pizza.prix);
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
		
		PreparedStatement updatePizzaSt;
		try {
			updatePizzaSt = (PreparedStatement) connection.prepareStatement("UPDATE pizzas WHERE CODE=codePizza");
			updatePizzaSt.setInt(1,pizza.id);
			updatePizzaSt.setString(2, pizza.code);
			updatePizzaSt.setString(3, pizza.libelle);
			updatePizzaSt.setDouble(4, pizza.prix);
			updatePizzaSt.executeUpdate();
			
			updatePizzaSt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		deconnecter() ;
		
	}
	
	public void deletePizza(String codePizza) {
		
		PreparedStatement deletePizzaSt;
		try {
			deletePizzaSt = (PreparedStatement) connection.prepareStatement("DELETE FROM pizzas WHERE CODE = codePizza");
			deletePizzaSt.executeUpdate();
			
			deletePizzaSt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public Pizza findPizzaByCode(String codePizza) {
		
		Pizza pizza = null ;
		
		try {
			PreparedStatement selectPizzaSt = (PreparedStatement) connection.prepareStatement("SELECT * FROM pizzas WHERE CODE = codePizza");
			ResultSet resultats = selectPizzaSt.executeQuery();
			
			
			int id = resultats.getInt("ID");
			String code = resultats.getString("CODE") ;
			String libelle = resultats.getString("LIBELLE") ;
			double prix = resultats.getDouble("PRIX") ;

			pizza = new Pizza(id, code , libelle , prix );
			
			resultats.close();
			selectPizzaSt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pizza ;
		
	}
	
	public boolean pizzaExists(String codePizza) {
		
		
		return false ;
	} 
}
