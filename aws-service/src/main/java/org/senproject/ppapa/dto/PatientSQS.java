package org.senproject.ppapa.dto;

import org.senproject.ppapa.model.JsonModel;

public class PatientSQS extends JsonModel{

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	} 
	
}
