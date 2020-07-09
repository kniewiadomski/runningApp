package pl.kniewiadomski.runningApp.service;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Training;

public interface TrainingAndGoalService {

	public boolean checkNewGoal(Goal goal);
	
	public boolean checkGoalsWithNewTraining(Training training);
}
