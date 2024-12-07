package com.jitendra.quizapp.service;

import com.jitendra.quizapp.dao.QuestionDao;
import com.jitendra.quizapp.dao.QuizDao;
import com.jitendra.quizapp.model.Question;
import com.jitendra.quizapp.model.QuestionWrapper;
import com.jitendra.quizapp.model.Quiz;
import com.jitendra.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findByCategory(category);
        if (questions.size() < numQ) {
            return new ResponseEntity<>("Not enough questions in the selected category", HttpStatus.BAD_REQUEST);
        }
        questions = questions.subList(0, numQ); // Select only the required number
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Created quiz successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;

        // Iterate through the responses and compare with the correct answers
        for (int i = 0; i < responses.size() && i < questions.size(); i++) {
            Response response = responses.get(i);
            Question question = questions.get(i);

            // Compare user response with the correct answer
            if (response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }




}
