package pl.kniewiadomski.runningApp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.kniewiadomski.runningApp.entity.Goal;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;

public interface GoalRepository extends JpaRepository<Goal, Integer>{	

    @Query("SELECT g FROM Goal g WHERE g.startDate <= :date and g.endDate >= :date and g.user = :user")
	public List<Goal> findByDate(@Param("date") Date date, @Param("user") User user);

	public List<Goal> findByUser(User user);
}
