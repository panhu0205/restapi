package com.example.restfulservice.user_jpa.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;
import javax.validation.Valid;

import com.example.restfulservice.user.bean.User;
import com.example.restfulservice.user.dao.UserDaoService;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.example.restfulservice.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
public class UserJPAController {

    @Autowired
    private UserDaoService service;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        // "all-users", SERVER_PATH + "/users"
        // retrieveAllUsers

        EntityModel<User> resource = EntityModel.of(user);

        Link link = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(link);

        // HATEOAS

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = service.deleteUserbyid(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

    }
}
