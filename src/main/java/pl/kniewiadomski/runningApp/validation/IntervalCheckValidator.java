package pl.kniewiadomski.runningApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.TrainingDetails;
import pl.kniewiadomski.runningApp.enums.TrainingType;

public class IntervalCheckValidator implements ConstraintValidator<IntervalCheck, TrainingDetails> {

	@Override
	public void initialize(IntervalCheck date) {
		// Nothing here
	}
	
	public boolean isValid(TrainingDetails details, ConstraintValidatorContext context) {
		
		if(details.getType() == TrainingType.DISTANCE_INTERVAL)
			return details.getIntervalQuantity() != null && details.getFastDistance() != null && details.getRecoverDistance() != null;
		
		if(details.getType() == TrainingType.TIME_INTERVAL)
			return details.getIntervalQuantity() != null && details.getFastTime() != null && details.getRecoverTime() != null;
		
		return true;
	}
}