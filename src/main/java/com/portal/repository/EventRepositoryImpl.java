package com.portal.repository;

import com.portal.model.Account;
import com.portal.model.Event;
import com.portal.model.User;
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
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event event = session.get(Event.class, integer);
        session.getTransaction().commit();
        session.close();
        return event;
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
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event event1 = session.get(Event.class, event.getId());
        event1.setAction(event.getAction());
        event1.setUser(event.getUser());
        session.getTransaction().commit();
        session.close();
        return event;
    }

    @Override
    public boolean delete(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE from Event where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
