package com.example.movielistapi.movielist;

import java.util.ArrayList;
import java.util.List;
import com.example.movielistapi.movielist.Repository.UserRepository;
import com.example.movielistapi.movielist.Collections.UserCollection;

import org.springframework.aop.target.EmptyTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class apitest {
    @Autowired
    UserRepository repository;

    // !DANGER
    @GetMapping("/DeleteAllUsers")
    public void DeleteUsers() {
        repository.deleteAll();
    }

    @GetMapping("/InsertUser")
    public String InsertUser() {
        UserCollection user = new UserCollection("John", "test", "JohnTest@gmail.com");
        repository.save(user);
        return "Saved";
    }

    @RequestMapping(value = "/InsertCustomUser", method = RequestMethod.POST)
    public String InsertCustomuser(@ModelAttribute("userCollection") UserCollection userCollection) {
        if (userCollection.getFirstName() != null && userCollection.getLastName() != null
                && userCollection.getEmailAddress() != null) {
            UserCollection saved = repository.save(userCollection);
            return "Success";
        }
        return "Couldn't Add";
    }
    
    @GetMapping("")
    public List<UserCollection> GetUsers() {
        List<UserCollection> users = new ArrayList<UserCollection>();
        // users = repository.findAll();
        repository.findAll().forEach(u -> users.add(u));
        return users;
    }
}