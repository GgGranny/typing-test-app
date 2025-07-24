package com.typing_test_app.typing_app.api;

import com.typing_test_app.typing_app.interfaces.UserService;
import com.typing_test_app.typing_app.model.User;
import com.typing_test_app.typing_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class UserRestAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        if(userService.userAuthenticate(user)) {
            return ResponseEntity.ok(userRepo.findByUsername(user.getUsername()));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/signup-user")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
        if(userService.saveUser(user)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(user);
        }
        return ResponseEntity.badRequest().build();


    }

}
