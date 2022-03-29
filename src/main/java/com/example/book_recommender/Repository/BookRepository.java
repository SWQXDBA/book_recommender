package com.example.book_recommender.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.book_recommender.entity.Book;
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    
    Book findBookById(Long id);

    Book findBookByName(String name);

}