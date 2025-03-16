package com.skm.redis.controller;

import com.skm.redis.dao.UserDao;
import com.skm.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userDao.saveUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userDao.getUser(userId);
    }

    @GetMapping
    public List<User> getAll() {
        Map<Object, Object> allUser = userDao.findAll();
        return allUser.values().stream()
                .map(value -> (User) value)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userDao.deleteUser(userId);
    }
}
