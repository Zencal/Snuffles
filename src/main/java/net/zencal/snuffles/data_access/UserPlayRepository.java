package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.UserPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userPlayRepository")
public interface UserPlayRepository extends JpaRepository<UserPlay, Long> {
    List<UserPlay> findByUserId(Long userId);
    List<UserPlay> findByTrackId(Long trackId);
    UserPlay findByUserIdAndTrackId(Long userId, String trackId);
}
