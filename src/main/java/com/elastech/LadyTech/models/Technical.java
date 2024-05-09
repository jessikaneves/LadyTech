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


@Entity
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
	private String departament;

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

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
