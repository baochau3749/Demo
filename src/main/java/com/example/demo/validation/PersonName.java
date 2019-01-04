package com.example.demo.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PersonNameConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PersonName {

	public String message() default "Name is invalid.";
	
	public int min() default 0;
	
	public int max() default 255;
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}
