package com.Administrator.tech11.configurations;

import com.Administrator.tech11.models.Users;
import com.Administrator.tech11.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * This class seeds the database using command line runner.
 */
@Configuration
public class UsersConfig {

        @Bean
        CommandLineRunner commandLineRunner(UserRepository userRepository) {
            return (args) -> {
                Users user = new Users(null, "John", "Doe", "j.doe@email.com", LocalDate.of(1987, 11, 9), "johnpass");
                Users user2 = new Users(null, "Peter", "Bush", "p.bush@email.com", LocalDate.of(1983, 7, 22), "peterpass");
                Users user3 = new Users(null, "Esther", "Dan", "e.dan@email.com", LocalDate.of(1991, 12, 23), "estherpass");
                Users user4 = new Users(null, "Matt", "Donovan", "m.donovan@email.com", LocalDate.of(1988, 4, 19), "mattpass");
                Users user5 = new Users(null, "Damon", "Salvatore", "d.salvator@email.com", LocalDate.of(1923, 2, 9), "damonpass");
                Users user6 = new Users(null, "Kyle", "Peters", "k.peters@email.com", LocalDate.of(1994, 6, 28), "kylepass");
                Users user7 = new Users(null, "Jessica", "Jay", "j.jay@email.com", LocalDate.of(1993, 1, 30), "jessicapass");
                Users user8 = new Users(null, "Anthony", "George", "a.george@email.com", LocalDate.of(1996, 1, 21), "anthonypass");
                Users user9 = new Users(null, "David", "Don", "d.don@email.com", LocalDate.of(2000, 11, 3), "davidpass");
                Users user10 = new Users(null, "Rose", "Ann", "r.ann@email.com", LocalDate.of(1970, 6, 26), "rosepass");
                Users user11 = new Users(null, "Gabriella", "Anthony", "g.anthony@email.com", LocalDate.of(1998, 10, 31), "gabriellapass");

                userRepository.save(user);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);
                userRepository.save(user5);
                userRepository.save(user6);
                userRepository.save(user7);
                userRepository.save(user8);
                userRepository.save(user9);
                userRepository.save(user10);
                userRepository.save(user11);

            };
        }
}
