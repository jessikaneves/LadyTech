package com.elastech.LadyTech.models;

import com.elastech.LadyTech.models.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tecnico")
public class Technical {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTechnical;
	@Column
	private String name;

	@Column
	private String userName;
	@Column
	private String email;
	@Column
	private String password;

	@Column
	@Enumerated(EnumType.STRING)
	private UserType userType = UserType.TECHNICAL;

	@ManyToOne
	@JoinColumn(name = "id_administrator")
	private Administrator administrator;

	@Column(name = "name_administrator")
	private String administratorName;

	public Technical() {
	}

	public long getIdTechnical() {
		return idTechnical;
	}

	public void setIdTechnical(long idTechnical) {
		this.idTechnical = idTechnical;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

}
