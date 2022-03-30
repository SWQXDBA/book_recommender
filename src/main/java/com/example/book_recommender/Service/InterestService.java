package com.example.book_recommender.Service;

import com.example.book_recommender.Repository.InterestRepository;
import com.example.book_recommender.Repository.UserRepository;
import com.example.book_recommender.entity.Interest;
import com.example.book_recommender.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;
    @Autowired
    UserRepository userRepository;

    public boolean addInterestForUser(String username, List<Long> interestId) {
        final User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }

        interestRepository
                .findAllById(interestId)
                .forEach(interest -> user.getInterests().add(interest));

        userRepository.save(user);
        return true;

    }
    public boolean deleteInterestForUser(String username, Long interestId) {
        final User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }

        final Interest interestById = interestRepository
                .findInterestById(interestId);
        if(interestById!=null){
            user.getInterests().remove(interestById);
        }

        userRepository.save(user);
        return true;

    }
    public boolean addInterest(String interestName) {
        if(!interestRepository.existsByName(interestName)){
            interestRepository.save(new Interest(interestName));
            return true;
        }
        return false;

    }
}