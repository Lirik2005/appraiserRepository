package com.example.appraiserbase.repository.appraiserRepository;

import com.example.appraiserbase.model.Appraiser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraiserRepositoryCustom {

    List <Appraiser> filterAppraiser(String searchText);

}
