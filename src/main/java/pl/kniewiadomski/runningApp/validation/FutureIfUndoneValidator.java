package pl.kniewiadomski.runningApp.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.Training;

public class FutureIfUndoneValidator implements ConstraintValidator<FutureIfUndone, Training> {

	@Override
	public void initialize(FutureIfUndone date) {
		// Nothing here
	}
	
	public boolean isValid(Training training, ConstraintValidatorContext context) {
			
		if(training.getDate() == null) return false;
		
		if(training.getIsDone() == false)
			return training.getDate().after(new Date());
		
		return true;
	}
}