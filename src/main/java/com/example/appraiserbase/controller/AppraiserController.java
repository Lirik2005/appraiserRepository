package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.service.appraiser.AppraiserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appraisers")
public class AppraiserController {

    private static final String APPRAISER_TABLE = "appraisers/appraiserTable :: appraiser_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_MODAL = "appraisers/modal/editAppraiser";
    private static final String ADD_MODAL = "appraisers/modal/addAppraiser";

    AppraiserService appraiserService;

    public AppraiserController(AppraiserService appraiserService) {
        this.appraiserService = appraiserService;
    }

    @GetMapping("/addAppraiser")
    public String addAppraiser(Model model) {
        try {
            model.addAttribute("predefinedRoles", appraiserService.getAllRoles());
            model.addAttribute("appraiser", new Appraiser());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("/saveAppraiser")
    public String saveAppraiser(Appraiser appraiser, Model model) {
        try {
            appraiserService.createNewAppraiser(appraiser);
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("message", "Оценщик успешно добавлен в базу");
            model.addAttribute("alertClass", "alert-success");
            return APPRAISER_TABLE;
        } catch (Exception e) {
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("message", "Произошла ошибка. Оценщик не добавлен в базу");
            model.addAttribute("alertClass", "alert-danger");
            return APPRAISER_TABLE;
        }
    }
}
