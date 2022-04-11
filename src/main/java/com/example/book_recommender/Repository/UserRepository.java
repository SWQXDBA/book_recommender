package com.example.book_recommender.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.book_recommender.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    
    User findUserById(Long id);

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);
    User findUserByPassword(String password);
    void deleteAllByUsernameLike(String like);



}