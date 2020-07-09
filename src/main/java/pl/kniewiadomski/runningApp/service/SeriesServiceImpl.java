package pl.kniewiadomski.runningApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kniewiadomski.runningApp.dao.SeriesRepository;
import pl.kniewiadomski.runningApp.entity.Series;
import pl.kniewiadomski.runningApp.entity.User;

@Service
public class SeriesServiceImpl implements SeriesService {

	
	private SeriesRepository seriesRepository;
	
	@Autowired
	public SeriesServiceImpl(SeriesRepository theSeriesRepository) {
		seriesRepository = theSeriesRepository;
	}
	
	@Override
	public List<Series> findAll(User user) {
		
		return seriesRepository.findByUser(user);
	}

	@Override
	public Series findById(int theId) {
		
		Optional<Series> result = seriesRepository.findById(theId);
		
		Series theSeries = null;
		
		if (result.isPresent()) {
			theSeries = result.get();
		}
		else {
			throw new RuntimeException("Did not find series id - " + theId);
		}
		
		return theSeries;
	}

	@Override
	public void save(Series theSeries) {
		
		seriesRepository.save(theSeries);
	}

	@Override
	public void deleteById(int theId) {
		
		seriesRepository.deleteById(theId);
	}

}
