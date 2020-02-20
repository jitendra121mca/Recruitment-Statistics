package com.recruitment.statistics.RecruitmentStatisticsService.controller;

import com.recruitment.statistics.RecruitmentStatisticsService.model.Organization;
import com.recruitment.statistics.RecruitmentStatisticsService.service.HiringOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/hiringoperation")
public class HiringOperationController {

    @Autowired
    HiringOperationService hiringOperationService;

    @GetMapping("/results")
    public List<Organization> getPanelResults(){

        return  hiringOperationService.getPanelResults();
    }

    @GetMapping("/results/{id}")
    public Organization getPanelResults(@PathVariable(value = "id") Long organizationId){

        return  hiringOperationService.getPanelResults(organizationId);
    }

    @PostMapping("/createorg")
    public Organization createOrganization(@RequestBody Organization organization) {
               return hiringOperationService.createOrganization(organization);
    }

    @PutMapping("/updateorg")
    public void updateOrganization(@RequestBody Organization organization) {
         hiringOperationService.updateOrganization(organization);
    }
}
