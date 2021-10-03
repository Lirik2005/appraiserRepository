package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.conclusions.BusinessConclusion;
import com.example.appraiserbase.service.appraiser.AppraiserService;
import com.example.appraiserbase.service.businessCoclusion.BusinessConclusionService;
import com.example.appraiserbase.utils.AuthUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/conclusions")
public class BusinessConclusionController {

    private static final String BUSINESS_CONCLUSION_TABLE = "conclusions/businessConclusions/businessConclusionTable :: businessConclusion_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_MODAL = "conclusions/businessConclusions/modal/editConclusion";
    private static final String ADD_MODAL = "conclusions/businessConclusions/modal/addConclusion";

    private final AppraiserService appraiserService;
    private final BusinessConclusionService businessConclusionService;

    @Autowired
    public BusinessConclusionController(AppraiserService appraiserService, BusinessConclusionService businessConclusionService) {
        this.appraiserService = appraiserService;
        this.businessConclusionService = businessConclusionService;
    }

    @GetMapping("/businessConclusions")
    public String businessConclusions(Model model) {
        List<BusinessConclusion> businessConclusions = businessConclusionService.getAllConclusions();
        model.addAttribute("businessConclusions", businessConclusions);
        return "conclusions/businessConclusions/businessConclusion";
    }

    @GetMapping("/businessConclusions/addConclusion")
    public String addConclusion(Long pid, Model model) {
        try {
            final User currentAppraiser = (User) AuthUtil.getUserFromContext();
            final Optional<Appraiser> appraiserByLogin = appraiserService.getAppraiserByLogin(currentAppraiser.getUsername());
            model.addAttribute("appraisers", appraiserByLogin.orElseThrow(() -> new ServiceException("Пользователь отсутствует")));
           // model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("businessConclusions", new BusinessConclusion());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("/businessConclusions/saveConclusion")
    public String saveConclusion(BusinessConclusion conclusion, Model model) {
        try {
            businessConclusionService.addNewConclusion(conclusion);
            model.addAttribute("businessConclusions", businessConclusionService.getAllConclusions());
            model.addAttribute("message", "Заключение успешно добавлено в базу");
            model.addAttribute("alertClass", "alert-success");
            return BUSINESS_CONCLUSION_TABLE;
        } catch (Exception e) {
            model.addAttribute("businessConclusions", businessConclusionService.getAllConclusions());
            model.addAttribute("message", "Произошла ошибка. Заключение не добавлено в базу");
            model.addAttribute("alertClass", "alert-danger");
            return BUSINESS_CONCLUSION_TABLE;
        }
    }


}
