package com.portal.repository;

import com.portal.model.FileBD;
import com.portal.repository.interfaces.FileRepository;
import com.portal.utils.BDUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {


    @Override
    public List<File> readAll() {
        return Arrays.asList(new File(BDUtil.path).listFiles());
    }

    @Override
    public File read(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = session.get(FileBD.class, integer);
        File file = new File(BDUtil.path + "/" + fileBD.getName());
        session.getTransaction().commit();
        session.close();
        return file;
    }

    @Override
    public File create(File file) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = new FileBD(file.getName());
        session.save(fileBD);
        session.getTransaction().commit();
        session.close();
        return file;
    }

    @Override
    public File update(File file) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = session.get(FileBD.class, integer);
        session.createQuery("DELETE from files where id = " + integer);
        session.getTransaction().commit();
        session.close();
        String path = BDUtil.path + "/" + fileBD.getName();
        File file = new File(path);
        file.delete();
        return true;
    }

    public static int getMaxId(){
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "SELECT id from FileBD order by id desc";
        Query query = session.createQuery(hql);
        List results = query.getResultList();
        session.close();
        if(results.size() >= 1) return (Integer) results.get(0);
        else return 0;
    }
}
