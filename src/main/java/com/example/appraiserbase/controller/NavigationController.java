package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.service.appraiser.AppraiserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    AppraiserServiceImpl appraiserService;

    @RequestMapping(value = "/appraisers", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String appraisers(Model model) {
        final List<Appraiser> allAppraisers = appraiserService.getAllAppraisers();
        model.addAttribute("appraisers", allAppraisers);
        return "appraisers/appraiser";
    }


}
