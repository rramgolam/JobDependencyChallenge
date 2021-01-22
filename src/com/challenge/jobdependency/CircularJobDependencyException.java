package com.challenge.jobdependency;

public class CircularJobDependencyException extends Exception {

    public CircularJobDependencyException(String errorMessage) {
        super(errorMessage);
    }

}
