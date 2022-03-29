package com.example.book_recommender.entity;

import lombok.Data;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import com.example.book_recommender.ViewModel.BookViewModel;
@Data
@Entity
public class Book{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;


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