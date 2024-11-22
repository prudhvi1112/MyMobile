package com.mymobile.controller;
import com.mymobile.entity.UserEntity;
import com.mymobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogoutController {
    //@RequestMapping("/")
    @Autowired
    private LogoutService logoutService;

    @PostMapping("/save")
    public ResponseEntity<LogoutEntity> save(@RequestBody UserEntity user) {
        return new ResponseEntity<>(logoutService.save(user).getBody(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LogoutEntity> update(@PathVariable user id, @RequestBody UserEntity user) {
        LogoutEntity updatedUser = logoutService.update(id, user).getBody();
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
