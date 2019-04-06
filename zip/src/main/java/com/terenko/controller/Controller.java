package com.terenko.controller;


import com.terenko.model.CustomUser;
import com.terenko.service.Service;
import com.terenko.service.UserService;
import com.terenko.repository.zipRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    @Autowired
    Service ser;
    @Autowired
    zipRepository ea;
    @Autowired
    UserService userService;
    @RequestMapping("/")
    public String show(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser dbUser = userService.getUserByLogin(login);
        model.addAttribute("files",ser.getAll(dbUser));
        return "index";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam MultipartFile File) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);

        try {
            ser.addToZip(File.getBytes(),File.getOriginalFilename(),dbUser);
        } catch (IOException e) {
            return "redirect:/";        }
        return "redirect:/";

    }
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String reset(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
            ser.reset(dbUser);
        return "redirect:/";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getZip(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
        try {
            ser.getZip(dbUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,

                         Model model) {
        if (userService.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }

        PasswordEncoder encoder= new StandardPasswordEncoder();
        String passHash = encoder.encode(password);

        CustomUser dbUser = new CustomUser(login, passHash);
        userService.addUser(dbUser);

        return "redirect:/";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        return "unauthorized";
    }
}
