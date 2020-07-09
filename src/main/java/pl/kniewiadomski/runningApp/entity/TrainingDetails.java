package pl.kniewiadomski.runningApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kniewiadomski.runningApp.enums.TrainingType;
import pl.kniewiadomski.runningApp.validation.ConstantCheck;
import pl.kniewiadomski.runningApp.validation.HrRangeCheck;
import pl.kniewiadomski.runningApp.validation.IntervalCheck;


@Entity
@Table(name = "training_details")
@HrRangeCheck(message = "Minimalny puls musi być mniejszy od maksymalnego")
@Data
@NoArgsConstructor
//@IntervalCheck(message = "Dla treningu interwałowego musisz określić długości interwałów i ich ilość")
//@ConstantCheck(message = "Dla treningu ciągłego musisz określić dystans lub/i czas")
public class TrainingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private TrainingType type;
	
	@Column(name="distance")
	@Min(value = 0, message = "Planowany dystans musi być wartością dodatnią")
	private Double distance;
	
	@Column(name="minHr")
	@Min(value = 0, message = "Puls musi być wartością dodatnią")
	private Integer minHr;
	
	@Column(name="maxHr")
	@Min(value = 0, message = "Puls musi być wartością dodatnią")
	private Integer maxHr;
	
	@Column(name="time")
	@Pattern(regexp="^[0-9]{2}:[0-5][0-9]:[0-5][0-9]$",message="Podaj czas w formacie HH:MM:SS")  
	private String time;
	
	@Column(name="fast_distance")
	@Min(value = 0, message = "Planowany dystans musi być wartością dodatnią")
	private Double fastDistance;
	
	@Column(name="recover_distance")
	@Min(value = 0, message = "Planowany dystans musi być wartością dodatnią")
	private Double recoverDistance;
	
	@Column(name="fast_time")
	@Pattern(regexp="^[0-9]{2}:[0-5][0-9]:[0-5][0-9]$",message="Podaj czas w formacie HH:MM:SS")  
	private String fastTime;
	
	@Column(name="recover_time")
	@Pattern(regexp="^[0-9]{2}:[0-5][0-9]:[0-5][0-9]$",message="Podaj czas w formacie HH:MM:SS") 
	private String recoverTime;
	
	@Column(name="interval_quantity")
	@Min(value = 0, message = "Ilość interwałów musi być wartością dodatnią")
	private Integer intervalQuantity;

	
	public Double getTotalDistance() {
		
		if(type == TrainingType.CONSTANT)
			return distance;
		
		if(type == TrainingType.DISTANCE_INTERVAL)
			return (fastDistance + recoverDistance) * intervalQuantity;
		
		return null;
	}

	@Override
	public String toString() {
		return "TrainingDetails [id=" + id + ", type=" + type + ", distance=" + distance
				+ ", minHr=" + minHr + ", maxHr=" + maxHr + ", time=" + time + ", fastDistance=" + fastDistance
				+ ", recoverDistance=" + recoverDistance + ", fastTime=" + fastTime + ", recoverTime=" + recoverTime
				+ ", intervalQuantity=" + intervalQuantity + "]";
	}
	
	
	
}
