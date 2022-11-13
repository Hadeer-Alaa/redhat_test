package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRestController<T>{

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public T createUser(@Valid @RequestBody User user) {
        if(userService.getUser(user.getUserName())==null){
            userService.createUser(user);
            return (T) user;
        }
        return (T) "This user name already exist";

    }

    @PutMapping("/user/{username}")
    public Object updateUser(@PathVariable String username,@Valid @RequestBody User user){
        if(userService.getUser(username)!=null){
            return userService.updateUser(username,user);
        }
        else {
            return "This username doesn't exist";
        }
    }

    @DeleteMapping("/user/{username}")
    public T deleteUser(@PathVariable String username,@Valid @RequestBody User user){
        if(userService.getUser(username)!=null){
            userService.deleteUser(username,user);
            return (T) user;
        }
        else {
            return (T) "This user name doesn't exist";
        }
    }

    @GetMapping("/user/{username}/{password}")
    public T login(@PathVariable String username,@PathVariable String password,@Valid @RequestBody User user){
        if(userService.login(username,password,user))
            return (T) user;
        else {
            return (T) "Invalid login";
        }
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username){
       User user = userService.getUser(username);
       return user;
    }


}
