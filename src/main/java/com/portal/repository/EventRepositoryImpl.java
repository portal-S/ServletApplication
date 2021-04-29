package com.portal.repository;

import com.portal.model.Account;
import com.portal.model.Event;
import com.portal.repository.interfaces.EventRepository;
import com.portal.utils.BDUtil;
import org.hibernate.Session;

import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public List<Event> readAll() {
        return null;
    }

    @Override
    public Event read(Integer integer) {
        return null;
    }

    @Override
    public Event create(Event event) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        session.close();
        return event;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
