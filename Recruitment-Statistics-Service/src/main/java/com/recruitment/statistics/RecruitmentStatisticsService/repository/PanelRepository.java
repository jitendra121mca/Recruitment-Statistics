package com.recruitment.statistics.RecruitmentStatisticsService.repository;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Candidate;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Panel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelRepository extends CrudRepository<Panel, Long> {
}

