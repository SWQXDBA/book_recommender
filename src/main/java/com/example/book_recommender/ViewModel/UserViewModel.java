package com.example.book_recommender.ViewModel;

import com.example.book_recommender.entity.Interest;
import lombok.Data;

import java.util.Set;

@Data
public class UserViewModel{
    public Long id;

    public String username;

    public String password;

    public Set<Interest> interests;

//    public Set<BookViewModel> interestingBooks;


}