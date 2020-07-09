package pl.kniewiadomski.runningApp.filters;

import java.sql.Date;

public class HistoryFilter {

	Double distanceFrom;
	Double distanceTo;
	Date dateFrom;
	Date dateTo;
	String timeFrom;
	String timeTo;
	Integer hrFrom;
	Integer hrTo;
	
	public HistoryFilter() {
	}

	public HistoryFilter(Double distanceFrom, Double distanceTo, Date dateFrom, Date dateTo, String timeFrom,
			String timeTo, Integer hrFrom, Integer hrTo) {
		this.distanceFrom = distanceFrom;
		this.distanceTo = distanceTo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.hrFrom = hrFrom;
		this.hrTo = hrTo;
	}

	public Double getDistanceFrom() {
		return distanceFrom;
	}

	public void setDistanceFrom(Double distanceFrom) {
		this.distanceFrom = distanceFrom;
	}

	public Double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(Double distanceTo) {
		this.distanceTo = distanceTo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public Integer getHrFrom() {
		return hrFrom;
	}

	public void setHrFrom(Integer hrFrom) {
		this.hrFrom = hrFrom;
	}

	public Integer getHrTo() {
		return hrTo;
	}

	public void setHrTo(Integer hrTo) {
		this.hrTo = hrTo;
	}

	
}
