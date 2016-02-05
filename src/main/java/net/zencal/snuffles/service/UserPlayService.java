package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.UserPlayRepository;
import net.zencal.snuffles.domain.UserPlay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserPlayService {
    private Logger logger = LogManager.getLogger(UserPlayService.class);

    @Autowired
    protected UserPlayRepository userPlayRepository;

    public UserPlay updateUserPlayTimes(String userId, String trackId) {
        UserPlay existingUserPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        if(existingUserPlay == null) {
            return createUserPlay(new UserPlay(userId, trackId));
        } else {
            existingUserPlay.setLastSeen(LocalDateTime.now());
            existingUserPlay.setTimesPlayed(existingUserPlay.getTimesPlayed() + 1);
            return updateUserPlay(existingUserPlay);
        }
    }

    public UserPlay createUserPlay(UserPlay userPlay) {
        logger.debug("Creating new UserPlay trackId: " + userPlay.getTrackId() + " userId: " + userPlay.getUserId() + "and timesPlayed: " + userPlay.getTimesPlayed());
        return userPlayRepository.saveAndFlush(userPlay);
    }

    public UserPlay updateUserPlay(UserPlay userPlay) {
        logger.debug("Updating UserPlay id:" + userPlay.getId() + " for trackId: " + userPlay.getTrackId() + " to timesPlayed: " + userPlay.getTimesPlayed());
        return userPlayRepository.saveAndFlush(userPlay);
    }

    public UserPlay findUserPlayById(Integer id) {
        return userPlayRepository.findOne(id);
    }

    public void deleteUserPlay(Integer id) {
        userPlayRepository.delete(id);
    }

    public UserPlay findUserPlayByUserIdAndTrackId(String userId, String trackId) {
        return userPlayRepository.findByUserIdAndTrackId(userId, trackId);
    }

    public UserPlay addUpdub(String userId, String trackId) {
        UserPlay userPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        userPlay.setUpdubs(userPlay.getUpdubs() + 1);
        return updateUserPlay(userPlay);
    }

    public UserPlay addDowndub(String userId, String trackId) {
        UserPlay userPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        userPlay.setDowndubs(userPlay.getDowndubs() + 1);
        return updateUserPlay(userPlay);
    }

    public Integer countTracksPlayedByUserId(String userId) {
        return userPlayRepository.countByUserId(userId);
    }
}
