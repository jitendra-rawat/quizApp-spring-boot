package com.jitendra.quizapp.controller;

import com.jitendra.quizapp.model.Question;
import com.jitendra.quizapp.model.QuestionWrapper;
import com.jitendra.quizapp.model.Response;
import com.jitendra.quizapp.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")


public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        System.out.println("Endpoint '/quiz/create' hit with params: " + category + ", " + numQ + ", " + title);
        return quizService.createQuiz(category, numQ, title);
    }


     @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);

     }


     @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List <Response> responses){
        return quizService.calculateResult(id,responses);
     }

}
