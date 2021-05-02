package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.StatusEntity;
import com.feeldip.spring.claimer.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;


    @GetMapping("/all")
    public ResponseEntity getStatuses(){
        return ResponseEntity.ok(statusService.getAllStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneStatusById(@PathVariable Long id){
        return ResponseEntity.ok(statusService.getStatusByID(id));
    }


    @PostMapping("/")
    public ResponseEntity addNewStatus(@RequestBody StatusEntity statusEntity){
        statusService.saveOrUpdateStatus(statusEntity);
        return ResponseEntity.ok("New status was added");
    }


    @PutMapping("/")
    public ResponseEntity updateStatus(@RequestBody StatusEntity statusEntity){
        statusService.saveOrUpdateStatus(statusEntity);
        return ResponseEntity.ok("Status was changed");
    }


    @DeleteMapping("/all")
    public ResponseEntity deleteStatuses(){
        statusService.deleteAllStatuses();
        return ResponseEntity.ok("All statuses were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStatusById(@PathVariable Long id){
        statusService.deleteStatusById(id);
        return ResponseEntity.ok("Status with id \"" + id + "\" was removed");
    }
}
