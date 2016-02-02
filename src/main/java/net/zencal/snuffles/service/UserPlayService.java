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

    public UserPlay updateUserPlayTimes(Long userId, String trackId) {
        UserPlay existingUserPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        if(existingUserPlay == null) {
            return createUserPlay(new UserPlay(userId, trackId));
        } else {
            existingUserPlay.setLastSeen(LocalDateTime.now());
            existingUserPlay.setTimesPlayed(existingUserPlay.getTimesPlayed() + 1L);
            return updateUserPlay(existingUserPlay);
        }
    }

    public UserPlay createUserPlay(UserPlay userPlay) {
        logger.debug("Creating new UserPlay trackId: " + userPlay.getTrackId() + " userId: " + userPlay.getUserId());
        return userPlayRepository.saveAndFlush(userPlay);
    }

    public UserPlay updateUserPlay(UserPlay userPlay) {
        logger.debug("Updating UserPlay id:" + userPlay.getId());
        return userPlayRepository.saveAndFlush(userPlay);
    }

    public UserPlay findUserPlayById(Long id) {
        return userPlayRepository.findOne(id);
    }

    public void deleteUserPlay(Long id) {
        userPlayRepository.delete(id);
    }

    public UserPlay findUserPlayByUserIdAndTrackId(Long userId, String trackId) {
        return userPlayRepository.findByUserIdAndTrackId(userId, trackId);
    }

    public UserPlay addUpdub(Long userId, String trackId) {
        UserPlay userPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        userPlay.setUpdubs(userPlay.getUpdubs() + 1L);
        return updateUserPlay(userPlay);
    }

    public UserPlay addDowndub(Long userId, String trackId) {
        UserPlay userPlay = findUserPlayByUserIdAndTrackId(userId, trackId);
        userPlay.setDowndubs(userPlay.getDowndubs() + 1L);
        return updateUserPlay(userPlay);
    }
}
