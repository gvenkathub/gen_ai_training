package com.epam.training.gen.ai.controller;

import com.epam.training.gen.ai.dto.VacationDetails;
import com.epam.training.gen.ai.service.VacationService;
import com.microsoft.semantickernel.services.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/vacation-planner")
public class VacationPlannerController {

    @Autowired
    private VacationService vacationService;

    @PostMapping(path = "/plan")
    public String getVacationPlan(@RequestBody VacationDetails vacationDetails) throws ServiceNotFoundException {
        return vacationService.fetchVacationDetails(vacationDetails);
    }

}
