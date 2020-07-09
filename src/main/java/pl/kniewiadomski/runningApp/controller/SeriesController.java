package pl.kniewiadomski.runningApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kniewiadomski.runningApp.entity.Series;
import pl.kniewiadomski.runningApp.entity.User;
import pl.kniewiadomski.runningApp.service.SeriesService;


@Controller
@RequestMapping("/series")
public class SeriesController {
	
private SeriesService seriesService;
	
	@Autowired
	public SeriesController(SeriesService theSeriesService) {
		
		seriesService = theSeriesService;
	}
	
	
	@GetMapping
	public String listSeries(Model theModel, @AuthenticationPrincipal User user) {
		
		List<Series> series = seriesService.findAll(user);
		
		theModel.addAttribute("series", series);
		
		return "/series/list-series";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Series theSeries = new Series();
		
		theModel.addAttribute("series", theSeries);
		
		return "series/series-form";
	}
	
	
	@PostMapping("/save")
	public String saveSeries(@Valid Series theSeries, BindingResult bindingResult, @AuthenticationPrincipal User user) {
			
		if (bindingResult.hasErrors()) {
			
			return "series/series-form";
		}
		
		theSeries.setUser(user);
		
		seriesService.save(theSeries);
		
		return "redirect:/series";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("seriesId") int theId, Model theModel) {
		
		Series theSeries = seriesService.findById(theId);
		
		theModel.addAttribute("series", theSeries);
		
		return "series/series-form";
	}
	
	@GetMapping("/delete")
	public String deleteTraining(@RequestParam("seriesId") int theId) {
		
		seriesService.deleteById(theId);
		
		return "redirect:/series";
	}
}
