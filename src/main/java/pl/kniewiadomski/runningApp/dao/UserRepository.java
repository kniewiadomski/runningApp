package pl.kniewiadomski.runningApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kniewiadomski.runningApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
