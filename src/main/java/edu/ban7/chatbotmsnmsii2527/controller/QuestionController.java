package edu.ban7.chatbotmsnmsii2527.controller;

import edu.ban7.chatbotmsnmsii2527.dao.QuestionDao;
import edu.ban7.chatbotmsnmsii2527.dao.TagDao;
import edu.ban7.chatbotmsnmsii2527.dto.QuestionRequest;
import edu.ban7.chatbotmsnmsii2527.model.AppUser;
import edu.ban7.chatbotmsnmsii2527.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    TagDao tagDao;

    @PostMapping
    public Question ask(@RequestBody QuestionRequest dto, @AuthenticationPrincipal AppUser user){
        Question question = new Question();
        question.setContent(dto.content());
        question.setUser(user);
        question.setIncludedTags(tagDao.findAllById(dto.includedTags()));
        question.setExcludedTags(tagDao.findAllById(dto.excludedTagIds()));

        return questionDao.save(question);
    }

    public List<Question> history(@AuthenticationPrincipal AppUser user)
    {
        if(user.isAdmin())
        {
            return questionDao.findAll();
        }
        return questionDao.findByUserId(user.getId());
    }

}
