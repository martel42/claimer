package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.AnswerEntity;
import com.feeldip.spring.claimer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;


    @GetMapping("/all")
    public ResponseEntity getAnswers(){
        return ResponseEntity.ok(answerService.getAllAnswers());
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneAnswerById(@PathVariable Long id){
        return ResponseEntity.ok(answerService.getAnswerByID(id));
    }
    @GetMapping("/text/{id}")
    public ResponseEntity getTextAnswer(@PathVariable Long id){
        return ResponseEntity.ok(answerService.getTextAnswerById(id));
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity getOneAnswerByAdminId(@PathVariable Long id){
        return ResponseEntity.ok(answerService.getAdminAnswers(id));
    }


    @PostMapping("/")
    public ResponseEntity addNewAnswer(@RequestBody AnswerEntity answerEntity,
                                       @RequestParam Long idAdmin,
                                       @RequestParam Long idStatus,
                                       @RequestParam Long idClaim) {
        answerService.saveOrUpdateAnswer(answerEntity, idAdmin, idStatus, idClaim);
        return ResponseEntity.ok("Answer has been added");
    }


    @PutMapping("/")
    public ResponseEntity updateAnswer(@RequestBody AnswerEntity answerEntity,
                                       @RequestParam Long idAdmin,
                                       @RequestParam Long idStatus,
                                       @RequestParam Long idClaim) {
        answerService.saveOrUpdateAnswer(answerEntity, idAdmin, idStatus, idClaim);
        return ResponseEntity.ok("Answer was changed");
    }


    @DeleteMapping("/all")
    public ResponseEntity deleteAnswers(){
        answerService.deleteAllAnswers();
        return ResponseEntity.ok("All answers were removed");
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity deleteAllAdminAnswers(@PathVariable Long id){
        answerService.deleteAdminAnswers(id);
        return ResponseEntity.ok("All administrator's answers were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneAnswerById(@PathVariable Long id){
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok("Answer with id \"" + id + "\" was removed");
    }

}
