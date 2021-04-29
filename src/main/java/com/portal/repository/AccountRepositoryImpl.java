package com.portal.repository;

import com.portal.model.Account;
import com.portal.model.AccountStatus;
import com.portal.repository.interfaces.AccountRepository;
import com.portal.utils.BDUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {


    @Override
    public List<Account> readAll() {
        return null;
    }

    @Override
    public Account read(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Account account = session.get(Account.class, integer);
        session.getTransaction().commit();
        session.close();
        return account;
    }

    @Override
    public Account create(Account account) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(account);
        session.getTransaction().commit();
        session.close();
        return account;
    }

    @Override
    public Account update(Account account) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Account accountDB = session.get(Account.class, account.getId());
        accountDB.setName(account.getName());
        accountDB.setPassword(account.getPassword());
        session.save(account);
        session.getTransaction().commit();
        session.close();
        return account;
    }

    @Override
    public boolean delete(Integer integer) {
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Account WHERE id = " + integer);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    public static AccountStatus auth(Account account){
        Session session = BDUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Account WHERE name = :nameAcc");
        query.setParameter("nameAcc", account.getName());
        List<Account> accounts = query.getResultList();
        session.getTransaction().commit();
        if(accounts.size() == 1){
            if(accounts.get(0).getPassword().equals(account.getPassword())){
                return accounts.get(0).getAccountStatus();
            } else return AccountStatus.ERROR_PASS;
        } else return AccountStatus.NOT_AUTHORIZED;
    }
}
