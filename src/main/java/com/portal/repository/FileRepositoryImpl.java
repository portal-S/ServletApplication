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
    public List<FileBD> readAll() {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from FileBD");
        query.executeUpdate();
        List<FileBD> fileBDList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return fileBDList;
    }

    @Override
    public FileBD read(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = session.get(FileBD.class, integer);
        session.getTransaction().commit();
        session.close();
        return fileBD;
    }

    @Override
    public FileBD create(FileBD file) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(file);
        session.getTransaction().commit();
        session.close();
        return file;
    }

    @Override
    public FileBD update(FileBD file) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = session.get(FileBD.class, file.getId());
        fileBD.setName(file.getName());
        session.getTransaction().commit();
        session.close();
        String path = BDUtil.path + "/" + file.getName();
        File file1 = new File(path);
        file1.delete();
        return file;
    }

    @Override
    public boolean delete(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FileBD fileBD = session.get(FileBD.class, integer);
        session.createQuery("DELETE from FileBD where id = " + integer).executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println(fileBD.getName());
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
