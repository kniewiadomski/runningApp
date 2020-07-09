package pl.kniewiadomski.runningApp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kniewiadomski.runningApp.entity.Training;
import pl.kniewiadomski.runningApp.entity.User;

public interface TrainingRepository extends JpaRepository<Training, Integer> {

    public List<Training> findByIsDoneAndUserOrderByDate(Boolean isDone, User user);
}
