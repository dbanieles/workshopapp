package com.devops.workshop.controller;

import com.devops.workshop.dto.Workshop;
import com.devops.workshop.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MetricController {

    private Integer _maxListItem = 1000;

    @Autowired
    private MetricService _metricService;

    @GetMapping("/useFor")
    public ResponseEntity<List<Workshop>> getWithFor(){
        List<Workshop> workshopList = _metricService.getDataWithFor(_maxListItem);
        return new ResponseEntity<List<Workshop>>(workshopList, HttpStatus.OK);
    }

    @GetMapping("/useWhile")
    public ResponseEntity<List<Workshop>> getWithForeach(){
        List<Workshop> workshopList = _metricService.getDataWithWhile(_maxListItem);
        return new ResponseEntity<List<Workshop>>(workshopList, HttpStatus.OK);
    }

}
