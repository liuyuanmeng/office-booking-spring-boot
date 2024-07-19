package Daisy.officeapp.controller;

import Daisy.officeapp.model.Space;
import Daisy.officeapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public List<Space> getAllSpaces() {
        return spaceService.getAllSpaces();
    }

    @GetMapping("/{id}")
    public Space getSpaceById(@PathVariable Long id) {
        return spaceService.getSpaceById(id);
    }
}

