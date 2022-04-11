package com.example.book_recommender;

import com.example.book_recommender.Repository.BookRepository;
import com.example.book_recommender.Repository.InterestRepository;
import com.example.book_recommender.Repository.UserRepository;
import com.example.book_recommender.Service.BookService;
import com.example.book_recommender.ViewModel.BookViewModel;
import com.example.book_recommender.entity.Book;
import com.example.book_recommender.entity.Interest;
import com.example.book_recommender.entity.User;
import com.example.book_recommender.util.Core;
import org.apache.tomcat.jni.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class BookRecommenderApplicationTests {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InterestRepository interestRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BookService bookService;

    @Test
    @Transactional
    @Rollback(false)
    void adm() {
        userRepository.deleteAllByUsernameLike("%admin%");
        User user = new User("admin3",passwordEncoder.encode("admin3"));
        user = userRepository.save(user);
        user.getInterests().add(interestRepository.findInterestByName("童书"));
        user.getInterests().add(interestRepository.findInterestByName("小说"));
        user.getInterests().add(interestRepository.findInterestByName("文学"));
        userRepository.save(user);
        final List<BookViewModel> admin = bookService.getRecommendBook("admin3");
        admin.forEach(bookViewModel -> System.out.println(bookViewModel.name));
    }

    @Test
    @Transactional
    @Rollback(false)
    void contextLoads() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\SWQXDBA\\IdeaProjects\\4653ssm校园疫情信息管理系统\\book_recommender\\src\\main\\resources\\static\\书籍.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Interest currentInterest = null;
            Map<Interest, List<Book>> booksMap = new LinkedHashMap<>();
            while (true){
                final String str = bufferedReader.readLine();
                if(str==null)break;
                if(str.contains(":")||str.contains("：")){
                    String  bookType = str.replace(":", "").replace("：","");
                     currentInterest = interestRepository.save(new Interest(bookType));
                    booksMap.put(currentInterest,new ArrayList<>());
                }else{
                    if(str.equals(""))continue;
                    if(currentInterest==null){
                        System.out.println("null!!!!!!!!!!!!!+"+str);
                        return;
                    }
                    Book book = new Book(str);
                    book =  bookRepository.save(book);
                    booksMap.get(currentInterest).add(book);
                }

            }
            List<User> users = new ArrayList<>();
            //开始生成虚拟用户
            Random random = new Random();
            final ArrayList<Map.Entry<Interest, List<Book>>> entries = new ArrayList<>(booksMap.entrySet());
            for (int i = 0; i < 1000; i++) {
                final UUID uuid = UUID.randomUUID();
                User user = new User(uuid.toString(),"testUserPassword");
               final User  userEntity = userRepository.save(user);
               //打乱一下顺序 创造随机性
                Collections.shuffle(entries);
                entries.forEach((entry)->{
                   var interest = entry.getKey();
                    var books = entry.getValue();
                    if(random.nextBoolean()&&random.nextBoolean()){
                        userEntity.getInterests().add(interest);
                        books.forEach((book -> {
                            if(random.nextBoolean()&&random.nextBoolean()){
                                userEntity.getInterestingBooks().add(book);
                            }
                        }));
                    }
                });
                users.add(userEntity);

                if(i%10==0){
                    System.out.println("进度: "+i/10+"%");
                }

            }
            userRepository.saveAll(users);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
