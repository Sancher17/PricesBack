package com.alexsoft.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class ControllerTests {


//    @GetMapping("/")
//    public User getUser(@RequestParam Long id) throws IOException {
//        User user = new User();
//        Address address = new Address();
//        address.setStreet("Street");
//        user.setAddress(address);
//        repo.save(user);
//
//        List<User> all = repo.findAll();
//        for (User user1 : all) {
//            System.out.println(user1);
//        }
//
//        Optional<User> byId = repo.findById(id);
//        return byId.get();
//    }
}
