package com.testcompany.songtracker.service;

import java.util.List;

public interface SongManagementService {

    void playSong(String song, String user);

    List<String> getRecentlyPlayed(String user);
}
