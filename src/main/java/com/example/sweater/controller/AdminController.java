package com.example.sweater.controller;

import com.example.sweater.domain.Profile;
import com.example.sweater.domain.Role;
import com.example.sweater.domain.UserInfo;
import com.example.sweater.repo.ProfileRepo;
import com.example.sweater.repo.UserInfoRepo;
import com.example.sweater.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserInfoRepo userRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private MailService mailService;

    @GetMapping("/users")
    public String getUsers(Model model){
        Iterable<UserInfo> users = userRepo.findByRole(Role.USER);
        List<UserInfo> infos = new ArrayList<>();
        Iterable<Profile> profiles = profileRepo.findAll();
        for(UserInfo user: users){
            for(Profile p : profiles){
                if(p.getUserInfo().getId() == user.getId()){
                    user.setProfile(new Profile());
                    user.getProfile().setDepartment(p.getDepartment());
                    user.getProfile().setCredentials(p.getCredentials());
                }
            }
        }
        model.addAttribute("usrs", users);
        return "users";
    }

    @GetMapping("/marketers")
    public String getMarketers(Model model){
        Iterable<UserInfo> users = userRepo.findByRole(Role.MARKETER);
        List<UserInfo> infos = new ArrayList<>();
        Iterable<Profile> profiles = profileRepo.findAll();
        for(UserInfo user: users){
            for(Profile p : profiles){
                if(p.getUserInfo().getId() == user.getId()){
                    user.setProfile(new Profile());
                    user.getProfile().setDepartment(p.getDepartment());
                    user.getProfile().setCredentials(p.getCredentials());
                }
            }
        }
        model.addAttribute("usrs", users);
        return "marketers";
    }

    @PostMapping("/users/{user}")
    public String editUser(@PathVariable UserInfo user, Model model){
        user.setActive(!user.isActive());
        userRepo.save(user);
        return "redirect:/users";
    }

    @PostMapping("/marketers/{marketer}")
    public String editMarketer(@PathVariable UserInfo user, Model model){
        user.setActive(!user.isActive());
        userRepo.save(user);
        return "redirect:/marketers";
    }

    @GetMapping("/users/newsletter")
    public String getUsersNews(Model model){
        model.addAttribute("path", "/users/newsletter");
        return "users-newsletter";
    }

    @GetMapping("/marketers/newsletter")
    public String getMarketersNews(Model model){
        model.addAttribute("path", "/marketers/newsletter");
        return "users-newsletter";
    }

    @PostMapping("/users/newsletter")
    public String postUsersNews(@RequestParam String subj, @RequestParam String text, Model model){
        Iterable<UserInfo> users = userRepo.findByRole(Role.USER);
        for(UserInfo user: users){
            if(user.isActive())
                mailService.send(subj, text, user.getUsername(), null);
        }
        return "redirect:/users";
    }

    @PostMapping("/marketers/newsletter")
    public String postMarketersNews(@RequestParam String subj, @RequestParam String text, Model model){
        Iterable<UserInfo> users = userRepo.findByRole(Role.MARKETER);
        for(UserInfo user: users){
            if(user.isActive())
                mailService.send(subj, text, user.getUsername(), null);
        }
        return "redirect:/marketers";
    }
}
