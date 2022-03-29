package com.example.book_recommender.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.book_recommender.entity.Interest;
@Repository
public interface InterestRepository extends CrudRepository<Interest,Long> {
    
    Interest findInterestById(Long id);

    Interest findInterestByName(String name);



}