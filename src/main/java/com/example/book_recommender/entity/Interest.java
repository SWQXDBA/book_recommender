package com.example.book_recommender.entity;

import lombok.Data;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.Set;

import com.example.book_recommender.ViewModel.InterestViewModel;
@Data
@Entity
public class Interest{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToMany
    Set<User> users;


    @CreationTimestamp
    Timestamp createTime;

    @UpdateTimestamp
    Timestamp updateTime;

    public InterestViewModel getViewModel(){
            InterestViewModel v = new InterestViewModel();
            v.id = this.id;
            v.name = this.name;

            return v;
    }

}