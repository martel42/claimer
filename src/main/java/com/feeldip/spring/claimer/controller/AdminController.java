package com.feeldip.spring.claimer.controller;

import com.feeldip.spring.claimer.entity.AdminEntity;
import com.feeldip.spring.claimer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/all")
    public ResponseEntity getAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @GetMapping("/{id}")
    public ResponseEntity getOneAdminById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getAdminByID(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity getOneAdminByUsername(@PathVariable String username){
        return ResponseEntity.ok((adminService.getAdminByUsername(username)));
    }


    @PostMapping("/")
    public ResponseEntity addNewAdmin(@RequestBody AdminEntity adminEntity,
                                      @RequestParam Long idRole){
        adminService.saveOrUpdateAdmin(adminEntity, idRole);
        return ResponseEntity.ok("New administrator was added");
    }


    @PutMapping("/")
    public ResponseEntity updateAdmin(@RequestBody AdminEntity adminEntity,
                                      @RequestParam Long idRole){
        adminService.saveOrUpdateAdmin(adminEntity, idRole);
        return ResponseEntity.ok("Administrator was changed");
    }


    @DeleteMapping("/all")
    public ResponseEntity deleteAdmins(){
        adminService.deleteAllAdmins();
        return ResponseEntity.ok("All administrators were removed");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOneAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
        return ResponseEntity.ok("Administrator with id \"" + id + "\" was removed");
    }
    @DeleteMapping("/username/{username}")
    public ResponseEntity deleteOneAdminByUsername(@PathVariable String username){
        adminService.deleteAdminByUsername(username);
        return ResponseEntity.ok("Administrator with username \"" + username + "\" was removed");
    }

}
