package com.sk89q.rebar.config;

public class LoaderBuilderException extends RuntimeException {

    private static final long serialVersionUID = 8337243690688403608L;

    public LoaderBuilderException() {
    }

    public LoaderBuilderException(String message) {
        super(message);
    }

    public LoaderBuilderException(Throwable cause) {
        super(cause);
    }

    public LoaderBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

}
