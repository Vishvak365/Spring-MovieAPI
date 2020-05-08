package com.example.movielistapi.movielist;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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

    @GetMapping("/Bcrypt")
    public void bcrypter() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPass1 = bCryptPasswordEncoder.encode("password");
        System.out.println(hashedPass1);

        String secretKey = "mySecretKey";
        UserCollection user = new UserCollection("John", "test", "JohnTest@gmail.com");
        List grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder().setId("softtekJWT").setSubject("username")
                .claim("authorities",
                        grantedAuthorities.stream())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        System.out.println(token);
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