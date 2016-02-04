package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "trackRepository")
public interface TrackRepository extends JpaRepository<Track, String> {
    Track findTopByOrderByUpdubsDesc();
    Track findTopByOrderByDowndubsDesc();
}
