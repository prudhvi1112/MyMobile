package com.mymobile.service;
package com.mymobile.service;

import com.mymobile.entity.UserData;
import com.mymobile.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
public class LogoutService {
    @Autowired
    private userDetailsDao userDetailsDao ;
    public void logoutUser(String userId) {
        UserData user = userDetailsDao.findById(userId);
        if (user != null) {
            user.setLastUpdatedTime(LocalDateTime.now());
            userDataRepository.save(user);
        } else {
            throw new RuntimeException("User not found with userId: " + userId);
        }
    }
}
