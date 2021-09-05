package com.example.appraiserbase.service.appraiser;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AppraiserService {

    List<Appraiser> getAllAppraisers();

    List<Role> getAllRoles();

    Appraiser createNewAppraiser(Appraiser appraiser);

    Appraiser updateAppraiser(Appraiser appraiser);

    Appraiser getAppraiserById(Long pid);

    Optional<Appraiser> getAppraiserByLogin(String login);

    void deleteAppraiserById(Long pid);

    List<Appraiser> filterAppraiser(String searchText);
}
