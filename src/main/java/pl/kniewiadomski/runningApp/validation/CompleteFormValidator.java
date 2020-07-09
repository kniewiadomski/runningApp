package pl.kniewiadomski.runningApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.enums.GoalType;

public class CompleteFormValidator implements ConstraintValidator<CompleteForm, Goal> {

	@Override
	public void initialize(CompleteForm date) {
		// Nothing here
	}
	
	public boolean isValid(Goal goal, ConstraintValidatorContext context) {
			
		if(goal.getType() == GoalType.QUANTITY) {
			
			return goal.getStartDate() != null && goal.getEndDate() != null && goal.getQuantity() != null;
		}
		if(goal.getType() == GoalType.TOTAL_DISTANCE || goal.getType() == GoalType.DISTANCE) {
			
			return goal.getStartDate() != null && goal.getEndDate() != null && goal.getDistance() != null;
		}
		if(goal.getType() == GoalType.TIME_ON_DISTANCE) {
	
			return goal.getStartDate() != null && goal.getEndDate() != null && goal.getTime() != null && goal.getDistance() != null;
		}
		
		return false;
	}
}