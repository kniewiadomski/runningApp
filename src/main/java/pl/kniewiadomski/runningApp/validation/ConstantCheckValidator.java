package pl.kniewiadomski.runningApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.TrainingDetails;
import pl.kniewiadomski.runningApp.enums.TrainingType;

public class ConstantCheckValidator implements ConstraintValidator<ConstantCheck, TrainingDetails> {

	@Override
	public void initialize(ConstantCheck date) {
		// Nothing here
	}
	
	public boolean isValid(TrainingDetails details, ConstraintValidatorContext context) {
		
		if(details.getType() == TrainingType.CONSTANT)
			return details.getDistance() != null || details.getTime() != null;
		
		return true;
	}
}