package com.portal.utils;

import com.portal.model.Account;
import com.portal.model.Event;
import com.portal.model.FileBD;
import com.portal.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BDUtil {
    public static String path = "";

    private static SessionFactory sessionFactory;

    static  {
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(FileBD.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Event.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
