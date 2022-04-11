package com.example.book_recommender.entity;

import lombok.Data;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import com.example.book_recommender.ViewModel.BookViewModel;
@Data
@Entity
@NoArgsConstructor
public class Book{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }

    @CreationTimestamp
    Timestamp createTime;

    @UpdateTimestamp
    Timestamp updateTime;

    public BookViewModel getViewModel(){
            BookViewModel v = new BookViewModel();
            v.id = this.id;
            v.name = this.name;
            return v;
    }

}