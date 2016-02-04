package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userVoteRepository")
public interface UserVoteRepository extends JpaRepository<UserVote, Integer> {
    List<UserVote> findByUserId(String userId);
    List<UserVote> findByTrackId(Integer trackId);
    List<UserVote> findByTargetUserId(Long targetUserId);
    UserVote findByUserIdAndTrackIdAndTargetUserId(String userId, String trackId, String targetUserId);
}
