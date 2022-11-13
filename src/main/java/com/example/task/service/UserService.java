package com.example.task.service;

import com.example.task.entity.User;
import com.example.task.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void createUser(User user) {
        userDAO.createUser(user);
    }
    public Object updateUser(String username, User user){
        return userDAO.updateUser(username,user);
    }
    public void deleteUser(String userName,User user){
        userDAO.deleteUser(userName,user);
    }
    public User getUser(String userName){
        return userDAO.getUser(userName);
    }
    public boolean login(String userName, String password,User user){
        return  userDAO.login(userName,password, user);

    }

}
