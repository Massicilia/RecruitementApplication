package com.example.recruitementapplication.models;

public class Recruiter {
    String uuid;
    String firstname;
    String lastname;
    String mail;
    int experience;
    String enterprise;
    Skill[] skills;
    Skill[] keySkills;

    public Recruiter(String uuid, String firstname, String lastname, String mail, int experience, String enterprise, Skill[] skills, Skill[] keySkills) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.experience = experience;
        this.enterprise = enterprise;
        this.skills = skills;
        this.keySkills = keySkills;
    }
}
