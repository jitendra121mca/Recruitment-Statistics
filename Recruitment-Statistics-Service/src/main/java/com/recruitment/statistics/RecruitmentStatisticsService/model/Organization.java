package com.recruitment.statistics.RecruitmentStatisticsService.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany()
    private List<Candidate> candidate;

    @Version
    private int version;

    public Organization() {
    }

    public Organization(Long id, String name, String description, List<Candidate> candidate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.candidate = candidate;
    }

    public Organization(String name, String description, List<Candidate> candidate) {
        this.name = name;
        this.description = description;
        this.candidate = candidate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(List<Candidate> candidate) {
        this.candidate = candidate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", candidate=" + candidate.stream().collect(Collectors.toList()) +
                ", version=" + version +
                '}';
    }
}
