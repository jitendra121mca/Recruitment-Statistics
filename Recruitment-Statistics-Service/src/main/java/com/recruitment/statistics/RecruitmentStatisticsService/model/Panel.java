package com.recruitment.statistics.RecruitmentStatisticsService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Panel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String panelResult;
    private String overAllResult;
    private int interviewDuration;

    public Panel() {
    }

    public Panel(Long id, String description, String panelResult, String overAllResult, int interviewDuration) {
        this.id = id;
        this.description = description;
        this.panelResult = panelResult;
        this.overAllResult = overAllResult;
        this.interviewDuration = interviewDuration;
    }

    public Panel(String description, String panelResult, String overAllResult, int interviewDuration) {
        this.description = description;
        this.panelResult = panelResult;
        this.overAllResult = overAllResult;
        this.interviewDuration = interviewDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPanelResult() {
        return panelResult;
    }

    public void setPanelResult(String panelResult) {
        this.panelResult = panelResult;
    }

    public String getOverAllResult() {
        return overAllResult;
    }

    public void setOverAllResult(String overAllResult) {
        this.overAllResult = overAllResult;
    }

    public int getInterviewDuration() {
        return interviewDuration;
    }

    public void setInterviewDuration(int interviewDuration) {
        this.interviewDuration = interviewDuration;
    }

    @Override
    public String toString() {
        return "Panel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", panelResult='" + panelResult + '\'' +
                ", overAllResult='" + overAllResult + '\'' +
                ", interviewDuration=" + interviewDuration +
                '}';
    }
}
