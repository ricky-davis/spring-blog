package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.AuthenticationService;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postDao;
    private UserRepository userDao;
    private final EmailService emailService;
    private AuthenticationService authSvc;

    public PostController(AuthenticationService authSvc,PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.authSvc = authSvc;
        this.emailService = emailService;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts")
    public String redirectToIndex() {
        return "redirect:/";
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
        post.setOwner(userDao.findOne(((User)authSvc.getCurUser()).getId()));
        postDao.save(post);
//        emailService.prepareAndSend(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        if(((User)authSvc.getCurUser()).getId() != post.getOwner().getId()) {
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        model.addAttribute("action", "/posts/"+id+"/edit");
        model.addAttribute("title", "Edit Post");

        return "posts/editCreate";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post) {
        post.setOwner(postDao.findById(post.getId()).getOwner());
        if(((User)authSvc.getCurUser()).getId() == post.getOwner().getId()) {
            postDao.save(post);
        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        Post post = postDao.findById(id);
        if(((User)authSvc.getCurUser()).getId() == post.getOwner().getId()) {
            postDao.delete(id);
        }
        return "redirect:/posts";
    }


    @GetMapping("/posts.json")
    @ResponseBody
    public Iterable<Post> viewAllAdsInJSONFormat() {
        return postDao.findAll();
    }

}
