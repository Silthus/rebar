package com.sk89q.rebar.config;

public class LoaderException extends RuntimeException {

    private static final long serialVersionUID = -6468896196176661038L;

    public LoaderException() {
    }

    public LoaderException(String message) {
        super(message);
    }

    public LoaderException(Throwable cause) {
        super(cause);
    }

    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
