package com.example.sweater.service;

import com.example.sweater.domain.Statistics;
import com.example.sweater.repo.StatisticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MetricsService {
    @Autowired
    private StatisticsRepo statisticsRepo;

    private Date strToDate(String s){
        String pattern = "yyyy-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    public Map<Date, Double> getMetric(String _from, String _to, String type){
        Date from = strToDate(_from);
        Date to = strToDate(_to);

        List<Statistics> statisticsList = new ArrayList<>();
        Iterable<Statistics> stats = statisticsRepo.findAll();

        for(Statistics s: stats){
            if(from.getTime() <= s.getDate().getTime() && to.getTime() >= s.getDate().getTime()){
                statisticsList.add(s);
            }
        }

        Map<Date, Double> mp = new HashMap<>();

        if(statisticsList.size() <= 1)
            return mp;

        for(Statistics s: statisticsList){
            switch (type){
                case "CRonAd":
                    mp.put(s.getDate(), (double) ((s.getNumberOfClicksOnAd() / s.getNumberOfCoverage()) * 100));
                    break;
                case "CRusrReq":
                    mp.put(s.getDate(), (double) ((s.getNumberOfUserRequest() / s.getNumberOfCoverage()) * 100));
                    break;
                case "CRusrSub":
                    mp.put(s.getDate(), (double) ((s.getNumberOfUserSubscribe() / s.getNumberOfCoverage()) * 100));
                    break;
                case "CRusrOrd":
                    mp.put(s.getDate(), (double) ((s.getNumberOfUserOrder() / s.getNumberOfCoverage()) * 100));
                    break;
                case "CTR":
                    mp.put(s.getDate(), (double) ((s.getNumberOfClicksOnAd() / s.getNumberOfShowing()) * 100));
                    break;
                case "CPM":
                    mp.put(s.getDate(), (double) ((s.getTotalExpense() / s.getNumberOfShowing()) * 1000));
                    break;
                case "CPC":
                    mp.put(s.getDate(), (double) (s.getTotalExpense() / s.getNumberOfClicksOnAd()));
                    break;
                case "CPL":
                    mp.put(s.getDate(), (double) (s.getTotalExpense() / s.getNumberOfUserRequest()));
                    break;
                case "CPA":
                    mp.put(s.getDate(), (double) (s.getTotalExpense() / s.getNumberOfTargetedActions()));
                    break;
                case "CPO":
                    mp.put(s.getDate(), (double) (s.getTotalExpense() / s.getNumberOfOrders()));
                    break;
                case "DRR":
                    mp.put(s.getDate(), (double) ((s.getTotalExpense() / s.getTotalIncome()) * 100));
                    break;

            }
        }

        return mp;
    }
}
