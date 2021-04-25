package com.portal.utils;

import com.portal.model.FileBD;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BDUtil {
    public static String path = "";

    private static SessionFactory sessionFactory;

    static  {
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(FileBD.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
