package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.UserVoteRepository;
import net.zencal.snuffles.domain.UserVote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserVoteService {
    private Logger logger = LogManager.getLogger(UserVoteService.class);
    
    @Autowired
    protected UserVoteRepository userVoteRepository;

    public UserVote createUserVote(UserVote userVote) {
        logger.debug("Creating UserVote for userId: " + userVote.getUserId() + " trackId: " + userVote.getTrackId() + " targetUserId: " + userVote.getTargetUserId());
        return userVoteRepository.saveAndFlush(userVote);
    }

    public UserVote updateUserVote(UserVote userVote) {
        logger.debug("Updating UserVote id: " + userVote.getId());
        return userVoteRepository.saveAndFlush(userVote);
    }

    public UserVote updateOrCreateUserVote(Long userId, String trackId, Long targetUserId, Boolean vote) {
        UserVote existingUserVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        Long downDubs = 0L;
        Long upDubs = 0L;

        if (existingUserVote == null) {
            if(vote) {
                upDubs = 1L;
                downDubs = 0L;
            } else {
                upDubs = 0L;
                downDubs = 1L;
            }
            return createUserVote(new UserVote(userId, trackId, targetUserId, upDubs, downDubs));
        } else {
            if(vote) {
                upDubs = existingUserVote.getUpdubs() + 1L;
                downDubs = existingUserVote.getDowndubs();
            } else {
                upDubs = existingUserVote.getUpdubs();
                downDubs = existingUserVote.getDowndubs() + 1L;
            }

            existingUserVote.setUpdubs(upDubs);
            existingUserVote.setDowndubs(downDubs);

            return updateUserVote(existingUserVote);
        }
    }

    public UserVote findUserVoteById(Long id) {
        return userVoteRepository.findOne(id);
    }

    public void deleteUserVote(Long id) {
        userVoteRepository.delete(id);
    }

    public UserVote findUserVoteByUserIdAndTrackIdAndTargetUserId(Long userId, String trackId, Long targetUserId) {
        return userVoteRepository.findByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
    }

    public UserVote addUpdub(Long userId, String trackId, Long targetUserId) {
        UserVote userVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        if(userVote == null) {
            userVote = createUserVote(new UserVote(userId, trackId, targetUserId));
        }
        userVote.setUpdubs(userVote.getUpdubs() + 1L);
        return updateUserVote(userVote);
    }

    public UserVote addDowndub(Long userId, String trackId, Long targetUserId) {
        UserVote userVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        if(userVote == null) {
            userVote = createUserVote(new UserVote(userId, trackId, targetUserId));
        }
        userVote.setDowndubs(userVote.getDowndubs() + 1L);
        return updateUserVote(userVote);
    }
}
