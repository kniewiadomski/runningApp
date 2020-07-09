package pl.kniewiadomski.runningApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Series;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.TrainingDetails;
import pl.kniewiadomski.runningApp.entity.TrainingResult;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.enums.GoalType;
import pl.kniewiadomski.runningApp.enums.TrainingType;
import pl.kniewiadomski.runningApp.filters.HistoryFilter;
import pl.kniewiadomski.runningApp.filters.PlanFilter;
import pl.kniewiadomski.runningApp.service.GoalService;
import pl.kniewiadomski.runningApp.service.SeriesService;
import pl.kniewiadomski.runningApp.service.TrainingService;


@Controller
@RequestMapping("/trainings")
public class TrainingController {

	private TrainingService trainingService;

	private GoalService goalService;
	
	private SeriesService seriesService;
	
	@Autowired
	public TrainingController(TrainingService theTrainingService, GoalService theGoalService, SeriesService theSeriesService) {
		
		trainingService = theTrainingService;
		goalService = theGoalService;
		seriesService = theSeriesService;
	}
	
	@GetMapping("/plan")
	public String listPlanTrainings(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> theTrainings = trainingService.findByIsDoneAndUser(false, user);
		
		theModel.addAttribute("trainings", theTrainings);
		
		List<Series> series = seriesService.findAll(user);
		
		PlanFilter planFilter = new PlanFilter();
		
		theModel.addAttribute("filter", planFilter);
		
		theModel.addAttribute("seriesList", series);
		
		return "trainings/list-plan";
	}
	
	@GetMapping("/history")
	public String listHistoryTrainings(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> theTrainings = trainingService.findByIsDoneAndUser(true, user);
		
		theModel.addAttribute("trainings", theTrainings);
		
		HistoryFilter historyFilter = new HistoryFilter();
		
		theModel.addAttribute("filter", historyFilter);
		
		return "trainings/list-history";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel, @AuthenticationPrincipal User user) {
		
		Training theTraining = new Training();
		
		TrainingDetails theDetails = new TrainingDetails();
		
		List<Series> series = seriesService.findAll(user);
		
		theTraining.setDetails(theDetails);
		
		theTraining.setIsDone(false);
		
		theModel.addAttribute("training", theTraining);
		
		theModel.addAttribute("seriesList", series);
		
		return "trainings/training-form";
	}
	
	@PostMapping("/save")
	public String saveTraining(@Valid Training theTraining, BindingResult bindingResult, @AuthenticationPrincipal User user) {
		
		
		if (bindingResult.hasErrors()) {
			
			if(theTraining.getIsDone() == false) {
				return "trainings/training-form";
			}
			else {
				return "trainings/submit-form";
			}
		}
		
		theTraining.setUser(user);
		
		trainingService.save(theTraining);
		
		if(theTraining.getIsDone() == true) {
			
			checkGoalsStatus(theTraining.getDate(), user);
		}
		
		return "redirect:/trainings/showDetails?trainingId="+theTraining.getId();
	}

	@GetMapping("/updatePlannedTraining")
	public String updatePlannedTraining(@RequestParam("trainingId") int theId, Model theModel, @AuthenticationPrincipal User user) {
		
		Training theTraining = trainingService.findById(theId);
		
		List<Series> series = seriesService.findAll(user);
		
		theModel.addAttribute("training", theTraining);
		
		theModel.addAttribute("seriesList", series);
		
		return "trainings/training-form";
	}
	
	@GetMapping("/updateDoneTraining")
	public String updateDoneTraining(@RequestParam("trainingId") int theId, Model theModel) {
		
		Training theTraining = trainingService.findById(theId);
		
		theModel.addAttribute("training", theTraining);
		
		return "trainings/submit-form";
	}
	
	@GetMapping("/delete")
	public String deleteTraining(@RequestParam("trainingId") int theId, @AuthenticationPrincipal User user) {
		
		Training theTraining = trainingService.findById(theId);
		
		trainingService.delete(theTraining);
		
		if(theTraining.getIsDone() == true) {
			
			checkGoalsStatus(theTraining.getDate(), user);
		}
		
		return "redirect:/trainings/plan";
	}
	
	@GetMapping("/showDetails")
	public String showTrainingDetails(@RequestParam("trainingId") int theId, Model theModel) {
		
		Training theTraining = trainingService.findById(theId);
		
		theModel.addAttribute("training", theTraining);
		
		if(theTraining.getIsDone() == false) return "trainings/training-details";
		else return "trainings/history-details";
	}
	
	@GetMapping("/submitTraining")
	public String submitTraining(@RequestParam("trainingId") int theId, Model theModel) {
		
		Training theTraining = trainingService.findById(theId);
		
		theTraining.setIsDone(true);
		
		TrainingResult result = new TrainingResult();
		
		theTraining.setResult(result);
		
		theModel.addAttribute("training", theTraining);
		
		return "trainings/submit-form";
	}
	
	@PostMapping("/filterPlan")
	public String filterPlan(@ModelAttribute("filter") PlanFilter planFilter, Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> theTrainings = trainingService.getFilteredPlan(planFilter, user);
		
		List<Series> series = seriesService.findAll(user);
		
		theModel.addAttribute("trainings", theTrainings);
		
		theModel.addAttribute("seriesList", series);
		
		return "trainings/list-plan";
	}
	
	@PostMapping("/filterHistory")
	public String filterHistory(@ModelAttribute("filter") HistoryFilter historyFilter, Model theModel, @AuthenticationPrincipal User user) {
		
		List<Training> theTrainings = trainingService.getFilteredHistory(historyFilter, user);
		
		theModel.addAttribute("trainings", theTrainings);
		
		return "trainings/list-history";
	}
	
	private void checkGoalsStatus(Date date, User user) {
		
		List<Goal> goals = goalService.findWithDate(date, user);
		
		List<Training> trainings = trainingService.findByIsDoneAndUser(true, user);
		
		goalService.updateGoalsStatus(goals, trainings);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    
	    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	    
	    binder.registerCustomEditor(String.class, stringTrimmerEditor);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
