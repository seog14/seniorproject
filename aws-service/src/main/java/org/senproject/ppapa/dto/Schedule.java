package org.senproject.ppapa.dto;

import org.senproject.ppapa.model.JsonModel;

public class Schedule extends JsonModel {

	private String user;
	private int hour;
	private int minute;
	private int month;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setUTC() {
		hour = (hour + 4) % 24; 
	}
}
