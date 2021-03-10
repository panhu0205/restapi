package com.example.restfulservice.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.restfulservice.user.bean.Post;
import com.example.restfulservice.user.bean.User;

import org.springframework.stereotype.Component;


@Component
public class PostDaoService {
    private static List<Post> posts = new ArrayList(); 
    private Integer postcount=2;

    static {
        posts.add(new Post(1, "Hello", "John"));
        posts.add(new Post(2, "Nice to meet you", "Simon"));
    }

    public List<Post> showAllposts(){
        return posts;
    }

    public Post savepost(Post post){
        if (post.getPostid() == null){
            post.setPostid(++postcount);
        }
        posts.add(post);
        return post;
    }

    public Post findOne (Integer postid){
        for (Post post:posts){
            if (post.getPostid() == postid){
            return post;
            }
        }
        return null;
    }

}
