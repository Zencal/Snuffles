package net.zencal.snuffles.service;

import net.zencal.snuffles.data_access.UserRepository;
import net.zencal.snuffles.domain.User;
import org.apache.commons.lang3.StringUtils;
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

    public User updateLastSeen(String username, String id) {
        User existingUser = findUserById(id);
        if(existingUser == null) {
            return createUser(new User(id, username));
        } else {
            if(!StringUtils.equalsIgnoreCase(existingUser.getUsername(), username)) {
                existingUser.setUsername(username);
            }
            existingUser.setTimesSeen(existingUser.getTimesSeen() + 1);
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

    public User findUserById(String id) {
        return userRepository.findOne(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public User addUpdubReceived(String id) {
        User user = findUserById(id);
        user.setUpdubsReceived(user.getUpdubsReceived() + 1);
        return updateUser(user);
    }

    public User addDowndubReceived(String id) {
        User user = findUserById(id);
        user.setDowndubsReceived(user.getDowndubsReceived() + 1);
        return updateUser(user);
    }

    public User addUpdubGiven(String id) {
        logger.debug("Adding updubGiven for userId: " + id);
        User user = findUserById(id);
        user.setUpdubsGiven(user.getUpdubsGiven() + 1);
        return updateUser(user);
    }

    public User addDowndubGiven(String id) {
        logger.debug("Adding downdubGiven for userId: " + id);
        User user = findUserById(id);
        user.setDowndubsGiven(user.getDowndubsGiven() + 1);
        return updateUser(user);
    }

    public User addGrabGiven(String id) {
        logger.debug("Adding grab for userId: " + id);
        User user = findUserById(id);
        user.setGrabs(user.getGrabs() + 1);
        return updateUser(user);
    }

    public User findUserByHighestUpdubsReceived() {
        return userRepository.findTopByOrderByUpdubsReceivedDesc();
    }

    public User findUserByHighestDowndubsReceived() {
        return userRepository.findTopByOrderByDowndubsReceivedDesc();
    }
}
