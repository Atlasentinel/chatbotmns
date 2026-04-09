package edu.ban7.chatbotmsnmsii2527.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String content;

    private String answer;

    private LocalDateTime create = LocalDateTime.now();

    @ManyToOne
    private AppUser user;

    @ManyToMany
    private List<Tag> includedTags = new ArrayList<>();

    @ManyToMany
    private List<Tag> excludedTags = new ArrayList<>();

}
