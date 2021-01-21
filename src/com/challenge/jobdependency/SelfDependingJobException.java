package com.challenge.jobdependency;

public class SelfDependingJobException extends Exception {
    public SelfDependingJobException(String errorMessage) {
        super(errorMessage);
    }
}
