package com.samara98.television;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public Television createTelevision(Television television) {
        return televisionRepository.save(television);
    }

    public List<Television> getTelevisionList() {
        return televisionRepository.findAll();
    }

    public Television getTelevision(Long televisionId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> new IllegalStateException("television with id " + televisionId + " does not exist"));
        return television;
    }

    @Transactional
    public Television updateTelevision(Television newTelevision, Long televisionId) {
        boolean exist = televisionRepository.existsById(televisionId);
        if (!exist) {
            throw new IllegalStateException("television with id " + televisionId + " does not exist");
        }

        Television updatedTelevision = televisionRepository.findById(televisionId).map(television -> {
            television.setTitle(newTelevision.getTitle());
            television.setOverview(newTelevision.getOverview());
            television.setPosterPath(newTelevision.getPosterPath());
            television.setPopularity(newTelevision.getPopularity());
            return televisionRepository.save(television);
        }).orElseGet(() -> {
            newTelevision.setId(televisionId);
            return televisionRepository.save(newTelevision);
        });

        return updatedTelevision;
    }

    public void deleteTelevision(Long televisionId) {
        boolean exist = televisionRepository.existsById(televisionId);
        if (!exist) {
            throw new IllegalStateException("television with id " + televisionId + " does not exist");
        }

        televisionRepository.deleteById(televisionId);
    }
}
