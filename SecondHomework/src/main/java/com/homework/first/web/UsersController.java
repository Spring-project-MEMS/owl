package com.homework.first.web;

import com.homework.first.domain.UsersService;
import com.homework.first.exception.InvalidEntityException;
import com.homework.first.exception.InvalidPermissions;
import com.homework.first.model.User;
import com.homework.first.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping
    public String getUsers(Model model)
    {
        model.addAttribute("path", "users");
        model.addAttribute("users", service.findAll());
        return "users";
    }

    @GetMapping("{id}")
    public String getUserById(@PathVariable("id") String id, Model model){
        model.addAttribute("path", "users");
        model.addAttribute("user", service.findById(id));
        return "edit-user-form";
    }

    @GetMapping("/user-form")
    public String addUser(Model model){
        model.addAttribute("path", "user-form");
        return "user-form";
    }

    @PostMapping("/user-form")
    public String addUser(@ModelAttribute UserModel userModel, Model model){

        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setSecondName(userModel.getSecondName());
        user.setEmail(userModel.getEmail());
        user.setRole(userModel.getRole());
        user.setPassword("");

        service.add(user);

        model.addAttribute("path", "users");
        model.addAttribute("users", service.findAll());

        return "users";
    }

    @PostMapping("{id}/edit")
    public String update(@PathVariable("id") String id,  Model model, @ModelAttribute UserModel userModel){

        /*
        if(!id.equals(user.getId())){
            throw new InvalidEntityException(String.format("Entity with ID '%s' is different from URL resource ID '%s'", user.getId(), id));
        }
        */

        User user = service.findById(id);
        user.setFirstName(userModel.getFirstName());
        user.setSecondName(userModel.getSecondName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());

        service.update(user);

        model.addAttribute("path", "users");
        model.addAttribute("users", service.findAll());

        return "users";
    }

    @PostMapping("{id}/delete")
    public String remove(@PathVariable("id") String id, Model model){

        service.remove(id);
        model.addAttribute("path", "users");
        model.addAttribute("users", service.findAll());

        return "users";
    }
}
