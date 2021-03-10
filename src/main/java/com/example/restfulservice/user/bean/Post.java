package com.example.restfulservice.user.bean;

public class Post {
    private Integer postid;
    private String author;
    private String content;

    protected Post(){

    }

    public Post(Integer postid, String author, String content){
        super();
        this.postid= postid;
        this.author= author;
        this.content= content;
    }

    public Integer getPostid(){
        return postid;
    }

    public void setPostid(Integer postid){
        this.postid= postid;
    }

    public String getAuthor(){
        return author;
    }

    
    public void setAuthor(String author){
        this.author= author;
    }

    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content= content;
    }

    @Override
    public String toString(){
        return String.format("Post [id=%s, content=%s, author=%s]", postid, content, author);
    }
}
