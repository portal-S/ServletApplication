package com.portal.service;

import com.portal.model.Account;
import com.portal.model.Event;
import com.portal.model.User;
import com.portal.repository.interfaces.UserRepository;
import com.portal.repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository repository = new UserRepositoryImpl();


    public void create(Account account){

        repository.create(new User(account));
    }

    public void delete(int id){
        repository.delete(id);
    }

    public User read(Integer id){
        return repository.read(id);
    }


}
