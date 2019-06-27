package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("postsList",postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPostByID(@PathVariable long id, Model model){
        model.addAttribute("post",postDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/delete/{id}")
    public String getPostDelete(@PathVariable long id, Model model){
        Post post = postDao.findOne(id);
        postDao.delete(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String getPostEdit(@PathVariable long id, Model model){
        model.addAttribute("post",postDao.findOne(id));
        return "posts/edit";
    }
    @PostMapping("/posts/edit/{id}")
    public String PostPostEdit(@PathVariable long id,
                               @RequestParam String title,
                               @RequestParam String body){
        System.out.println(title);
        Post post = postDao.findOne(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts/"+id;
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
