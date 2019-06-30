package com.example.recruitementapplication.models;

import java.util.Date;

public class Interview {
String idInterview;
Recruiter recruiter;
Candidate candidate;
Date localDateTime;

    public Interview(String idInterview, Recruiter recruiter, Candidate candidate, Date localDateTime) {
        this.idInterview = idInterview;
        this.recruiter = recruiter;
        this.candidate = candidate;
        this.localDateTime = localDateTime;
    }
}
