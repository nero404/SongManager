package com.rob.Models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {
    private int id;
    private static int counterSongs;
    private String title;
    private int likes;

    private List<String> commentListStrings = new ArrayList<String>();

    @XmlElement(name="comment")
    private List<Comment> commentList = new ArrayList<Comment>();

    public Song() {
        this.id = counterSongs++;
    }

    public Song(String title){
        this.title = title;
        this.id = counterSongs++;
    }

    public void addCommentString(){

    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }

    public List<String> getCommentListStrings() {
        return commentListStrings;
    }

    public void setCommentListStrings(List<String> commentListStrings) {
        this.commentListStrings = commentListStrings;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void addLike() {
        likes++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", commentList=" + commentList +
                '}';
    }
}
