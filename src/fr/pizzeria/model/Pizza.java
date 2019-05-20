package fr.pizzeria.model;

import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.ToString;

public class Pizza {
	
	public int id ;
	@ToString (uppercase = true)
	public String code ;
	@ToString (uppercase = false)
	public String libelle ;
	@Rule(min=0) 
	public double prix ;
	public static int id_pizza = 0 ;
		// AJOUT DE L'ATTRIBUT DE TYPE CategoriePizza
	public CategoriePizza cat;
	
		// AJOUT DE LA CATEGORIE DE PIZZA DANS LE CONSTRUCTEUR
	public Pizza (String code , String libelle , double prix , CategoriePizza cat) {
		this.id = id_pizza ;
		id_pizza++ ;
		this.code = code ;
		this.libelle = libelle ;
		this.prix = prix ;
		this.cat = cat ;
		
	}
	
		// AJOUT DE LA CATEGORIE DE PIZZA DANS LE CONSTRUCTEUR
	public Pizza (int id , String code , String libelle , double prix , CategoriePizza cat) {
		this.id = id ;
		if (id > id_pizza) {
			id_pizza = id+1;
		}
		this.code = code ;
		this.libelle = libelle ;
		this.prix = prix ;
		this.cat = cat ;
	}
	
	public String getCat() {
		return cat.getCat();
	}
	
	
	//public String toString () {
		
		
	//}
	

}
