package com.recruitment.statistics.RecruitmentStatisticsService.service;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.model.Panel;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.CandidateRepository;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.OrganizationRepository;
import com.recruitment.statistics.RecruitmentStatisticsService.repository.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HiringOperationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    PanelRepository panelRepository;

    @Autowired
    CandidateRepository candidateRepository;

    public List<Organization> getPanelResults(){

        return (List<Organization>) organizationRepository.findAll();
    }

    public Organization getPanelResults(Long organizationId){

        return  organizationRepository.findById(organizationId).get();
    }

    public Organization createOrganization(Organization organization) {
        organization.getCandidate().forEach(c -> {
            c.getPanels().forEach(p -> {
                panelRepository.save(p);
            });
            candidateRepository.save(c);
        });
               return organizationRepository.save(organization);
    }

    public void updateOrganization(Organization organization) {
        try {
            organization.getCandidate().forEach(c -> {
                c.getPanels().forEach(p -> {
                    panelRepository.save(p);
                });
                candidateRepository.save(c);
            });
            organizationRepository.save(organization);
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("this is the object" + organization);
        }
    }

}
