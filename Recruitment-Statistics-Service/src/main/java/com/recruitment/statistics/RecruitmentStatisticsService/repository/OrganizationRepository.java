package com.recruitment.statistics.RecruitmentStatisticsService.repository;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
