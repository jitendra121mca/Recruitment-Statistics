package com.recruitment.statistics.RecruitmentStatisticsService.repository;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Candidate;
        import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
}
