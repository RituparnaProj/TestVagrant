package com.testcompany.songtracker.exception.code;

public enum SongManagementExceptionCode {
    SONG_USER_NULL_OR_EMPTY("SME-001", "Song and user cannot be null or empty"),
    USER_NULL_OR_EMPTY("SME-002", "User cannot be null or empty"),
    NO_RECORDS_FOUND("SME-002","User not found");

    private final String errorCode;
    private final String errorMessage;

    SongManagementExceptionCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

