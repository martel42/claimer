package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.ClaimEntity;
import com.feeldip.spring.claimer.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;


    @GetMapping("/all")
    public ResponseEntity getClaims() throws InterruptedException {
        return ResponseEntity.ok(claimService.getAllClaims());
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneClaimById(@PathVariable Long id){
        return ResponseEntity.ok(claimService.getClaimByID(id));
    }
    @GetMapping("/text/{id}")
    public ResponseEntity getTextClaim(@PathVariable Long id){
        return ResponseEntity.ok(claimService.getTextClaimById(id));
    }
    @GetMapping("/claimer/{id}")
    public ResponseEntity getOneClaimByClaimerId(@PathVariable Long id){
        return ResponseEntity.ok(claimService.getClaimerClaims(id));
    }
    @GetMapping("/unanswered/all")
    public ResponseEntity getUnansweredClaims(){
        return ResponseEntity.ok(claimService.getAllUnansweredClaims());
    }
    @GetMapping("/unanswered/{id}")
    public ResponseEntity getClaimerUnansweredClaims(@PathVariable Long id){
        return ResponseEntity.ok(claimService.getClaimerUnansweredClaim(id));
    }



    @PostMapping("/")
    public ResponseEntity addNewClaim(@RequestBody ClaimEntity claimEntity,
                                       @RequestParam Long idClaimer,
                                       @RequestParam Long idType) {
        claimService.saveOrUpdateClaim(claimEntity, idClaimer, idType);
        return ResponseEntity.ok("Claim was added");
    }


    @PutMapping("/")
    public ResponseEntity updateClaim(@RequestBody ClaimEntity claimEntity,
                                      @RequestParam Long idClaimer,
                                      @RequestParam Long idType) {
        claimService.saveOrUpdateClaim(claimEntity, idClaimer, idType);
        return ResponseEntity.ok("Claim was changed");
    }
    @PutMapping("/isAnswered/{id}")
    public ResponseEntity updateClaimAnswerState(@PathVariable Long id){
        claimService.updateAnsweredState(id);
        return ResponseEntity.ok("Now claim is " +
                (claimService.getClaimByID(id).isAnswered() ? "answered" : "unanswered"));
    }



    @DeleteMapping("/all")
    public ResponseEntity deleteClaims(){
        claimService.deleteAllClaims();
        return ResponseEntity.ok("All claims were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneClaimById(@PathVariable Long id){
        claimService.deleteClaimById(id);
        return ResponseEntity.ok("Claim with id \"" + id + "\" was removed");
    }
    @DeleteMapping("/claimer/{id}")
    public ResponseEntity deleteAllClaimerClaims(@PathVariable Long id){
        claimService.deleteClaimerClaims(id);
        return ResponseEntity.ok("All user's claims were removed");
    }

}
