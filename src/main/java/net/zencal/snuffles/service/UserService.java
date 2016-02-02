package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.UserRepository;
import net.zencal.snuffles.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    protected UserRepository userRepository;

    public User updateLastSeen(String username, String dubUserId) {
        User existingUser = findUserByUsername(username);
        if(existingUser == null) {
            return createUser(new User(username, dubUserId));
        } else {
            existingUser.setTimesSeen(existingUser.getTimesSeen() + 1L);
            existingUser.setLastSeen(LocalDateTime.now());
            return updateUser(existingUser);
        }
    }

    public User createUser(User user) {
        logger.debug("Creating new User username:" + user.getUsername());
        return userRepository.saveAndFlush(user);
    }

    public User updateUser(User user) {
        logger.debug("Updating User id: " + user.getId());
        return userRepository.saveAndFlush(user);
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByDubUserId(String dubUserId) {
        return userRepository.findByDubUserId(dubUserId);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public User addUpdubReceived(Long id) {
        User user = findUserById(id);
        user.setUpdubsReceived(user.getUpdubsReceived() + 1L);
        return updateUser(user);
    }

    public User addDowndubReceived(Long id) {
        User user = findUserById(id);
        user.setDowndubsReceived(user.getDowndubsReceived() + 1L);
        return updateUser(user);
    }

    public User addUpdubGiven(Long id) {
        User user = findUserById(id);
        user.setUpdubsGiven(user.getUpdubsGiven() + 1L);
        return updateUser(user);
    }

    public User addDowndubGiven(Long id) {
        User user = findUserById(id);
        user.setDowndubsGiven(user.getDowndubsGiven() + 1L);
        return updateUser(user);
    }
}
