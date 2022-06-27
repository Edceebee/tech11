package com.Administrator.tech11.services;

import com.Administrator.tech11.models.Users;
import com.Administrator.tech11.models.dto.UsersDto;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers(Integer pageNo, Integer pageSize);

    Users addUser(Users users);

    Users modifyUser(Long userId, UsersDto usersDto);

    Users findById(Long userId);

}
