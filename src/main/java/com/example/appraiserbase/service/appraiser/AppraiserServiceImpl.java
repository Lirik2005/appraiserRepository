package com.example.appraiserbase.service.appraiser;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.model.Role;
import com.example.appraiserbase.repository.appraiserRepository.AppraiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppraiserServiceImpl implements AppraiserService {

    private final AppraiserRepository appraiserRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppraiserServiceImpl(AppraiserRepository appraiserRepository, PasswordEncoder passwordEncoder) {
        this.appraiserRepository = appraiserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<Appraiser> getAllAppraisers() {
        return appraiserRepository.findAll();
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return appraiserRepository.getAllRolesForAppraiser();
    }

    @Override
    @Transactional
    public Appraiser createNewAppraiser(Appraiser appraiser) {
        appraiser.setPassword(passwordEncoder.encode(appraiser.getPassword()));
        return appraiserRepository.save(appraiser);
    }

    @Override
    @Transactional
    public Appraiser updateAppraiser(Appraiser appraiser) {
        appraiser.setPassword(passwordEncoder.encode(appraiser.getPassword()));
        return appraiserRepository.save(appraiser);
    }

    @Override
    @Transactional(readOnly = true)
    public Appraiser getAppraiserById(Long pid) {
        return appraiserRepository.getById(pid);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Appraiser> getAppraiserByLogin(String login) {
        return appraiserRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void deleteAppraiserById(Long pid) {
        appraiserRepository.deleteById(pid);
    }

    @Override
    public List<Appraiser> filterAppraiser(String searchText) {
        return appraiserRepository.filterAppraiser(searchText);
    }
}
