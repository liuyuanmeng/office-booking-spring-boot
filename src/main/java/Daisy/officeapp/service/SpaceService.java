package Daisy.officeapp.service;


import Daisy.officeapp.model.Space;
import Daisy.officeapp.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository spaceRepository;

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Space getSpaceById(Long id) {
        return spaceRepository.findById(id).orElse(null);
    }
}
