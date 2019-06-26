package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String getPostsDescription(Model model){
        ArrayList<Post> postsList = new ArrayList<>();
        Post post = new Post("Boop","Bop");
        Post post2 = new Post("beep","bip");
        postsList.add(post);
        postsList.add(post2);
        model.addAttribute("postsList",postsList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostByIDDescription(@PathVariable int id, Model model){
        Post post = new Post("Boop","Bop");
        model.addAttribute("post",post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getPostCreateDescription(){
        return "view the form for creating a post";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String PostCreatePostDescription(){
        return "create a new post";
    }
}
