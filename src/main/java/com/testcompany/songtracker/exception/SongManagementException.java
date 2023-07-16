package com.testcompany.songtracker.exception;



public class SongManagementException extends RuntimeException {
    private final String errorCode;

    public SongManagementException(String errorCode, String message) {
        super(errorCode+" - "+message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
