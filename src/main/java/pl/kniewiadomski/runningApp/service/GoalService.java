package pl.kniewiadomski.runningApp.service;

import java.util.Date;
import java.util.List;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;

public interface GoalService {
	
	public List<Goal> findAll(User user);
	
	public Goal findById(int theId);
	
	public void save(Goal theGoal);
	
	public void deleteById(int theId);

	public List<Goal> findWithDate(Date date, User user);

	public void updateGoalsStatus(List<Goal> goals, List<Training> trainings);
}
