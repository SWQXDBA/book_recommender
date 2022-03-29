package com.example.book_recommender.Service;
import com.example.book_recommender.entity.User;
import com.example.book_recommender.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Transactional(rollbackFor = {RuntimeException.class})
    public boolean register(String username,String password){
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        User user = new User(username,passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }
}