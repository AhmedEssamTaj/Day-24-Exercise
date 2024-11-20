package com.example.day24exercise.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty (message = "id cannot be null")
    private String id;

    @NotEmpty(message = "title cannot be empty")
    @Size(max = 100, message = "title must at max 100 letters")
    private String title;

    @NotEmpty(message = "author cannot be empty")
    @Size(min = 5, max = 20,message = "author size must be between 5 and 20")
    private String author;

    @NotEmpty(message = "content cannot be empty")
    @Size(min = 201, message = "content must be more than 200 letters")
    private String content;

    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp = "^(politics|sports|technology)$",message = "category must be politics|sports|technology")
    private String category;

    @NotEmpty(message = "image URL cannit be empty")
    private String imageUrl;


    private boolean isPublished;

    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate publishDate;


}
