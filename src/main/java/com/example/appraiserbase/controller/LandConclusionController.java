package com.example.appraiserbase.controller;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.conclusions.LandConclusion;
import com.example.appraiserbase.service.appraiser.AppraiserService;
import com.example.appraiserbase.service.landConclusion.LandConclusionService;
import com.example.appraiserbase.utils.AuthUtil;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/conclusions")
public class LandConclusionController {

    private static final String LAND_CONCLUSION_TABLE = "conclusions/landConclusions/landConclusionTable :: landConclusion_list";
    private static final String ERROR_ALERT = "fragments/alert :: alert";
    private static final String EDIT_MODAL = "conclusions/landConclusions/modal/editConclusion";
    private static final String ADD_MODAL = "conclusions/landConclusions/modal/addConclusion";

    private final AppraiserService appraiserService;
    private final LandConclusionService landConclusionService;

    @Autowired
    public LandConclusionController(AppraiserService appraiserService, LandConclusionService landConclusionService) {
        this.appraiserService = appraiserService;
        this.landConclusionService = landConclusionService;
    }

    @GetMapping("/landConclusions")
    public String landConclusions(Model model) {
        List<LandConclusion> landConclusions = landConclusionService.getAllConclusions();
        model.addAttribute("landConclusions", landConclusions);
        return "conclusions/landConclusions/landConclusion";
    }

    @GetMapping("/landConclusions/addConclusion")
    public String addConclusion(Long pid, Model model) {
        try {
            final User currentAppraiser = (User) AuthUtil.getUserFromContext();
            final Optional<Appraiser> appraiserByLogin = appraiserService.getAppraiserByLogin(currentAppraiser.getUsername());
            model.addAttribute("appraisers", appraiserByLogin.orElseThrow(() -> new ServiceException("Пользователь отсутствует")));
            model.addAttribute("landConclusions", new LandConclusion());
            return ADD_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ADD_MODAL;
        }
    }

    @PostMapping("/landConclusions/saveConclusion")
    public String saveConclusion(LandConclusion conclusion, Model model) {
        try {
            landConclusionService.addNewConclusion(conclusion);
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "Заключение успешно добавлено в базу");
            model.addAttribute("alertClass", "alert-success");
            return LAND_CONCLUSION_TABLE;
        } catch (Exception e) {
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "Произошла ошибка. Заключение не добавлено в базу");
            model.addAttribute("alertClass", "alert-danger");
            return LAND_CONCLUSION_TABLE;
        }
    }

   @GetMapping("/landConclusions/editConclusion")
    public String editLandConclusion(Long pid, Model model) {
        try {
            LandConclusion landConclusion = landConclusionService.getLandConclusionById(pid);

            model.addAttribute("landConclusions", landConclusion);
            return EDIT_MODAL;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return EDIT_MODAL;
        }
    }

    @PostMapping("/landConclusions/updateConclusion")
    public String updateConclusion (LandConclusion landConclusion, Model model) {
        try {
            landConclusionService.updateConclusion(landConclusion);
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "Заключение успешно обновлено");
            model.addAttribute("alertClass", "alert-success");
            return LAND_CONCLUSION_TABLE;
        } catch (Exception e) {
            model.addAttribute("message", "При редактировании заключения возникла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return LAND_CONCLUSION_TABLE;
        }
    }


    @RequestMapping(value = "/landConclusions/deleteConclusion", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteConclusion(Long pid, Model model) {
        try {
            landConclusionService.deleteConclusion(pid);
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "Заключение успешно удалено из базы");
            model.addAttribute("alertClass", "alert-success");
            return LAND_CONCLUSION_TABLE;
        } catch (Exception e) {
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "Возникла ошибка при удалении из базы");
            model.addAttribute("alertClass", "alert-danger");
            return LAND_CONCLUSION_TABLE;
        }
    }

    @GetMapping("/landConclusions/filter")
    public String landConclusionFilter(String searchLandText, Model model) {
        List<LandConclusion> landConclusionList;
        try {
            if (searchLandText != null && !searchLandText.isEmpty()) {
                landConclusionList = landConclusionService.landConclusionFilter(searchLandText);
            } else {
                landConclusionList = landConclusionService.getAllConclusions();
            }
            model.addAttribute("landConclusions", landConclusionList);
            return LAND_CONCLUSION_TABLE;
        } catch (Exception e) {
            model.addAttribute("landConclusions", landConclusionService.getAllConclusions());
            model.addAttribute("message", "В результате поиска произошла ошибка");
            model.addAttribute("alertClass", "alert-danger");
            return LAND_CONCLUSION_TABLE;
        }
    }





}
