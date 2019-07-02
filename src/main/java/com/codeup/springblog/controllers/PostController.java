package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private PostRepository postDao;
    private UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.emailService = emailService;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("action", "/posts/create");
        model.addAttribute("title", "Create Post");
        return "posts/editCreate";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post) {
        post.setOwner(userDao.findOne(1L));
        postDao.save(post);
        emailService.prepareAndSend(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Post post = postDao.findOne(id);
        model.addAttribute("post", postDao.findOne(id));
        model.addAttribute("action", "/posts/"+id+"/edit");
        model.addAttribute("title", "Edit Post");

        return "posts/editCreate";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.delete(id);
        return "redirect:/posts";
    }


}
