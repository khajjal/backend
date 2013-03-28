package com.vacuumhead.wesplit.dao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 19/03/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionManager {


    public Object daoExecution(ProceedingJoinPoint point) throws Throwable {
        Object retVal = null;

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        try {
            System.out.println("Calling function in DAO" + method + "(" + signature + ")");

            long startTime = System.currentTimeMillis();
            retVal = point.proceed();
            long stopTime = System.currentTimeMillis();

            System.out.println("Returning from function in DAO " + method + "(" + signature + ")"
                    + ". Took " + String.valueOf(stopTime - startTime) + " ms.");
        } catch (Throwable throwable) {

            System.out.println("calling in DAO " + point.toString() + " threw exception = "+throwable.toString());
        }

        return retVal;

    }
}
