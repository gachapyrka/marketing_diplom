package com.example.sweater.controller;

import com.example.sweater.domain.Order;
import com.example.sweater.domain.Profile;
import com.example.sweater.domain.UserInfo;
import com.example.sweater.repo.OrderRepo;
import com.example.sweater.repo.ProfileRepo;
import com.example.sweater.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;

    @GetMapping("/orders-view")
    public String getOrders(@AuthenticationPrincipal UserInfo user, Model model){
        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        Iterable<Order> orders = orderRepo.findAll();
        ArrayList<Order> ord = new ArrayList<>();
        for(Order o: orders){
            if(o.getProfile().getId() == profile.getId())
                ord.add(o);
        }

        model.addAttribute("orders", ord);
        return "orders-view";
    }

    @PostMapping("/order-delete/{order}")
    public String deleteOrder(@PathVariable Order order, Model model){
        orderRepo.delete(order);
        return "redirect:/orders-view";
    }

    @PostMapping("/add-order")
    public String addOrder(@AuthenticationPrincipal UserInfo user, @RequestParam String text, Model model){
        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        Order order = new Order();
        order.setProfile(profile);
        order.setText(text);

        orderRepo.save(order);
        return "redirect:/orders-view";
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserInfo user, Model model){
        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile(@AuthenticationPrincipal UserInfo user,
                              @RequestParam String departments, @RequestParam String credentials,
                              Model model){
        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        profile.setCredentials(credentials);
        profile.setDepartment(departments);
        profileRepo.save(profile);

        return "redirect:/profile";
    }

    @PostMapping("/delete-profile")
    public String deleteProfile(@AuthenticationPrincipal UserInfo user, Model model){
        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        Iterable<Order> orders = orderRepo.findAll();
        for(Order o: orders){
            if(o.getProfile().getId() == profile.getId())
                orderRepo.delete(o);
        }

        profileRepo.delete(profile);
        userInfoRepo.delete(profile.getUserInfo());

        return "redirect:/logout";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        return "logout";
    }
}
