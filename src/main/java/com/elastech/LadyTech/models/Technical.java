package com.elastech.LadyTech.models;

import jakarta.persistence.*;

@Entity
public class Technical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tec;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String departament;
    // chamando o conjunto enum UserType
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.TECNICO;

    public Technical() {
    }

    public long getId_tec() {
        return id_tec;
    }

    public void setId_tec(long id_tec) {
        this.id_tec = id_tec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
