package com.example.day24exercise.Service;

import com.example.day24exercise.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    // method that returns the list of articles
    public ArrayList<NewsArticle> getAllArticles() {
        return newsArticles;
    }


    // method that adds a new article to the list
    public void addArticle(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);

    }

    // method to update an article if it exist
    public boolean updateArticle(String id, NewsArticle newsArticle) {
        for (NewsArticle n : newsArticles) {
            if (n.getId().equals(id)) {
                newsArticles.set(newsArticles.indexOf(n), newsArticle);
                return true;
            }
        }
        return false;
    }

    // method to delete an article if it exist
    public Boolean deleteArticle(String id) {
        for (NewsArticle n : newsArticles) {
            if (n.getId().equals(id)) {
                newsArticles.remove(newsArticles.indexOf(n));
                return true;
            }
        }
        return false;
    }

    // method to publish article
    public boolean publishArticle(String id) {

        for (NewsArticle n : newsArticles) {
            if (n.getId().equals(id)) {
                if (!n.isPublished()) {
                    // set isPublished to true and set to current date
                    newsArticles.get(newsArticles.indexOf(n)).setPublished(true);
                    Date date = new Date();
                    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    newsArticles.get(newsArticles.indexOf(n)).setPublishDate(LocalDate.parse(modifiedDate));
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    // method to get all the published articles
    public ArrayList<NewsArticle> getPublishedArticles() {

        ArrayList<NewsArticle> publishedArticles = new ArrayList<>();
        for (NewsArticle n : newsArticles) {
            if (n.isPublished()) {
                publishedArticles.add(n);

            }
        }

        if (publishedArticles.isEmpty()) {
            return null;
        }
        return publishedArticles;

    }

    // method to get articles by category
    public ArrayList <NewsArticle> getByCategory (String category){
        ArrayList <NewsArticle> articleByCategory = new ArrayList<>();

        for (NewsArticle n : newsArticles){
            if (n.getCategory().equals(category)){
                articleByCategory.add(n);
            }
        }

        if (articleByCategory.isEmpty()){
            return null;
        }
        return articleByCategory;
    }

}
