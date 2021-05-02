package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.TypeEntity;
import com.feeldip.spring.claimer.service.TypeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;


    @GetMapping("/all")
    public ResponseEntity getTypes(){
        return ResponseEntity.ok(typeService.getAllTypes());
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneTypeById(@PathVariable long id) {
        return ResponseEntity.ok(typeService.getTypeByID(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity getOneTypeByName(@PathVariable String name){
        return ResponseEntity.ok(typeService.getTypeByName(name));
    }
    @GetMapping("/description/{id}")
    public ResponseEntity getDescriptionById(@PathVariable Long id){
        return ResponseEntity.ok(typeService.getTypeByID(id).getDescriptionType());
    }
    @GetMapping("/description/name/{name}")
    public ResponseEntity getDescriptionByName(@PathVariable String name){
        return ResponseEntity.ok(typeService.getTypeByName(name).getDescriptionType());
    }


    @PostMapping("/")
    public ResponseEntity addNewType(@RequestBody TypeEntity typeEntity){
        typeService.saveOrUpdateType(typeEntity);
        return ResponseEntity.ok("New type was added");
    }


    @PutMapping("/")
    public ResponseEntity updateType(@RequestBody TypeEntity typeEntity){
        typeService.saveOrUpdateType(typeEntity);
        return ResponseEntity.ok("Type was changed");
    }


    @DeleteMapping("/all")
    public ResponseEntity deleteTypes(){
        typeService.deleteAllTypes();
        return ResponseEntity.ok("All types were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneTypeById(@PathVariable Long id){
        typeService.deleteTypeById(id);
        return ResponseEntity.ok("Type with id \"" + id + "\" was removed");
    }
    @DeleteMapping("/name/{name}")
    public ResponseEntity deleteOneTypeBy(@PathVariable String name){
        typeService.deleteTypeByName(name);
        return ResponseEntity.ok("Type with name \"" + name + "\" was removed");
    }
}
