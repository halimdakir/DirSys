package com.dirsys.worktest.util;

import com.dirsys.worktest.entity.User;
import com.dirsys.worktest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SetUpDataBase {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                var user1 = new User("Halim Dakir", "halim.dakir@gmail.com", passwordEncoder.encode("1234"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
                var user2 = new User("Elodie Frere", "elodie.frere@gmail.com", passwordEncoder.encode("1234"), "USER", "ACCESS_TEST1");
                userRepository.save(user1);
                userRepository.save(user2);
            }
        };
    }
}
