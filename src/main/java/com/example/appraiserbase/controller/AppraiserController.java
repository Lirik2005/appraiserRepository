package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.service.appraiser.AppraiserService;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
            model.addAttribute("appraisers", new Appraiser());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @GetMapping("/checkLogin")
    public String checkLogin(@RequestParam("login") String login, Model model, HttpServletResponse response) {
        if (appraiserService.getAppraiserByLogin(login).isPresent()) {
            response.setStatus((HttpServletResponse.SC_CONFLICT));
            model.addAttribute("message", "Пользователь с таким логином уже зарегистрирован");
            model.addAttribute("alertClass", "alert-danger");
        }
        return ERROR_ALERT;
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

    @GetMapping("/editAppraiser")
    public String editAppraiser(Long pid, Model model) {
        try {
            Appraiser appraiser = appraiserService.getAppraiserById(pid);
            model.addAttribute("predefinedRoles", appraiserService.getAllRoles());
            model.addAttribute("appraisers", appraiser);
            return EDIT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return EDIT_MODAL;
        }
    }

    @PostMapping("/updateAppraiser")
    public String updateAppraiser(Appraiser appraiser, Model model) {
        try {
            appraiserService.updateAppraiser(appraiser);
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("message", "Оценщик успешно обновлен");
            model.addAttribute("alertClass", "alert-success");
            return APPRAISER_TABLE;
        } catch (Exception e) {
            model.addAttribute("message", "При редактировании оценщика возникла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return APPRAISER_TABLE;
        }
    }

    @RequestMapping(value = "/deleteAppraiser", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteAppraiser(Long pid, Model model) {
        try {
            appraiserService.deleteAppraiserById(pid);
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("message", "Оценщик успешно удален из базы");
            model.addAttribute("alertClass", "alert-success");
            return APPRAISER_TABLE;
        } catch (Exception e) {
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
            model.addAttribute("message", "Возникла ошибка при удалении из базы");
            model.addAttribute("alertClass", "alert-danger");
            return APPRAISER_TABLE;
        }
    }

    @GetMapping("/filter")
    public String filterAppraiser(String searchText, Model model) {
        List<Appraiser> appraiserList;
        try {
           if (searchText != null && !searchText.isEmpty()) {
                appraiserList = appraiserService.filterAppraiser(searchText);
            } else {
                appraiserList = appraiserService.getAllAppraisers();
            }
            model.addAttribute("appraisers", appraiserList);
            return APPRAISER_TABLE;
        } catch (Exception e) {
            model.addAttribute("appraisers", appraiserService.getAllAppraisers());
        model.addAttribute("message", "В результате поиска произошла ошибка");
        model.addAttribute("alertClass", "alert-danger");
        return APPRAISER_TABLE;

        }

    }


}
