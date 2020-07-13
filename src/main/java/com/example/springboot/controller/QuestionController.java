package com.example.springboot.controller;


import com.example.springboot.entity.Category;
import com.example.springboot.entity.Question;
import com.example.springboot.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuestionController {
    private final Logger log = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAllQuestion (){
        log.debug("---------------REST request to getAllQuestion--------------------");
        return new ResponseEntity<List<Question>>(questionService.getAllQuestion(), HttpStatus.OK);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public Question createQuestion (@RequestBody Question question){
        log.debug("---------------REST request to createQuestion--------------------");
        return this.questionService.save(question);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
    public void deleteQuestionById (@PathVariable(value = "id") Integer id){
        log.debug("---------------REST request to deleteQuestion--------------------");
        this.questionService.deleteById(id);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion (@RequestBody Question question, @PathVariable(value = "id") Integer id) {
        log.debug("---------------REST request to updateQuestion--------------------");
        Optional<Question> questionOptional = questionService.findById(id);
        question.setId(questionOptional.get().getId());
        questionService.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAllQuestionByContent", method = RequestMethod.GET)
    public ResponseEntity <Iterable<Question>> findAllQuestionByContent (@RequestParam("content") String content){
        Iterable<Question> questions = questionService.findAllByContentContaining(content);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @RequestMapping(value = "/findAllQuestionByCategory", method = RequestMethod.GET)
    public ResponseEntity <Iterable<Question>> findAllQuestionByCategory (@RequestParam("category") Category category){
        Iterable<Question> questions = questionService.findAllByCategory(category);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> questionDetail(@PathVariable Integer id){
        Optional<Question> questionOptional = questionService.findById(id);
        return questionOptional.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
