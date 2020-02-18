package com.recruitment.statistics.RecruitmentStatisticsService;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Candidate;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Panel;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.CandidateRepository;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.OrganizationRepository;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.PanelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class RecruitmentStatisticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentStatisticsServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner getCommandLinerExecutor(OrganizationRepository organizationRepository, CandidateRepository candidateRepository, PanelRepository panelRepository){
		return (args) -> {

			Panel p1 = new Panel("p1 desc", "YES_FOR_NEXT_ROUND","SELECT", 1);
			Panel p2 = new Panel("p2 desc", "REJECT","REJECTED", 2);
			Panel p3 = new Panel("p3 desc", "YES_FOR_NEXT_ROUND","REJECTED", 3);
			panelRepository.saveAll(Arrays.asList(p1,p2,p3));

			Candidate c1 = new Candidate("C1", Arrays.asList(p1,p2,p3));
			candidateRepository.save(c1);
			Organization o1 = new Organization("O1","O1 Desc", Arrays.asList(c1));
			organizationRepository.save(o1);
		};
	}

}
