package com.niit.chat.dao;

import java.util.List;

import com.niit.chat.model.Users;

public interface UsersDAO 
{
Users authenticate(Users users);
void updateUsers(Users users);
Users registerUsers(Users users);
//public List<Users> usersList();
public List<Users> getAllUsers(Users user);

}
