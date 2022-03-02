package com.psoft.match.tcc.model.user;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    private String fullName;

    private String email;

    public Admin() {}

    public Admin(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(UserRole.ADMIN_USER);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
