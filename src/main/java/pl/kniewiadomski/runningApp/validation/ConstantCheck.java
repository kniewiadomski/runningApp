package pl.kniewiadomski.runningApp.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ConstantCheckValidator.class)
public @interface ConstantCheck {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

