package com.Administrator.tech11.services;

import com.Administrator.tech11.models.Users;
import com.Administrator.tech11.models.dto.UsersDto;
import com.Administrator.tech11.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * method to list Users in batches .
     * @param pageNo to know the number of page after grouping
     * @param pageSize to know the size of page you want to group into
     * @return a list of Users with pageNo and pageSize given
     */
    @Override
    public List<Users> getAllUsers(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        log.info("getting list of users from database");
        Page<Users> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            log.info("users found");
            return pagedResult.getContent();
        } else {
            throw new IllegalArgumentException("page not found");
        }
    }

    /**
     * method to add a user to the database
     * @param users for collecting details t add
     * @return newly added user
     */
    @Override
    public Users addUser(Users users) {
       Optional<Users> user = userRepository.findByEmail(users.getEmail());

       if (user.isPresent()) {
           throw new IllegalArgumentException("email already exist");
       }
        return userRepository.save(users);
    }

    /**
     * method to update details of a user.
     * @param userId needs an id to identify what to update.
     * @param usersDto are fields to be updated.
     * @return updated user
     */
    @Override
    public Users modifyUser(Long userId, UsersDto usersDto) {
        Users user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("user with id "+userId+" not found");
        }
        modelMapper.map(usersDto, user);
        return userRepository.save(user);
        }

    /**
     * method to retrieve data of a user by id.
     * @param userId for searching
     * @return found user
     */
    @Override
    public Users findById(Long userId) {
        Optional<Users> foundUser = userRepository.findById(userId);

        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new IllegalArgumentException("User with id --> " + userId + " does not exist");
        }
    }

    }

