package com.example.sweater.controller;

import com.example.sweater.domain.Profile;
import com.example.sweater.domain.Role;
import com.example.sweater.domain.UserInfo;
import com.example.sweater.repo.ProfileRepo;
import com.example.sweater.repo.UserInfoRepo;
import com.example.sweater.service.MailService;
import com.example.sweater.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserInfoRepo userRepo;

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private MailService mailService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String departments, @RequestParam String credentials,
            @RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword,
                          Map<String, Object> model) {
        Profile profile = new Profile();
        profile.setDepartment(departments);
        profile.setCredentials(credentials);
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        profile.setUserInfo(user);

        if(!user.getPassword().equals(repeatPassword)){
            model.put("message", "Пароли должны совпадать!");
            return "registration";
        }

        UserInfo userFromDb = userRepo.findByUsername(profile.getUserInfo().getUsername());

        if (userFromDb != null) {
            model.put("message", "Данный логин уже занят!");
            return "registration";
        }
        if(profile.getDepartment().equals("Отдел маркетинга"))
            profile.getUserInfo().setRole(Role.MARKETER);
        else
            profile.getUserInfo().setRole(Role.USER);

        String key = registrationService.getKey(profile);
        String text = "Ваш код-подтверждение: " + key + "\n\n\nНе сообщайте код никому!";

        mailService.send("Код подтверждения", text, profile.getUserInfo().getUsername(), null);

        model.put("message", key);

        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation(Model model) {
        return "confirmation";
    }

    @PostMapping("/confirmation")
    public String confirm(@RequestParam String key, Model model) {
        if(!registrationService.checkKey(key))
            return "redirect:/confirmation";

        Profile p = registrationService.getProfile();
        userRepo.save(p.getUserInfo());
        profileRepo.save(p);

        return "redirect:/login";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        int size = 0;
        Iterable<UserInfo> coll = userRepo.findByRole(Role.ADMIN);
        for (UserInfo i : coll) {
            size++;
        }
        if(size == 0){
            UserInfo info = new UserInfo();
            info.setUsername("admin@admin.com");
            info.setPassword("admin");
            info.setRole(Role.ADMIN);
            info.setActive(true);
            userRepo.save(info);
        }

        return "main";
    }
}
