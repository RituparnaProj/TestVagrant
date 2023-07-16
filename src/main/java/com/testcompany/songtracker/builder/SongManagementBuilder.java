package com.testcompany.songtracker.builder;

import com.testcompany.songtracker.service.impl.SongManagementServiceImpl;

public class SongManagementBuilder {
    private final SongManagementServiceImpl store;

    private SongManagementBuilder(int capacity) {
        this.store = new SongManagementServiceImpl(capacity);
    }

    public static SongManagementBuilder newBuilder(int capacity) {
        return new SongManagementBuilder(capacity);
    }

    public SongManagementBuilder playSong(String song, String user) {
        store.playSong(song, user);
        return this;
    }

    public SongManagementServiceImpl build() {
        return store;
    }
}
