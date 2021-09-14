package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.service.appraiser.AppraiserService;
import com.example.appraiserbase.service.appraiser.AppraiserServiceImpl;
import com.example.appraiserbase.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NavigationController {

    private final AppraiserService appraiserService;

    @Autowired
    public NavigationController(AppraiserService appraiserService) {
        this.appraiserService = appraiserService;
    }

    @RequestMapping(value = {"/", "/appraisers"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String appraisers(Model model) {
        User user = (User) AuthUtil.getUserFromContext();
        List<Appraiser> appraiserList = appraiserService.getAllAppraisers();
        model.addAttribute("appraisers", appraiserList);
        return "appraisers/appraiser";
    }

    @GetMapping("/conclusions")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PREMIUM')")
    public String conclusions () {
        return "conclusions/conclusions";
    }


}
