package com.recruitment.statistics.RecruitmentStatisticsService.model;

import java.util.List;

public class Statistics {
    private List<Organization> organization;

    public Statistics() {
    }

    public Statistics(List<Organization> organization) {
        this.organization = organization;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Organization> organization) {
        this.organization = organization;
    }
}
