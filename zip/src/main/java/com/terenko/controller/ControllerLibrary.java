package com.terenko.controller;


import com.terenko.model.CustomUser;
import com.terenko.repository.zipRepository;
import com.terenko.service.Service;
import com.terenko.service.UserService;
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
public class ControllerLibrary {
    @Autowired
    Service ser;
    @Autowired
    UserService userService;
    @RequestMapping("/zips")
    public String show(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser dbUser = userService.getUserByLogin(login);
        model.addAttribute("files",ser.getAllZip(dbUser));
        return "library";
    }

    @RequestMapping(value = "/zips/resetzip", method = RequestMethod.POST)
    public String reset(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);
            ser.resetZip(dbUser);
        return "redirect:/zips";
    }

    @RequestMapping(value = "/zips/get", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getZip(Model model,@RequestParam Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();

        CustomUser dbUser = userService.getUserByLogin(login);

        try {
            byte[] zip=ser.getZip(dbUser,id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/zip"));
            headers.add("Content-Disposition", "attachment; filename=\"YourArchive.zip\"");
            return new ResponseEntity<byte[]>(zip,headers,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
