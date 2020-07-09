package pl.kniewiadomski.runningApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.TrainingDetails;

public class HrRangeCheckValidator implements ConstraintValidator<HrRangeCheck, TrainingDetails> {

	@Override
	public void initialize(HrRangeCheck date) {
		// Nothing here
	}
	
	public boolean isValid(TrainingDetails details, ConstraintValidatorContext context) {
		
		if(details.getMinHr() == null || details.getMaxHr() == null)
			return true;
		
		return details.getMinHr() < details.getMaxHr();
	}
}