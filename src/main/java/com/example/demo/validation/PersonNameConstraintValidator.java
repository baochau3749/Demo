package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PersonNameConstraintValidator implements ConstraintValidator<PersonName, String> {

	private int minLength;
	
	private int maxLength;
	
	
	
	@Override
	public void initialize(PersonName name) {
		
		this.minLength = name.min();
		this.maxLength = name.max();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (value == null) {
			 return false;
		}
		
		if (value.isEmpty()) {
			return false;
		}
		
		if (value.length() < minLength | value.length() > maxLength) {
			return false;
		}

		return true;
	}

}
