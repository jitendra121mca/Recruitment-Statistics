package com.recruitment.statistics.RecruitmentStatisticsService.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<Panel> panels;

    public Candidate() {
    }

    public Candidate(Long id, String name, List<Panel> panels) {
        this.id = id;
        this.name = name;
        this.panels = panels;
    }

    public Candidate(String name, List<Panel> panels) {
        this.name = name;
        this.panels = panels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Panel> getPanels() {
        return panels;
    }

    public void setPanels(List<Panel> panels) {
        this.panels = panels;
    }
}
