package pl.kniewiadomski.runningApp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kniewiadomski.runningApp.dao.GoalRepository;
import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.enums.GoalType;

@Service
public class GoalServiceImpl implements GoalService {

	private GoalRepository goalRepository;
	
	@Autowired
	public GoalServiceImpl(GoalRepository theGoalRepository) {
		goalRepository = theGoalRepository;
	}

	@Override
	public List<Goal> findAll(User user) {
		
		return goalRepository.findByUser(user);
	}

	@Override
	public Goal findById(int theId) {
		Optional<Goal> result = goalRepository.findById(theId);
		
		Goal theGoal = null;
		
		if (result.isPresent()) {
			theGoal = result.get();
		}
		else {
			throw new RuntimeException("Did not find goal id - " + theId);
		}
		
		return theGoal;
	}

	@Override
	public void save(Goal theGoal) {
		
		if(theGoal.getType() == GoalType.QUANTITY && theGoal.getQuantityProgress() == null) theGoal.setQuantityProgress(0);
		else if(theGoal.getType() == GoalType.TOTAL_DISTANCE && theGoal.getDistanceProgress() == null) theGoal.setDistanceProgress(0.0);
		goalRepository.save(theGoal);
	}

	@Override
	public void deleteById(int theId) {
		
		goalRepository.deleteById(theId);
	}

	@Override
	public List<Goal> findWithDate(Date date, User user) {
		
		return goalRepository.findByDate(date, user);
	}


	@Override
	public void updateGoalsStatus(List<Goal> goals, List<Training> trainings) {

		for (Goal goal : goals) {
				
			if(goal.getType() == GoalType.TOTAL_DISTANCE) {
				
				double current = trainings.stream()
						.filter(t -> t.getDate().compareTo(goal.getStartDate()) >= 0 && t.getDate().compareTo(goal.getEndDate()) <= 0)
						.mapToDouble(t -> t.getResult().getActDistance().doubleValue())
						.sum();
				
				goal.setDistanceProgress(current);
				
				if(goal.getIsDone() == false && current >= goal.getDistance()) {
					goal.setIsDone(true);
				}
				else if(goal.getIsDone() == true && current < goal.getDistance()) {
					goal.setIsDone(false);
				}
				
				goalRepository.save(goal);
			}
			
			if(goal.getType() == GoalType.QUANTITY) {
				
				int current = (int)trainings.stream()
						.filter(t -> t.getDate().compareTo(goal.getStartDate()) >= 0 && t.getDate().compareTo(goal.getEndDate()) <= 0)
						.count();
						
				System.out.println(current);
				
				goal.setQuantityProgress(current);
				
				if(goal.getIsDone() == false && current >= goal.getQuantity()) {
					goal.setIsDone(true);
				}
				else if(goal.getIsDone() == true && current < goal.getQuantity()) {
					goal.setIsDone(false);
				}
				
				goalRepository.save(goal);
			}
			
			if(goal.getType() == GoalType.DISTANCE) {
				
				boolean isDone = trainings.stream()
						.anyMatch(t -> t.getResult().getActDistance() >= goal.getDistance());
				
				goal.setIsDone(isDone);
				
				goalRepository.save(goal);
			}
			
			if(goal.getType() == GoalType.TIME_ON_DISTANCE) {
				
				boolean isDone = trainings.stream()
						.filter(t -> Math.abs(t.getResult().getActDistance() - goal.getDistance()) < 0.001)
						.anyMatch(t -> t.getResult().getActTime().compareTo(goal.getTime()) <= 0);
				
				goal.setIsDone(isDone);
				
				goalRepository.save(goal);
			}		
		}
	}



	

}
