package com.aiinterviewer.interviewSession.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class Session {

    @Id
    private String sessionId = UUID.randomUUID().toString();
    private String jobRole;
    private String jobDescription;
    private List<String> skills;
    private int experience;
    private List<QuestionAnswer> questionAnswers;
    private String status;

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
