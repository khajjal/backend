package com.vacuumhead.wesplit.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 19/03/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionManager {
    private static SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public EntityManagerFactory getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("wesplit");
        return factory;
    }
}
