package com.vacuumhead.wesplit.dao;


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

public class UserAccountDao implements IUserAccountDao {

    /* @PersistenceUnit
    private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    public UserAccountDao() {
    }

    public UserAccount retrieveUserAccount(EntityManager em, Integer id) {
        UserAccount userAccount = em.find(UserAccount.class, id);
        return userAccount;
    }

    public UserAccount retrieveUserAccount(EntityManager em, String username) {
        TypedQuery<UserAccount> query = em.createQuery("select l from UserAccount l where username = :username", UserAccount.class);
        query.setParameter("username", username);
        List<UserAccount> resultSet = query.getResultList();
        return resultSet.size() > 0 ? resultSet.get(0) : null;
    }

    public void createUserAccount(EntityManager em, UserAccount userAccount) {
        em.persist(userAccount);
    }

    public void updateUserAccount(EntityManager em, UserAccount userAccount) {
        em.merge(userAccount);
    }

    public void deleteUserAccount(EntityManager em, UserAccount userAccount) {
        em.remove(userAccount);
    }
}