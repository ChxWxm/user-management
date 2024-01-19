package com.usermangement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers(@RequestParam("active") Optional<Boolean> active) {
        return userService.getUsers(active);
    }

    @PostMapping("api/users")
    public User createUsers(@RequestBody UserRequest request) {
        return userService.createUsers(request);
    }

    @PutMapping("api/users/{id}")
    public void updateUsers(@PathVariable("id") int id, @RequestBody UserRequest request) {
        userService.updateUsers(id, request);
    }

    @DeleteMapping("api/users/{id}")
    public void deleteUsers(@PathVariable("id") int id) {
        userService.deleteUsers(id);
    }
}

record UserRequest(String name, int age) {
}

class User {
    private final int id;
    private final int age;
    private final Boolean active;
    private String name;

    public User(int id, String name, int age, Boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getActive() {
        return active;
    }
}
