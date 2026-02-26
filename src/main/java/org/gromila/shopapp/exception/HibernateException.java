package org.gromila.shopapp.exception;

public class HibernateException extends RuntimeException {
    public HibernateException(String message) {
        super(message);
    }
}