package com.example.springboot.service;

import com.example.springboot.DAO.QuestionDAO;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getAllQuestion(){
        return this.questionDAO.findAll();
    }
    public Question save (Question question) {
        return this.questionDAO.save(question);
    }
    public void deleteById (Integer id){
        this.questionDAO.deleteById(id);
    }
    public Optional<Question> findById (Integer id){
        return this.questionDAO.findById(id);
    }
    public Iterable<Question> findAllByContentContaining (String content){
        return questionDAO.findAllByContentContaining(content);
    }
    public Iterable<Question> findAllByCategory (Category category){
        return questionDAO.findAllByCategory(category);
    }
}
