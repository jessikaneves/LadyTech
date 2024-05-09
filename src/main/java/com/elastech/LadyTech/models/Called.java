/*
package com.elastech.LadyTech.models;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Called {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCalled;

	@Column
	private Client client;

	@Column
	private String title;

	// adiconar update como false no db
	@Column
	private Timestamp timestamp;

	@Column
	private String priority;

	@Column
	private String description;

	// criar um enum para esses status
	@Column
	private String status;
		

	public Called() {
	}

	public long getIdCalled() {
		return idCalled;
	}

	public void setIdCalled(long idCalled) {
		this.idCalled = idCalled;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
*/
