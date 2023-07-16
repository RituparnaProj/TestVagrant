package com.testcompany.songtracker.service.impl;

import com.testcompany.songtracker.exception.SongManagementException;
import com.testcompany.songtracker.exception.code.SongManagementExceptionCode;
import com.testcompany.songtracker.service.SongManagementService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SongManagementServiceImpl implements SongManagementService {

    private final int capacity;
    private final Map<String, String> store; // Song -> User mapping
    private final Deque<String> recentlyPlayed; // Maintain order of recently played songs

    public SongManagementServiceImpl(int capacity) {
        this.capacity = capacity;
        this.store = new LinkedHashMap<>();
        this.recentlyPlayed = new ArrayDeque<>();
    }

    public void playSong(String song, String user) {
        //validate if song and user are not empty
        Optional.ofNullable(song)
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> {
                    return (user == null || user.isEmpty())
                            ? new SongManagementException(
                            SongManagementExceptionCode.USER_NULL_OR_EMPTY.getErrorCode(),
                            SongManagementExceptionCode.USER_NULL_OR_EMPTY.getErrorMessage())
                            : new SongManagementException(
                            SongManagementExceptionCode.SONG_USER_NULL_OR_EMPTY.getErrorCode(),
                            SongManagementExceptionCode.SONG_USER_NULL_OR_EMPTY.getErrorMessage()
                    );
                });


        // Remove the song if it already exists in the store
        Optional.ofNullable(store.get(song))
                .ifPresent(existingUser -> {
                    recentlyPlayed.remove(song);
                    store.remove(song);
                });

        // Add the song-user pair to the store and recently played deque
        store.put(song, user);
        recentlyPlayed.addFirst(song);

        // Remove the least recently played song if the store becomes full
        Optional.of(recentlyPlayed)
                .filter(rp -> rp.size() > capacity)
                .map(Deque::removeLast)
                .ifPresent(store::remove);
    }

    public List<String> getRecentlyPlayed(String user) throws SongManagementException {
        if(StringUtils.isEmpty(user)) throw new SongManagementException(
                SongManagementExceptionCode.USER_NULL_OR_EMPTY.getErrorCode(),
                SongManagementExceptionCode.USER_NULL_OR_EMPTY.getErrorMessage());

        return recentlyPlayed.stream()
                .filter(song -> store.get(song).equals(user))
                .findFirst()
                .map(song -> recentlyPlayed.stream()
                        .filter(s -> store.get(s).equals(user))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new SongManagementException(
                        SongManagementExceptionCode.NO_RECORDS_FOUND.getErrorCode(),
                        SongManagementExceptionCode.NO_RECORDS_FOUND.getErrorMessage()));

    }

}
