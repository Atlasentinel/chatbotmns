package edu.ban7.chatbotmsnmsii2527.dto;

import java.util.List;


public record QuestionRequest( String content,
        List<Integer> includedTags,
        List<Integer> excludedTagIds) {
}
