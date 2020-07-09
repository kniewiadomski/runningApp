package pl.kniewiadomski.runningApp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniewiadomski.runningApp.enums.GoalType;
import pl.kniewiadomski.runningApp.validation.CompleteForm;

@Entity
@Valid
@Data
@NoArgsConstructor
@Table(name = "goal")
@CompleteForm(message = "Wypełnij wszystkie dostępne pola")
public class Goal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(
            fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "type")
	private GoalType type;
	
	@Column(name="start_date")
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Musisz określić początek celu")
	private Date startDate;
	
	@Column(name = "end_date")
	@Future
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull(message="Musisz określić koniec celu")
	private Date endDate;
	
	@Column(name="distance")
	@Min(value = 0, message = "Dystans musi być liczbą dodatnią")
	private Double distance;
	
	@Column(name="distance_progress")
	private Double distanceProgress;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "quantity")
	@Min(value = 0, message = "Ilość treningów musi być liczbą dodatnią")
	private Integer quantity;
	
	@Column(name = "quantity_progress")
	private Integer quantityProgress;
	
	@Column(name = "is_done")
	private Boolean isDone;


	@Override
	public String toString() {
		return "Goal [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", distance=" + distance
				+ ", time=" + time + ", quantity=" + quantity + "]";
	}
}
