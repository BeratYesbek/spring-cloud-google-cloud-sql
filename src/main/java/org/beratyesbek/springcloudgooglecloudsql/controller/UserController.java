package org.beratyesbek.springcloudgooglecloudsql.controller;

import lombok.RequiredArgsConstructor;
import org.beratyesbek.springcloudgooglecloudsql.entity.User;
import org.beratyesbek.springcloudgooglecloudsql.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        long start = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " - START JDBC Query");

        List<User> users = userRepository.findAll();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " - END JDBC Query - Time taken: " + (end - start) + "ms");

        return ResponseEntity.ok(users);
    }


    @GetMapping("/async")
    public CompletableFuture<ResponseEntity<List<User>>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - START JDBC Query");
            var user = userRepository.findAll();
            System.out.println(Thread.currentThread().getName() + " - END JDBC Query");
            return ResponseEntity.ok(user);
        });
    }

}
