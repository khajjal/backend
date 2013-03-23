package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;
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

    private EntityManager entityManager;

    public UserAccount retriveUserAccountExist(int id) {
        UserAccount userAccount = null ;
        entityManager.getTransaction().begin();
        userAccount = entityManager.find(UserAccount.class , id);
        entityManager.getTransaction().commit();
        return userAccount ;
    }

    public UserAccount retriveUserAccountExist(String userName) {
        TypedQuery<UserAccount> query = entityManager.createQuery("select l from UserAccount l where username = :userName", UserAccount.class);
        query.setParameter("username", userName);
        List<UserAccount> resultSet =  query.getResultList();
        return resultSet.size() > 0 ? resultSet.get(0) : null ;
    }

    public void createUserAccount(UserAccount userAccount) {
        entityManager.getTransaction().begin();
        entityManager.persist(userAccount);
        entityManager.getTransaction().commit();
    }

    public void updateUserAccount(UserAccount userAccount) {
        entityManager.getTransaction().begin();
        entityManager.merge(userAccount);
        entityManager.getTransaction().commit();
    }

    public void deleteUserAccount(UserAccount userAccount) {
        entityManager.getTransaction().begin();
        entityManager.remove(userAccount);
        entityManager.getTransaction().commit();
    }
}
