package com.example.day24exercise.Controller;

import com.example.day24exercise.ApiResponse.ApiResponse;
import com.example.day24exercise.Model.NewsArticle;
import com.example.day24exercise.Service.NewsArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/news-article")
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    // Endpoint to get all the articles
    @GetMapping("/get-all")
    public ResponseEntity getAllArticle() {

        ArrayList<NewsArticle> articles = newsArticleService.getAllArticles();
        return ResponseEntity.status(200).body(articles);

    }

    // endpoint to add new article with validation
    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        newsArticleService.addArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("news article created successfully"));


    }

    // endpoint to update an EXISTING article
    @PutMapping("/update/{id}")
    public ResponseEntity updateArticle (@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if(newsArticleService.updateArticle(id,newsArticle)){
            return ResponseEntity.status(200).body(new ApiResponse("News Aritcle successfully updated"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("no article with that id exist"));
        }
    }

    // endpoint to delete an EXISTING article
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle (@PathVariable String id){
        if (newsArticleService.deleteArticle(id)){
            return ResponseEntity.status(200).body(new ApiResponse("article successfully deleted"));
        }else {
            return ResponseEntity.status((400)).body(new ApiResponse("no article with that id exist"));
        }
    }

    // endpoint to publish an article
    @PutMapping("/publish/{id}")
    public ResponseEntity publishArticle (@PathVariable String id){
        if (newsArticleService.publishArticle(id)){
            return ResponseEntity.status(200).body(new ApiResponse("article published successfully"));
        }else{
            return ResponseEntity.status(400).body(new ApiResponse("failed to publish article"));
        }
    }


    // endpoint to get all the published articles
    @GetMapping("/get-published")
    public ResponseEntity getPublishedArticles (){
        ArrayList <NewsArticle> publishedArticles = newsArticleService.getPublishedArticles();

        if (publishedArticles == null){
            return ResponseEntity.status(400).body(new ApiResponse("No published articles found!"));
        }
        return ResponseEntity.status(200).body(publishedArticles);

    }

    // endpoint to get articles by their category
    @GetMapping("/get-category/{category}")
    public ResponseEntity getByCategory (@PathVariable String category){

        ArrayList <NewsArticle> articleByCategory = newsArticleService.getByCategory(category);

        if (articleByCategory == null){
            return ResponseEntity.status(400).body(new ApiResponse
                    ("no article with this category has been found!"));

        }
        else {
            return ResponseEntity.status(200).body(articleByCategory);
        }


    }

}
