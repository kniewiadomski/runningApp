package pl.kniewiadomski.runningApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "training_result")
public class TrainingResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="act_distance")
	@Min(value = 0, message = "Planowany dystans musi być wartością dodatnią")
	@NotNull(message = "Musisz podać dystans wykonanego treningu")
	private Double actDistance;
	
	@Column(name="act_Hr")
	@Min(value = 0, message = "Puls musi być wartością dodatnią")
	private Integer actHr;
	
	@Column(name="act_time")
	@NotEmpty(message="Musisz podać czas wykonanego treningu")
	@Pattern(regexp="^[0-9]{2}:[0-5][0-9]:[0-5][0-9]$",message="Podaj czas w formacie HH:MM:SS")  
	private String actTime;


}
