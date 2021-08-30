package com.example.appraiserbase.repository.appraiserRepository;

import com.example.appraiserbase.model.Appraiser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppraiserRepositoryCustomImpl implements AppraiserRepositoryCustom{
    
    @Override
    public List<Appraiser> filterAppraiser(String searchWord) {

        return null;
    }
}
