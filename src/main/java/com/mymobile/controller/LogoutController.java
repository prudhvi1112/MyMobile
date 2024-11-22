package com.mymobile.controller;
package com.mymobile.controller;

import com.mymobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;


    @PostMapping("/{userId}")
    public ResponseEntity<String> logout(@PathVariable String userId) {
        try {
            userService.logoutUser(userId);
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}




