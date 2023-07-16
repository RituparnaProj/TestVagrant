package com.testcompany.songtracker;

import com.testcompany.songtracker.builder.SongManagementBuilder;
import com.testcompany.songtracker.exception.SongManagementException;
import com.testcompany.songtracker.service.impl.SongManagementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SongManagementTest {
        @Test
        public void testRecentlyPlayedSongs() {
            SongManagementServiceImpl store = SongManagementBuilder.newBuilder(3)
                    .playSong("S1", "User1")
                    .playSong("S2", "User1")
                    .playSong("S3", "User1")
                    .build();

        List<String> recentlyPlayedSongs = store.getRecentlyPlayed("User1");
        Assertions.assertEquals(3, recentlyPlayedSongs.size());
        Assertions.assertEquals("S3", recentlyPlayedSongs.get(0));
        Assertions.assertEquals("S2", recentlyPlayedSongs.get(1));
        Assertions.assertEquals("S1", recentlyPlayedSongs.get(2));
    }

        @Test
        public void testDuplicateSongs() {
            SongManagementServiceImpl store = SongManagementBuilder.newBuilder(4)
                    .playSong("S1", "User1")
                    .playSong("S2", "User1")
                    .playSong("S3", "User1")
                    .playSong("S1", "User1")
                .build();

        List<String> recentlyPlayedSongs = store.getRecentlyPlayed("User1");
        Assertions.assertEquals(3, recentlyPlayedSongs.size());
        Assertions.assertEquals("S1", recentlyPlayedSongs.get(0));
        Assertions.assertEquals("S3", recentlyPlayedSongs.get(1));
        Assertions.assertEquals("S2", recentlyPlayedSongs.get(2));
    }

        @Test
        public void testFullCapacity() {
            SongManagementServiceImpl store = SongManagementBuilder.newBuilder(3)
                    .playSong("S1", "User1")
                    .playSong("S2", "User1")
                    .playSong("S3", "User1")
                    .playSong("S4", "User1")
                .build();

        List<String> recentlyPlayedSongs = store.getRecentlyPlayed("User1");
        Assertions.assertEquals(3, recentlyPlayedSongs.size());
        Assertions.assertEquals("S4", recentlyPlayedSongs.get(0));
        Assertions.assertEquals("S3", recentlyPlayedSongs.get(1));
        Assertions.assertEquals("S2", recentlyPlayedSongs.get(2));
    }

    @Test
    public void testPlaySong() {
        SongManagementServiceImpl store = SongManagementBuilder.newBuilder(3)
                .playSong("S1", "User1")
                .playSong("S2", "User1")
                .playSong("S3", "User1")
                .build();

        // Attempt to play a song with the same user,but song not provided
        Assertions.assertThrows(SongManagementException.class, () -> {
            store.playSong("", "User1");
        });
    }

    @Test
    public void testGetRecentlyPlayed() {
        SongManagementServiceImpl store = SongManagementBuilder.newBuilder(3)
                .playSong("S1", "User1")
                .playSong("S2", "User1")
                .playSong("S3", "User1")
                .playSong("S4", "User2")
                .playSong("S5", "User2")
                .build();

        // Attempt to get recently played songs for a non-existing user
        Assertions.assertThrows(SongManagementException.class, () -> {
            store.getRecentlyPlayed("User3");
        });
    }
}