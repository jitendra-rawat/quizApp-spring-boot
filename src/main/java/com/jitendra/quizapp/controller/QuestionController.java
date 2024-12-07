package com.jitendra.quizapp.controller;


import com.jitendra.quizapp.model.Question;
import com.jitendra.quizapp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    QuestionService questionService;


    @PostMapping("/add-question")
    public ResponseEntity<String>  addQuestion(@RequestBody Question question){
        System.out.println("received payload"+question);
        return    questionService.addQuestion(question);
    }

    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>>  getAllQuestions(){
        return questionService.getAllQuestions();
    }

@GetMapping("/category/{category}")
    public  ResponseEntity<List<Question>>  getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }














}
