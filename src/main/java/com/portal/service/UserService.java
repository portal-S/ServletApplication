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

    public void update(int id, Account account){
        repository.update(new User(id, account));
    }

    public void delete(int id){
        repository.delete(id);
    }

    public void addEvent(int userId, Event event){
        User user = repository.read(userId);
        List<Event> events = user.getEvents();
        if(events == null) events = new ArrayList<>();
        events.add(event);
        repository.update(user);
    }

    public User read(Integer id){
        return repository.read(id);
    }


}
