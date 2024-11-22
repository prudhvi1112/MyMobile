package com.mymobile.service;

import com.mymobile.entity.UserEntity;
import com.mymobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LogoutService {
        @Autowired
        private LogoutRepository logoutRepository;

        public ResponseEntity<LogoutEntity> save(LogoutEntity user){
            LocalDateTime currentTimeStamp =LocalDateTime.now();
            user.setLastLogoutTime(LocalDateTime.now());

            UserEntity db = userRepository.save(user);
            return new ResponseEntity<>(db, HttpStatus.OK);
        }
public ResponseEntity<LogoutEntity> update(user id, LogoutEntity user) {
    Optional<UserEntity> existingUserOpt = userRepository.findById(id);

    if (!existingUserOpt.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    UserEntity existingUser = existingUserOpt.get();
    UserEntity updatedUser = (UserEntity) userRepository.save(existingUser);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
}
}