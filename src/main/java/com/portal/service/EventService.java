package com.portal.service;

import com.portal.model.Event;
import com.portal.model.User;
import com.portal.repository.EventRepositoryImpl;
import com.portal.repository.interfaces.EventRepository;

public class EventService {

    private EventRepository repository = new EventRepositoryImpl();
    private UserService userService = new UserService();

    public void create(Event event){
        repository.create(event);
    }

    public void create(int user_id, String action){
        repository.create(new Event(userService.read(user_id), action));
    }

    public void delete(int id){
        repository.delete(id);
    }

    public void update(int user_id, String action){
        repository.update(new Event(userService.read(user_id), action));
    }

    public Event read(Integer id){
        return repository.read(id);
    }
}
