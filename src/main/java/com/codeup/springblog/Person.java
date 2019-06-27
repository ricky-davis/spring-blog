package com.codeup.springblog;

import javax.persistence.*;

@Entity
@Table(name="people")
public class Person {
    @Id @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned not null auto_increment")
    private int id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "char(2) NOT NULL")
    private String resideState;



    public Person() { }

    public Person(int age, String name, String resideState) {
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }



    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
