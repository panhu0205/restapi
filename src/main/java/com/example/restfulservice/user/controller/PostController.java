package com.example.restfulservice.user.controller;

import java.net.URI;
import java.util.List;

import com.example.restfulservice.exception.PostNotFoundException;
import com.example.restfulservice.user.bean.Post;
import com.example.restfulservice.user.dao.PostDaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PostController {
    
    @Autowired
    private PostDaoService postservice;

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts (){
        return postservice.showAllposts();
    }

    @GetMapping("/users/{id}/posts/{postid}")
    public Post retrieveAPost(@PathVariable Integer postid){
        Post post = postservice.findOne(postid);
        if (post== null){
            throw new PostNotFoundException ("postid-"+postid);
        }
        return postservice.findOne(postid);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createUser(@RequestBody Post post){
        Post savedpost = postservice.savepost(post);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{postid}")
            .buildAndExpand(savedpost.getPostid()).toUri();

        return ResponseEntity.created(location).build();
    }
}
