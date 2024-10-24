package com.balejko.spring.projectone.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int person_id;
    @NotEmpty(message = "Имя пользователя не должно быть пустым")
    @Size(min = 2,max=100,message = "Имя должно быть длиной от 2 до 100 символов")
    private String fullName;
    @Min(value = 1900,message = "Год рождения должен быть больше чем 1900")
    private int yearOfBirth;
    public Person() {
    }

    public Person(int person_id, String fullName, int yearOfBirth) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
