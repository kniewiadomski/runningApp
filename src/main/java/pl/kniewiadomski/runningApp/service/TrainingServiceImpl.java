package pl.kniewiadomski.runningApp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import pl.kniewiadomski.runningApp.dao.TrainingRepository;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.filters.HistoryFilter;
import pl.kniewiadomski.runningApp.filters.PlanFilter;

@Service
public class TrainingServiceImpl implements TrainingService {

	private TrainingRepository trainingRepository;
	
	@Autowired
	public TrainingServiceImpl(TrainingRepository theTrainingRepository) {
		trainingRepository = theTrainingRepository;
	}
	
	@Override
	public List<Training> findByIsDoneAndUser(Boolean isDone, User user) {
		return trainingRepository.findByIsDoneAndUserOrderByDate(isDone, user);
	}

	@Override
	public Training findById(int theId) {
		Optional<Training> result = trainingRepository.findById(theId);
		
		Training theTraining = null;
		
		if (result.isPresent()) {
			theTraining = result.get();
		}
		else {
			throw new RuntimeException("Did not find training id - " + theId);
		}
		
		return theTraining;
	}

	@Override
	public void save(Training theTraining) {
		trainingRepository.save(theTraining);
	}

	@Override
	public void delete(Training theTraining) {
		trainingRepository.delete(theTraining);
	}

	@Override
	public List<Training> getFilteredPlan(PlanFilter filter, User user) {
		
		List<Training> theTrainings = findByIsDoneAndUser(false, user);
		
		
		Predicate<Training> dateFrom = training -> (filter.getDateFrom() != null) ? (training.getDate().compareTo(filter.getDateFrom()) >= 0) : true;
		Predicate<Training> dateTo = training -> (filter.getDateTo() != null) ? (training.getDate().compareTo(filter.getDateTo()) <= 0) : true;
		Predicate<Training> series = training -> (filter.getSeries() != null && training.getSeries() != null) ? (training.getSeries().equals(filter.getSeries())) : true;
		
		theTrainings = theTrainings.stream().filter(dateFrom)
											.filter(dateTo)
											.filter(series)
											.collect(Collectors.toList());
		
		return theTrainings;
	}

	@Override
	public List<Training> getFilteredHistory(HistoryFilter filter, User user) {
		
		List<Training> theTrainings = findByIsDoneAndUser(true, user);
		
		Predicate<Training> distanceFrom = training -> (filter.getDistanceFrom() != null) ? (training.getResult().getActDistance() >= filter.getDistanceFrom()) : true;
		Predicate<Training> distanceTo = training -> (filter.getDistanceTo() != null) ? (training.getResult().getActDistance() <= filter.getDistanceTo()) : true;
		Predicate<Training> dateFrom = training -> (filter.getDateFrom() != null) ? (training.getDate().compareTo(filter.getDateFrom()) >= 0) : true;
		Predicate<Training> dateTo = training -> (filter.getDateTo() != null) ? (training.getDate().compareTo(filter.getDateTo()) <= 0) : true;
		Predicate<Training> timeFrom = training -> (filter.getTimeFrom() != null) ? (training.getResult().getActTime().compareTo(filter.getTimeFrom()) >= 0) : true;
		Predicate<Training> timeTo = training -> (filter.getTimeTo() != null) ? (training.getResult().getActTime().compareTo(filter.getTimeTo()) <= 0) : true;
		Predicate<Training> hrFrom = training -> (filter.getHrFrom() != null) ? (training.getResult().getActHr() >= filter.getHrFrom()) : true;
		Predicate<Training> hrTo = training -> (filter.getHrTo() != null) ? (training.getResult().getActHr() <= filter.getHrTo()) : true;
		
		theTrainings = theTrainings.stream().filter(distanceFrom)
											.filter(distanceTo)
											.filter(dateFrom)
											.filter(dateTo)
											.filter(timeFrom)
											.filter(timeTo)
											.filter(hrFrom)
											.filter(hrTo)
											.collect(Collectors.toList());
		
		return theTrainings;
	}


}






