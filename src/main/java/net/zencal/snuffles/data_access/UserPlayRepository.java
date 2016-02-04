package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.UserPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userPlayRepository")
public interface UserPlayRepository extends JpaRepository<UserPlay, Integer> {
    List<UserPlay> findByUserId(String userId);
    List<UserPlay> findByTrackId(Integer trackId);
    UserPlay findByUserIdAndTrackId(String userId, String trackId);
    Integer countByUserId(String userId);
}
