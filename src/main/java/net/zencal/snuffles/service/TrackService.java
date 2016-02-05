package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.TrackRepository;
import net.zencal.snuffles.domain.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrackService {
    private Logger logger = LogManager.getLogger(TrackService.class);
    @Autowired
    protected TrackRepository trackRepository;

    public Track updateTimesPlayed(String id, String type, String fkid, String title) {
        Track existingTrack = findTrackById(id);
        if(existingTrack == null) {
            return createTrack(new Track(id, type, fkid, title));
        } else {
            existingTrack.setTimesPlayed(existingTrack.getTimesPlayed() + 1);
            existingTrack.setLastSeen(LocalDateTime.now());
            return updateTrack(existingTrack);
        }
    }

    public Track createTrack(Track track) {
        logger.debug("Creating new Track trackId: " + track.getId() + " and timesPlayed: " + track.getTimesPlayed());
        return trackRepository.saveAndFlush(track);
    }

    public Track updateTrack(Track track) {
        logger.debug("Updating Track trackId: " + track.getId() + " and timesPlayed: " + track.getTimesPlayed());
        return trackRepository.saveAndFlush(track);
    }

    public Track findTrackById(String id) {
        return trackRepository.findOne(id);
    }

    public Track findTrackByHighestUpdubs() {
        return trackRepository.findTopByOrderByUpdubsDesc();
    }

    public Track findTrackByHighestDowndubs() {
        return trackRepository.findTopByOrderByDowndubsDesc();
    }

    public void deleteTrack(String id) {
        trackRepository.delete(id);
    }

    public Track addUpdub(String id) {
        Track track = findTrackById(id);
        track.setUpdubs(track.getUpdubs() + 1);
        return updateTrack(track);
    }

    public Track addDowndub(String id) {
        Track track = findTrackById(id);
        track.setDowndubs(track.getDowndubs() + 1);
        return updateTrack(track);
    }
}
