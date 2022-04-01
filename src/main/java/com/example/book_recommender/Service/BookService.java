package com.example.book_recommender.Service;

import com.example.book_recommender.Repository.BookRepository;
import com.example.book_recommender.Repository.UserRepository;
import com.example.book_recommender.ViewModel.BookViewModel;
import com.example.book_recommender.entity.Book;
import com.example.book_recommender.entity.Interest;
import com.example.book_recommender.entity.User;
import com.example.book_recommender.util.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public boolean addInterestBook(String username, List<Long> bookIds) {
        final User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }

        final List<Book> books = bookIds.stream()
                .map(bookId -> bookRepository.findBookById(bookId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        user.getInterestingBooks().addAll(books);
        userRepository.save(user);
        return true;

    }

    public boolean addBook(String bookName) {
        if (!bookRepository.existsByName(bookName)) {
            bookRepository.save(new Book(bookName));
            return true;
        }
        return false;
    }

    public List<BookViewModel> getRecommendBook(String username) {
        final User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return new ArrayList<>();
        }
        //获取感兴趣的标签
        final Set<Interest> interests = user.getInterests();
        Set<User> matchUsers = new HashSet<>();
        //找出有这些标签的其他用户 至少要有一个相同的兴趣才有比较相似度的价值
        for (Interest interest : interests) {
            matchUsers.addAll(interest.getUsers());
        }
        //移除自己
        matchUsers.remove(user);
        //通过用户的兴趣标签构造入参
        Map<String, Double> userInterests = new HashMap<>();
        for (Interest interest : user.getInterests()) {
            userInterests.put(interest.getName(), 1d);
        }

        Map<User, Double> similarities = new HashMap<>();
        for (User matchUser : matchUsers) {
            Map<String, Double> otherUserInterests = new HashMap<>();
            for (Interest interest : matchUser.getInterests()) {
                otherUserInterests.put(interest.getName(), 1d);
            }
            final double similarity = Core.computeSimilarity(userInterests, otherUserInterests);
            similarities.put(matchUser, similarity);
        }
        final List<BookViewModel> books = new ArrayList<>();
        //找出相似度最大的用户
        similarities.entrySet().stream().max((o1, o2) -> (int) ((o1.getValue() - o2.getValue()) * 10))
                .ifPresent(entry -> books.addAll(entry.getKey().getInterestingBooks()
                        .stream().map(Book::getViewModel)
                        .collect(Collectors.toList())));

        return books;


    }

}