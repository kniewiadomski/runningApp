package pl.kniewiadomski.runningApp.service;

import java.util.List;

import pl.kniewiadomski.runningApp.entity.Series;
import pl.kniewiadomski.runningApp.entity.User;

public interface SeriesService {
	
	public List<Series> findAll(User user);
	
	public Series findById(int theId);
	
	public void save(Series theSeries);
	
	public void deleteById(int theId);
}
