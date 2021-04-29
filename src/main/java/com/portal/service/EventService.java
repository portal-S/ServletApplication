package com.portal.service;

import com.portal.model.Event;
import com.portal.repository.EventRepositoryImpl;
import com.portal.repository.interfaces.EventRepository;

public class EventService {

    private EventRepository repository = new EventRepositoryImpl();

    public void create(Event event){
        repository.create(event);
    }
}
