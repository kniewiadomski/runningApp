package pl.kniewiadomski.runningApp.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniewiadomski.runningApp.validation.FutureIfUndone;
import pl.kniewiadomski.runningApp.validation.PastIfDone;

@Entity
@Table(name = "training")
@Data
@NoArgsConstructor
@FutureIfUndone(message = "Zaplanowany trening musi mieć datę z przyszłości.")
@PastIfDone(message = "Wykonany trening musi mieć datę z przeszłości.")
public class Training {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(
            fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="isDone")
	private Boolean isDone;
	
	@Column(name="name")
	@NotEmpty(message="Musisz nazwać swój trening")
	private String name;
	
	@Column(name="date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull(message="Musisz określić datę swojego treningu")
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "series")
	private Series series;
	
	@OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "training_details_id")
	@Valid
	private TrainingDetails details;
	
	@OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "training_result_id")
	@Valid
	private TrainingResult result;
	
}
