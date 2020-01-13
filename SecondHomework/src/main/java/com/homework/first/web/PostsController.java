package com.homework.first.web;

import com.homework.first.domain.PostService;
import com.homework.first.model.Post;
import com.homework.first.model.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

//@RestController
@Controller
@RequestMapping("/api/posts")
@Slf4j
public class PostsController {

    @Autowired
    private PostService service;

    /*
    @GetMapping
    public List<Post> getPosts(){
        return service.findAll();
    }
    */

    @GetMapping
    public String getPosts(Model model){
        model.addAttribute("path", "posts");
        model.addAttribute("posts", service.findAll());
        log.debug("GET Posts: {}",  service.findAll());
        return "posts";
    }

    @GetMapping("{id}")
    public String getPostsById(@PathVariable("id") String postId, Model model){
        model.addAttribute("path", "posts");
        model.addAttribute("post", service.findById(postId));
        return "edit-post-form";
    }

    @GetMapping("/post-form")
    public String addPost(Model model){
        model.addAttribute("path", "post-form");
        return "post-form";
    }

    @PostMapping("/post-form")
    public String addPost(@ModelAttribute PostModel postModel, Model model){

        Post post = new Post();
        post.setAuthor(postModel.getAuthor());
        post.setTitle(postModel.getTitle());
        post.setContent(postModel.getContent());
        post.setStatus(postModel.getStatus().equals("open"));

        service.add(post);
        //ResponseEntity.created(MvcUriComponentsBuilder.fromMethodName(PostsController.class, "addPost", Post.class).pathSegment("{id}").build(created.getId())).body(created);
        model.addAttribute("path", "posts");
        model.addAttribute("posts", service.findAll());
        return "posts";
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable("id") String id, Model model, @ModelAttribute PostModel postModel){

        /*
        if(!id.equals(post.getId())){
            throw new InvalidEntityException(String.format("Entity with id '%s' is different from URL resource id '%s'", post.getId(), id));
        }
        */

        Post post = service.findById(id);
        post.setAuthor(postModel.getAuthor());
        post.setTitle(postModel.getTitle());
        post.setContent(postModel.getContent());

        service.update(post);

        model.addAttribute("path", "posts");
        model.addAttribute("posts", service.findAll());
        return "posts";
    }

    //@DeleteMapping("{id}")
    @PostMapping("{id}/delete")
    public String remove(@PathVariable("id") String id, Model model){
        service.remove(id);
        model.addAttribute("path", "posts");
        model.addAttribute("posts", service.findAll());
        return "posts";
    }
}
