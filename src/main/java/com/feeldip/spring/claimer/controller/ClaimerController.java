package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.ClaimerEntity;
import com.feeldip.spring.claimer.service.ClaimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claimer")
public class ClaimerController {

    @Autowired
    ClaimerService claimerService;


    @GetMapping("/all")
    public ResponseEntity getClaimers(){
        return ResponseEntity.ok(claimerService.getAllClaimers());
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneClaimerById(@PathVariable Long id){
        return ResponseEntity.ok((claimerService.getClaimerID(id)));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity getOneClaimerByEmail(@PathVariable String email){
        return ResponseEntity.ok((claimerService.getClaimerByEmail(email)));
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity getOneClaimerByPhone(@PathVariable String phone){
        return ResponseEntity.ok((claimerService.getClaimerByPhone(phone)));
    }


    @PostMapping("/")
    public ResponseEntity addNewClaimer(@RequestBody ClaimerEntity claimerEntity){
        claimerService.saveOrUpdateClaimer(claimerEntity);
        return ResponseEntity.ok("New claimer was added");
    }


    @PutMapping("/")
    public ResponseEntity updateNewClaimer(@RequestBody ClaimerEntity claimerEntity){
        claimerService.saveOrUpdateClaimer(claimerEntity);
        return ResponseEntity.ok("Claimer was changed");
    }


    @DeleteMapping("/all")
    public ResponseEntity deleteClaimers(){
        claimerService.deleteAllClaimer();
        return ResponseEntity.ok("All claimers were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneClaimerById(@PathVariable Long id){
        claimerService.deleteClaimerById(id);
        return ResponseEntity.ok("Claimer with id \"" + id + "\" was removed");
    }
    @DeleteMapping("/email/{email}")
    public ResponseEntity deleteOneClaimerByEmail(@PathVariable String email){
        claimerService.deleteClaimerByEmail(email);
        return ResponseEntity.ok("Claimer with email \"" + email + "\" was removed");
    }
    @DeleteMapping("/phone/{phone}")
    public ResponseEntity deleteOneClaimerByPhone(@PathVariable String phone){
        claimerService.deleteClaimerByPhone(phone);
        return ResponseEntity.ok("Claimer with phone \"" + phone + "\" was removed");
    }

}
