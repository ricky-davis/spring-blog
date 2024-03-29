package com.codeup.springblog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 255)
    @NotBlank(message = "You must have an email!")
    @Email(message="[${validatedValue}] is not a valid email")
    private String email;

    @Column(nullable = false, unique = true, length = 255)
    @NotBlank(message = "You must have a username!")
    @Size(min = 3, message = "A username must be at least 3 characters.")
    private String username;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "You must have a password!")
    @Size(min = 8, message = "A password must be at least 8 characters.")
    @JsonIgnore
    private String password;

    @NotBlank(message = "You must confirm your password!")
    @JsonIgnore
    @Transient
    private String cnfmpassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonBackReference
    private List<Post> postList;

    public User() {
    }
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        cnfmpassword = copy.cnfmpassword;
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnfmpassword() {
        return cnfmpassword;
    }

    public void setCnfmpassword(String cnfmpassword) {
        this.cnfmpassword = cnfmpassword;
    }

}