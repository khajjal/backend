package com.vacuumhead.wesplit.dao;


import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shuklar
 * Date: 3/24/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserAccountDao implements  IUserAccountDao {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UserAccountDao() {
    }

    public UserAccount retrieveUserAccount(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        UserAccount userAccount;
        entityManager.getTransaction().begin();
        userAccount = entityManager.find(UserAccount.class ,id);
        entityManager.getTransaction().commit();
        return userAccount ;
    }

    public UserAccount retrieveUserAccount(String username) {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<UserAccount> query = entityManager.createQuery("select l from UserAccount l where username = :param1", UserAccount.class);
        query.setParameter("param1", (String)username);
        System.out.println("12334" +username +" " +username.getClass());
        UserAccount account =  query.getSingleResult();
        //return resultSet.size() > 0 ? resultSet.get(0) : null ;
        return account;
    }

    public void createUserAccount(UserAccount userAccount) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userAccount);
        entityManager.getTransaction().commit();
    }

    public void updateUserAccount(UserAccount userAccount) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(userAccount);
        entityManager.getTransaction().commit();
    }

    public void deleteUserAccount(UserAccount userAccount) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(userAccount);
        entityManager.getTransaction().commit();
    }
}