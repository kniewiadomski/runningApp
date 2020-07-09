package pl.kniewiadomski.runningApp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Series;
import pl.kniewiadomski.runningApp.entity.User;

public interface SeriesRepository extends JpaRepository<Series, Integer>{
	
	public List<Series> findByUser(User user);
}
