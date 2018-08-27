package com.rob.Models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name="song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {
   // @XmlElement
    private int id;
   // @XmlElement
    private static int counter;
    //@XmlElement
    private String title;
    //@XmlElement
    private int likes;
    @XmlElement
    private List<String> commentListStrings = new ArrayList<String>();
    private Date date;

   // @XmlElement(name="comment")
    private List<Comment> commentList = new ArrayList<Comment>();

    public Song() {
        this.date=new Date();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", commentListStrings=" + commentListStrings +
                ", date=" + date +
                ", commentList=" + commentList +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Song(String title){
        this.title = title;
        this.id = counter++;
        this.date=new Date();
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

}
