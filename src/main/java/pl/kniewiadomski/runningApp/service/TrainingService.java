package pl.kniewiadomski.runningApp.service;

import java.util.Date;
import java.util.List;

import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.filters.HistoryFilter;
import pl.kniewiadomski.runningApp.filters.PlanFilter;

public interface TrainingService {
	
	public List<Training> findByIsDoneAndUser(Boolean isDone, User user);
	
	public Training findById(int theId);
	
	public void save(Training theTraining);

	public void delete(Training theTraining);
	
	public List<Training> getFilteredPlan(PlanFilter filter, User user);
	
	public List<Training> getFilteredHistory(HistoryFilter filter, User user);
}
