package com.vacuumhead.wesplit.responseobject;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 23/03/13
 * Time: 12:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseWrapper {

    private String request;
    private int code;
    private Object response;

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "request='" + request + '\'' +
                ", code=" + code +
                ", response=" + response +
                '}';
    }

    public ResponseWrapper(String request, int code, Object response) {
        this.request = request;
        this.code = code;
        this.response = response;
    }
}
