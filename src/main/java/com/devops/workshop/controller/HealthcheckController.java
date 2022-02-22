package com.devops.workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HealthcheckController {

    @GetMapping("/healtcheck")
    public ResponseEntity<String> healthcheck(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
