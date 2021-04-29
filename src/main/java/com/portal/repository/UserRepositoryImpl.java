package com.portal.repository;

import com.portal.model.Account;
import com.portal.model.FileBD;
import com.portal.model.User;
import com.portal.repository.interfaces.UserRepository;
import com.portal.utils.BDUtil;
import org.hibernate.Session;

import java.io.File;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User read(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, integer);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User create(User user) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User update(User user) {  //wait
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user1 = session.get(User.class, user.getId());
        user1.setAccount(user.getAccount());
        user1.setEvents(user.getEvents());
        System.out.println(user1);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean delete(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE from User where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
