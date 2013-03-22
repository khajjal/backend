package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.tables.Loggedin;
import com.vacuumhead.wesplit.tables.Login;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionDao implements ISessionDao {

    private EntityManager entityManager;

    public SessionDao() {
        entityManager = SessionManager.getInstance().getEntityManager().createEntityManager();
    }


    public AccountCodes checkExistUser(String username) {

        TypedQuery<Login> query = entityManager.createQuery("select l from Login l where username = :username", Login.class);
        query.setParameter("username", username);
        List<Login> resultSet =  query.getResultList();

        return resultSet.size() > 0 ? AccountCodes.ACCOUNT_ALREADY_EXIST : AccountCodes.ACCOUNT_DOES_NOT_EXIST;
    }

    public AccountCodes createUser(String username, String password) {

        Login newUser = new Login(username, password);
        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();

        return AccountCodes.ACCOUNT_CREATION_SUCCESSFUL;
    }

    public AccountCodes checkAlreadyLogged(String username) {
        TypedQuery<Loggedin> query = entityManager.createQuery("from Loggedin l where username = :username", Loggedin.class);
        query.setParameter("username", username);
        List<Loggedin> resultSet =  query.getResultList();

        return resultSet.size() > 0 ? AccountCodes.USER_ALREADY_LOGGED_IN: AccountCodes.USER_NOT_LOGGED_IN;
    }

    public AccountCodes setUserLoggedIn(String username) {

        Loggedin loginUser = new Loggedin(username, true);
        entityManager.getTransaction().begin();
        entityManager.persist(loginUser);
        entityManager.getTransaction().commit();

        return AccountCodes.USER_LOGGED_IN;
    }

    public AccountCodes logoutUser(String username) {
        Loggedin loggedinUser = entityManager.find(Loggedin.class, username);
        if(loggedinUser == null) {
            return AccountCodes.USER_NOT_LOGGED_IN;
        }
        entityManager.getTransaction().begin();
        entityManager.remove(loggedinUser);
        entityManager.getTransaction().commit();

        return AccountCodes.USER_LOGGED_OUT;
    }

    public AccountCodes checkCredentials(String username, String password) {
        Login loginUser = new Login(username, password);
        Login result = entityManager.find(Login.class, loginUser.getAccount_id());

        return result != null ? AccountCodes.CREDENTIALS_VALID : AccountCodes.CREDENTIALS_INVALID;
    }

}
