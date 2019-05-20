package fr.pizzeria.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import fr.pizzeria.exception.PizzaException;

public class Validator {
	
	public void check(Object obj) throws Exception {
		
		Field[] fields = obj.getClass().getFields();

		// PARCOURS DES ANNOTATIONS
		for(Field f : fields) {
			
			for(Annotation a : f.getAnnotations()) {
				
				//VERIFICATION QUE LE MINIMUM EST RESPECTE SI L'ANNOTATION EST @Rule
				if(a instanceof Rule && ((Rule)a).min() > (double)f.get(obj)) {
					
					throw new PizzaException("vous devez saisir une valeur superieur à 0");
					
				}
			}
		}
	}

}
