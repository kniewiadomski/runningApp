package pl.kniewiadomski.runningApp.filters;

import java.sql.Date;

import pl.kniewiadomski.runningApp.entity.Series;

public class PlanFilter {

	Date dateFrom;
	Date dateTo;
	Series series;
	
	public PlanFilter() {
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

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
	
	
}
