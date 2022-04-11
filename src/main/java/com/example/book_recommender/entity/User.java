package com.example.book_recommender.entity;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.example.book_recommender.ViewModel.UserViewModel;
@Data
@Entity
@NoArgsConstructor
public class User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ManyToMany
    @EqualsAndHashCode.Exclude
    Set<Interest> interests = new HashSet<>();
    @ManyToMany
    @EqualsAndHashCode.Exclude
    Set<Book> interestingBooks = new HashSet<>();


    @CreationTimestamp
    Timestamp createTime;

    @UpdateTimestamp
    Timestamp updateTime;

    public UserViewModel getViewModel(){
            UserViewModel v = new UserViewModel();
            v.id = this.id;
            v.username = this.username;
            v.password = this.password;

            return v;
    }

}