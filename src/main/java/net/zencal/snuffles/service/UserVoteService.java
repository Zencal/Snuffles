package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.UserVoteRepository;
import net.zencal.snuffles.domain.UserVote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserVote updateOrCreateUserVote(Integer userId, String trackId, Integer targetUserId, Boolean vote) {
        UserVote existingUserVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        Integer downDubs = 0;
        Integer upDubs = 0;

        if (existingUserVote == null) {
            if(vote) {
                upDubs = 1;
                downDubs = 0;
            } else {
                upDubs = 0;
                downDubs = 1;
            }

            return createUserVote(new UserVote(userId, trackId, targetUserId, upDubs, downDubs));
        } else {
            if(vote) {
                upDubs = existingUserVote.getUpdubs() + 1;
                downDubs = existingUserVote.getDowndubs();
            } else {
                upDubs = existingUserVote.getUpdubs();
                downDubs = existingUserVote.getDowndubs() + 1;
            }


            existingUserVote.setUpdubs(upDubs);
            existingUserVote.setDowndubs(downDubs);

            return updateUserVote(existingUserVote);
        }
    }

    public UserVote findUserVoteById(Integer id) {
        return userVoteRepository.findOne(id);
    }

    public void deleteUserVote(Integer id) {
        userVoteRepository.delete(id);
    }

    public UserVote findUserVoteByUserIdAndTrackIdAndTargetUserId(Integer userId, String trackId, Integer targetUserId) {
        return userVoteRepository.findByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
    }

    public UserVote addUpdub(Integer userId, String trackId, Integer targetUserId) {
        UserVote userVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        if(userVote == null) {
            userVote = createUserVote(new UserVote(userId, trackId, targetUserId));
        }
        userVote.setUpdubs(userVote.getUpdubs() + 1);
        return updateUserVote(userVote);
    }

    public UserVote addDowndub(Integer userId, String trackId, Integer targetUserId) {
        UserVote userVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);
        if(userVote == null) {
            userVote = createUserVote(new UserVote(userId, trackId, targetUserId));
        }
        userVote.setDowndubs(userVote.getDowndubs() + 1);
        return updateUserVote(userVote);
    }

    public UserVote addGrab(Integer userId, String trackId, Integer targetUserId) {
        UserVote existingUserVote = findUserVoteByUserIdAndTrackIdAndTargetUserId(userId, trackId, targetUserId);

        if (existingUserVote == null) {
            return createUserVote(new UserVote(userId, trackId, targetUserId, 0, 0, 1));
        } else {
            existingUserVote.setGrabs(existingUserVote.getGrabs() + 1);
            return updateUserVote(existingUserVote);
        }
    }
}
