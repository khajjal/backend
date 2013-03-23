package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.tables.Loggedin;
import com.vacuumhead.wesplit.tables.UserAccount;
import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionDao implements ISessionDao {

    private EntityManager entityManager;

    public SessionDao() {
        entityManager = SessionManager.getInstance().getEntityManager().createEntityManager();
    }


    public AccountCodes checkExistUser(String username) {

        TypedQuery<UserAccount> query = entityManager.createQuery("select l from UserAccount l where username = :username", UserAccount.class);
        query.setParameter("username", username);
        List<UserAccount> resultSet =  query.getResultList();

        return resultSet.size() > 0 ? AccountCodes.ACCOUNT_ALREADY_EXIST : AccountCodes.ACCOUNT_DOES_NOT_EXIST;
    }

    public AccountCodes createUser(String username, String password) {

        UserAccount newUser = new UserAccount(username, password);
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
        UserAccount loginUser = new UserAccount(username, password);
        UserAccount result = entityManager.find(UserAccount.class, loginUser.getAccountId());

        return result != null ? AccountCodes.CREDENTIALS_VALID : AccountCodes.CREDENTIALS_INVALID;
    }

}
