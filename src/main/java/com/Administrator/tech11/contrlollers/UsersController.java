package com.Administrator.tech11.contrlollers;

import com.Administrator.tech11.models.Users;
import com.Administrator.tech11.models.dto.UsersDto;
import com.Administrator.tech11.responses.ApiResponse;
import com.Administrator.tech11.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * api get endpoint to list Users in batches .
     * @param pageNo to know the number of page after grouping
     * @param pageSize to know the size of page you want to group into
     * @return a list of Users with pageNo and pageSize given
     */
    @GetMapping("")
    public ResponseEntity<?> getAllUsers( @RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "5") Integer pageSize) {

        try {
            List<Users> foundCountries = userService.getAllUsers(pageNo, pageSize);
            return new ResponseEntity<>(foundCountries, HttpStatus.FOUND);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

    }


    /**
     * api post endpoint to add a user to the database
     * @param usersDto for collecting details t add
     * @return newly added user
     */
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody UsersDto usersDto) {
        try {

            Users userRequest = modelMapper.map(usersDto, Users.class);
            Users users = userService.addUser(userRequest);
            return new ResponseEntity<>(users, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }


    /**
     * Api put endpoint updates an existing user and returns
     * then newly updated truck.
     * @param userId  truck id to identify what to update
     * @param usersDto fields to be updated
     * @return updated user
     */
    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UsersDto usersDto) {
        try {
            Users updateUser = userService.modifyUser(userId, usersDto);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * api get endpoint to get details of a user with id given.
     * @param userId for searching
     * @return found user
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> searchUser(@PathVariable Long userId) {

        try {
            Users foundUser = userService.findById(userId);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
