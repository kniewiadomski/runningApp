package pl.kniewiadomski.runningApp.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.Training;

public class PastIfDoneValidator implements ConstraintValidator<PastIfDone, Training> {

	@Override
	public void initialize(PastIfDone date) {
		// Nothing here
	}
	
	public boolean isValid(Training training, ConstraintValidatorContext context) {
		
		if(training.getIsDone() == true)
			return training.getDate().before(new Date());
		
		return true;
	}
}