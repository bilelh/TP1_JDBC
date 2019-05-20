package fr.pizzeria.model;

public enum CategoriePizza {
	
	VIANDE 			("Viande"), 
	POISSON 		("Poisson"), 
	SANS_VIANDE 	("Sans Viande");
	
	private String cat;

	private CategoriePizza(String cat) {
		
		this.cat = cat;
		
	}
	
	public String getCat() {
		
		return cat;
		
	}
	public String toString() {
		
		return getCat();
	}
	

}
