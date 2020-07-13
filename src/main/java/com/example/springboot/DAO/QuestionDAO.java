package com.example.springboot.DAO;

import com.example.springboot.entity.Category;
import com.example.springboot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    Iterable<Question> findAllByContentContaining (String content);
    Iterable<Question> findAllByCategory (Category category);
}
