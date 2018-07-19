package com.example.domain.auction.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;

    //Todo: Quick and Sloppy.
    @PostMapping("/users")
    public User save(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }
    // http -a user1:password GET localhost:8080/users

//    @GetMapping("/users/{id}")
//    public User getUserById(@PathVariable("id") Long id){
//        Optional<User> user = userRepository.findById(id);
//
//        if(user.isPresent()){
//            return user.get();
//        }
//        return null;
//    }
    // http -a user1:password GET localhost:8080/users

    @GetMapping("/users/{userName}")
    public Optional<User> getUserByUserName(@PathVariable("userName") String userName){
        Optional<User> user = userRepository.findByUsername(userName);

        if(user.isPresent()){
            return user;
        }
        return Optional.empty();
    }
}

//http -v POST localhost:8080/users username="test" password="test"
//http -v GET localhost:8080/users
//http -v -a user:test GET localhost:8080/users


//        User user = new User();
//        user.setPassword("test");
//        user.setUsername("test");
//        userRepository.save(user);
//        log.info("Created user with id:{}, name:{}, and pass:{}", user.getId(), user.getUsername(), user.getPassword());
