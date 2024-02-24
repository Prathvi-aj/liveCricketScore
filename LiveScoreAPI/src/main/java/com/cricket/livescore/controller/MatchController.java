package com.cricket.livescore.controller;

import com.cricket.livescore.dto.CricketInfoDto;
import com.cricket.livescore.model.CricketInfo;
import com.cricket.livescore.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cricket")
@CrossOrigin("*")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("live")
    ResponseEntity<List<CricketInfo>> getLiveMatches(){
        return new ResponseEntity<>(matchService.getLiveMatches(), HttpStatus.OK);

    }
    @GetMapping
    ResponseEntity<List<CricketInfo>> getAllMatches(String status){
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);

    }
    @GetMapping("matchDetails/{matchName}")
    ResponseEntity<CricketInfo> getMatcheDetails(String matchName){
        return new ResponseEntity<>(matchService.getMatcheDetails(matchName), HttpStatus.OK);

    }
    @GetMapping("/cwc-point-table")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(matchService.getCWC2023PointTable(), HttpStatus.OK);
    }

    @GetMapping("/wpl-point-table")
    public ResponseEntity<?> getWPL2023PointTable() {
        return new ResponseEntity<>(matchService.getWPL2024PointTable(), HttpStatus.OK);
    }

}
