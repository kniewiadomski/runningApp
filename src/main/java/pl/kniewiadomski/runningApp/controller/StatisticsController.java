package pl.kniewiadomski.runningApp.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.service.GoalService;
import pl.kniewiadomski.runningApp.service.TrainingService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	private TrainingService trainingService;
	private GoalService goalService;
	
	@Autowired
	public StatisticsController(TrainingService theTrainingService, GoalService theGoalService) {
		
		goalService = theGoalService;
		trainingService = theTrainingService;
	}
	
	@GetMapping("/main")
	public String getQuantity(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> allTrainings = trainingService.findByIsDoneAndUser(true, user);
		List<Goal> goals = goalService.findAll(user);
		List<Goal> doneGoals = goals.stream()
									.filter(g->g.getIsDone() == true)
									.collect(Collectors.toList());
		
		double totalDistance = 0;
		
		for (Training training : allTrainings) {
			
			totalDistance += training.getResult().getActDistance();
		}
		
		theModel.addAttribute("quantity", allTrainings.size());
		theModel.addAttribute("totalDistance", totalDistance);
		theModel.addAttribute("doneGoals", doneGoals.size());
		theModel.addAttribute("goals", goals.size());
		
		return "statistics/basic-statistics";
	}
	
	
	@GetMapping("/annual")
	public String annualStatistic(Model theModel, @AuthenticationPrincipal User user) {
		
		
		List<Training> allTrainings = trainingService.findByIsDoneAndUser(true, user);
		
		Map<Integer,Long> counter = allTrainings.stream()
				.collect(Collectors.groupingBy(t -> t.getDate().getYear() + 1900, Collectors.counting()));
		
		
		for(int i = new Date().getYear() + 1900 - 5; i <= new Date().getYear() + 1900; i++) {
			
			if(!counter.containsKey(i)) counter.put(i, 0L);
		}
		
		Map <Integer, Long> sortedCounter = counter.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (t1, t2) -> t2, LinkedHashMap::new));
		
		Map<Integer,Double> totalDistance = allTrainings.stream()
				.collect(Collectors.groupingBy(t -> t.getDate().getYear() + 1900,
                        Collectors.summingDouble(t->t.getResult().getActDistance())));
		
		for(int i = new Date().getYear() + 1900 - 5; i <= new Date().getYear() + 1900; i++) {
			
			if(!totalDistance.containsKey(i)) totalDistance.put(i, 0.0);
		}
		
		Map <Integer, Double> sortedTotalDistance = totalDistance.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (t1, t2) -> t2, LinkedHashMap::new));
		
		theModel.addAttribute("counter", sortedCounter);
		theModel.addAttribute("totalDistance", sortedTotalDistance);
		
		return "statistics/annual-statistics";
	}
	
	@GetMapping("/monthly")
	public String monthlyStatistic(@RequestParam(name = "year", defaultValue = "2020") int year, Model theModel, @AuthenticationPrincipal User user) {
		
		String[] months = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Pazdziernik"
		                   ,"Listopad", "Grudzień"};
		
		System.out.println(year);
		
		List<Training> allTrainings = trainingService.findByIsDoneAndUser(true, user).stream()
					.filter(t -> t.getDate().getYear()+1900 == year)
					.collect(Collectors.toList());
		
		Map<Integer,Long> counter = allTrainings.stream()
				.collect(Collectors.groupingBy(t -> t.getDate().getMonth(), Collectors.counting()));
		
		for(int i = 0; i <= 11; i++) {
			
			if(!counter.containsKey(i)) counter.put(i, 0L);
		}	
		
		Map <String, Long> sortedCounter = counter.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(t -> months[t.getKey()], t -> t.getValue(), (t1, t2) -> t2, LinkedHashMap::new));
		
		Map<Integer,Double> totalDistance = allTrainings.stream()
				.collect(Collectors.groupingBy(t -> t.getDate().getMonth(),
                        Collectors.summingDouble(t->t.getResult().getActDistance())));
		
		for(int i = 0; i <= 11; i++) {
			
			if(!totalDistance.containsKey(i)) totalDistance.put(i, 0.0);
		}	
		
		Map <String, Double> sortedtotalDistance = totalDistance.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(t -> months[t.getKey()], t -> t.getValue(), (t1, t2) -> t2, LinkedHashMap::new));
		
		sortedCounter.put("Styczeń", 10L);
		sortedCounter.put("Luty", 13L);
		sortedCounter.put("Marzec", 8L);
		sortedCounter.put("Kwiecień", 14L);
		sortedCounter.put("Maj", 15L);
		sortedCounter.put("Czerwiec", 6L);
		sortedCounter.put("Lipiec", 19L);
		sortedCounter.put("Sierpień", 16L);
		sortedCounter.put("Wrzesień", 22L);
		sortedCounter.put("Pazdziernik", 20L);
		sortedCounter.put("Listopad", 21L);
		sortedCounter.put("Grudzień", 22L);
		
		sortedtotalDistance.put("Styczeń", 100.00);
		sortedtotalDistance.put("Luty", 120.00);
		sortedtotalDistance.put("Marzec", 100.00);
		sortedtotalDistance.put("Kwiecień", 110.00);
		sortedtotalDistance.put("Maj", 120.00);
		sortedtotalDistance.put("Czerwiec", 90.00);
		sortedtotalDistance.put("Lipiec", 70.00);
		sortedtotalDistance.put("Sierpień", 130.00);
		sortedtotalDistance.put("Wrzesień", 140.00);
		sortedtotalDistance.put("Pazdziernik", 100.00);
		sortedtotalDistance.put("Listopad", 110.00);
		sortedtotalDistance.put("Grudzień", 100.00);
		
		theModel.addAttribute("counter", sortedCounter);
		theModel.addAttribute("totalDistance", sortedtotalDistance);
		
		
		return "statistics/monthly-statistics";
	}
	
	@GetMapping(value = {"/records"})
	public String records(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> allTrainings = trainingService.findByIsDoneAndUser(true, user);
		
		List<Training> best5km = allTrainings
				.stream()
				.filter(t -> Math.abs(t.getResult().getActDistance() - 5.0) < 0.01)
				.sorted((t1,t2) -> t1.getResult().getActTime().compareTo(t2.getResult().getActTime()))
				.limit(3)
				.collect(Collectors.toList());
		
		List<Training> best10km = allTrainings
				.stream()
				.filter(t -> Math.abs(t.getResult().getActDistance() - 10.0) < 0.01)
				.sorted((t1,t2) -> t1.getResult().getActTime().compareTo(t2.getResult().getActTime()))
				.limit(3)
				.collect(Collectors.toList());
		
		List<Training> best21km = allTrainings
				.stream()
				.filter(t -> Math.abs(t.getResult().getActDistance() - 21.0) < 0.01)
				.sorted((t1,t2) -> t1.getResult().getActTime().compareTo(t2.getResult().getActTime()))
				.limit(3)
				.collect(Collectors.toList());
		
		List<Training> best42km = allTrainings
				.stream()
				.filter(t -> Math.abs(t.getResult().getActDistance() - 42.0) < 0.01)
				.sorted((t1,t2) -> t1.getResult().getActTime().compareTo(t2.getResult().getActTime()))
				.limit(3)
				.collect(Collectors.toList());
		
		
		theModel.addAttribute("best5km", best5km);
		theModel.addAttribute("best10km", best10km);
		theModel.addAttribute("best21km", best21km);
		theModel.addAttribute("best42km", best42km);
		return "statistics/records";
	}
}
