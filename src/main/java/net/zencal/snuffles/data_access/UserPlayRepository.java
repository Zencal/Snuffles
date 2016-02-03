package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.UserPlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userPlayRepository")
public interface UserPlayRepository extends JpaRepository<UserPlay, Integer> {
    List<UserPlay> findByUserId(Integer userId);
    List<UserPlay> findByTrackId(Integer trackId);
    UserPlay findByUserIdAndTrackId(Integer userId, String trackId);
    Integer countByUserId(Integer userId);
}
