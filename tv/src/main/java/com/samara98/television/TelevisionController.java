package com.samara98.television;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tv")
public class TelevisionController {
    private final TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    //    create
    @PostMapping
    public Television createTelevision(@RequestBody Television newTelevision) {
        return televisionService.createTelevision(newTelevision);
    }

//    read all
    @GetMapping
    public List<Television> getTelevisionList() {
        return televisionService.getTelevisionList();
    }

//    read one
    @GetMapping(path = "{television_id}")
    public Television getTelevision(@PathVariable("television_id") Long televisionId) {
        return televisionService.getTelevision(televisionId);
    }

//    update
    @PatchMapping(path = "{television_id}")
    public Television updatTelevision(@RequestBody Television newTelevision, @PathVariable("television_id") Long televisionId) {
        return televisionService.updateTelevision(newTelevision, televisionId);
    }

//    delete
    @DeleteMapping(path = "{television_id}")
    public void deleteTelevision(@PathVariable("television_id") Long televisionId) {
        televisionService.deleteTelevision(televisionId);
    }
}
