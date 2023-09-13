package com.example.sweater.controller;

import com.example.sweater.domain.Order;
import com.example.sweater.domain.Profile;
import com.example.sweater.domain.Statistics;
import com.example.sweater.domain.UserInfo;
import com.example.sweater.repo.OrderRepo;
import com.example.sweater.repo.ProfileRepo;
import com.example.sweater.repo.StatisticsRepo;
import com.example.sweater.service.MailService;
import com.example.sweater.service.MetricsService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('MARKETER')")
public class MarketerController {
    @Autowired
    private MailService mailService;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private StatisticsRepo statisticsRepo;

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/info")
    public String getInfo(Model model){
        model.addAttribute("stats", statisticsRepo.findAll());
        return "info";
    }

    @PostMapping("/info-delete/{stat}")
    public String deleteInfo(@PathVariable Statistics stat, Model model){
        statisticsRepo.delete(stat);
        return "redirect:/info";
    }

    @GetMapping("/add-info")
    public String getAddInfo(Model model){
        return "add-info";
    }

    @PostMapping("/add-info")
    public String postAddInfo(@RequestParam String date ,@RequestParam int numberOfCoverage,
                              @RequestParam int numberOfShowing ,@RequestParam int showingFrequencyForUser,
                              @RequestParam int numberOfClicksOnAd ,@RequestParam int numberOfUserRequest,
                              @RequestParam int numberOfUserSubscribe ,@RequestParam int numberOfUserOrder,
                              @RequestParam int numberOfRequests ,@RequestParam int numberOfSubscribes,
                              @RequestParam int numberOfOrders ,@RequestParam int numberOfTargetedActions,
                              @RequestParam double totalExpense ,@RequestParam double totalIncome,
                              Model model){
        Statistics stat = new Statistics();
        stat.setDateStr(date);
        stat.setNumberOfCoverage(numberOfCoverage);
        stat.setNumberOfShowing(numberOfShowing);
        stat.setShowingFrequencyForUser(showingFrequencyForUser);
        stat.setNumberOfClicksOnAd(numberOfClicksOnAd);
        stat.setNumberOfUserRequest(numberOfUserRequest);
        stat.setNumberOfUserSubscribe(numberOfUserSubscribe);
        stat.setNumberOfUserOrder(numberOfUserOrder);
        stat.setNumberOfRequests(numberOfRequests);
        stat.setNumberOfSubscribes(numberOfSubscribes);
        stat.setNumberOfOrders(numberOfOrders);
        stat.setNumberOfTargetedActions(numberOfTargetedActions);
        stat.setTotalExpense(totalExpense);
        stat.setTotalIncome(totalIncome);

        List<Statistics> lst = statisticsRepo.findByDate(stat.getDate());
        if(lst!= null && lst.size() != 0){
           stat.setId(lst.get(0).getId());

        }
        statisticsRepo.save(stat);

        return "redirect:/info";
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        model.addAttribute("orders", orderRepo.findAll());
        return "orders";
    }

    @GetMapping("/report/{order}")
    public String getOrder(@PathVariable Order order, Model model){
        model.addAttribute("order", order);
        return "report";
    }

    @PostMapping("/report/{order}")
    public String getOrder(@PathVariable Order order,
                           @RequestParam String from, @RequestParam String to, @RequestParam String type,
                           Model model){
        List<Pair<String, Double>> mp = metricsService.getMetric(from, to, type);

        model.addAttribute("order", order);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("type", metricsService.getTypeFormat(type));
        model.addAttribute("ttype", type);

        model.addAttribute("isOK", mp.size() <= 1);

        model.addAttribute("result", mp);
        model.addAttribute("ticks", metricsService.getTicks(type));
        model.addAttribute("maxTick", metricsService.getMaxTick(type));

        return "analyze";
    }

    @PostMapping("/mail/{order}")
    public String postMail(@PathVariable Order order,
                           @RequestParam String text, @AuthenticationPrincipal UserInfo user,
                           Model model){

        Profile profile = null;

        Iterable<Profile> profiles = profileRepo.findAll();
        for(Profile p : profiles){
            if(p.getUserInfo().getId() == user.getId()){
                profile = p;
                break;
            }
        }

        String subj = "Отчет по запросу";
        String t = "Добрый день! Предоставляем Вам отчет по запросу '" + order.getText() + "'\n\n\n";
        t+="Ответ: " + text +"\n\n\n";
        t+="С уважением "+ profile.getCredentials() +", отдел маркетинга.";

        mailService.send(subj, t, order.getProfile().getUserInfo().getUsername(), null);

        orderRepo.delete(order);

        return "redirect:/orders";
    }
}
