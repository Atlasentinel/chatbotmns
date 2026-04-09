package edu.ban7.chatbotmsnmsii2527.controller;

import edu.ban7.chatbotmsnmsii2527.dao.QuestionDao;
import edu.ban7.chatbotmsnmsii2527.dao.TagDao;
import edu.ban7.chatbotmsnmsii2527.dto.QuestionRequest;
import edu.ban7.chatbotmsnmsii2527.model.AppUser;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import edu.ban7.chatbotmsnmsii2527.model.Tag;
import edu.ban7.chatbotmsnmsii2527.security.AppUserDetails;
import edu.ban7.chatbotmsnmsii2527.security.IsUser;
import edu.ban7.chatbotmsnmsii2527.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatbotController {

    protected final AiService aiService;
    protected final QuestionDao questionDao;
    protected final TagDao tagDao;

    @PostMapping("/ask")
    @IsUser
    public ResponseEntity<String> ask(
            @AuthenticationPrincipal AppUserDetails userDetails,
            @RequestBody QuestionRequest questionRequest) {

        List<Tag> includedTags = tagDao.findAllById(questionRequest.includedTags());
        List<Tag> excludedTags = tagDao.findAllById(questionRequest.excludedTagIds());

        String answer = aiService.askGemini(questionRequest.content(), includedTags, excludedTags);

        System.out.println(userDetails.getUser().getPseudo());

        Question question = new Question();
        question.setContent(questionRequest.content());
        question.setAnswer(answer);
        question.setUser(userDetails.getUser());
        question.setIncludedTags(includedTags);
        question.setExcludedTags(excludedTags);
        questionDao.save(question);

        return new ResponseEntity<>(
                aiService.askGemini(questionRequest.content(), includedTags, excludedTags),
                HttpStatus.OK);
    }

    @GetMapping("/history")
    @IsUser
    public ResponseEntity<List<Question>> history(
            @AuthenticationPrincipal AppUserDetails userDetails) {

        AppUser user = userDetails.getUser();
        List<Question> questions = user.isAdmin()
                ? questionDao.findAll()
                : questionDao.findQuestionBy(user.getId());

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
