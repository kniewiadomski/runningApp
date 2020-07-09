package pl.kniewiadomski.runningApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.service.GoalService;

@Controller
@RequestMapping("/goals")
public class GoalController {

	private GoalService goalService;
	
	@Autowired
	public GoalController(GoalService theGoalService) {
		
		goalService = theGoalService;
	}
	
	@GetMapping("/list")
	public String listGoals(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Goal> theGoals = goalService.findAll(user);
		
		theModel.addAttribute("goals", theGoals);
		
		return "goals/list-goals";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Goal theGoal = new Goal();
		
		theGoal.setIsDone(false);
		
		theModel.addAttribute("goal", theGoal);
		
		return "goals/goal-form";
	}
	
	@PostMapping("/save")
	public String saveGoal(@Valid Goal theGoal, BindingResult bindingResult, @AuthenticationPrincipal User user) {
			
		if (bindingResult.hasErrors()) {
			
			return "goals/goal-form";
		}
		
		theGoal.setUser(user);
		
		goalService.save(theGoal);
		
		return "redirect:/goals/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("goalId") int theId, Model theModel) {
		
		Goal theGoal = goalService.findById(theId);
		
		theModel.addAttribute("goal", theGoal);
		
		return "goals/goal-form";
	}
	
	@GetMapping("/delete")
	public String deleteTraining(@RequestParam("goalId") int theId) {
		
		goalService.deleteById(theId);
		
		return "redirect:/goals/list";
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
