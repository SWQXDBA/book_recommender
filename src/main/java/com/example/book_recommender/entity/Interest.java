package com.example.book_recommender.entity;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.book_recommender.ViewModel.InterestViewModel;

@Entity
@Data
@NoArgsConstructor
public class Interest{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToMany(mappedBy = "interests")
    @EqualsAndHashCode.Exclude
    Set<User> users = new HashSet<>();



    @CreationTimestamp
    Timestamp createTime;

    @UpdateTimestamp
    Timestamp updateTime;

    @Override
    public String toString() {
        return "Interest{" +
                "name='" + name + '\'' +
                '}';
    }

    public Interest(String name) {
        this.name = name;
    }

    public InterestViewModel getViewModel(){
            InterestViewModel v = new InterestViewModel();
            v.id = this.id;
            v.name = this.name;

            return v;
    }

}