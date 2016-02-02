package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userVoteRepository")
public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
    List<UserVote> findByUserId(Long userId);
    List<UserVote> findByTrackId(Long trackId);
    List<UserVote> findByTargetUserId(Long targetUserId);
    UserVote findByUserIdAndTrackIdAndTargetUserId(Long userId, String trackId, Long targetUserId);
}
