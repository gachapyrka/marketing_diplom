package com.example.sweater.service;

import com.example.sweater.domain.Statistics;
import com.example.sweater.repo.StatisticsRepo;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class MetricsService {
    @Autowired
    private StatisticsRepo statisticsRepo;

    private List<String> ticks;

    private Date strToDate(String s){
        String pattern = "yyyy-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    private String getDateStr(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String d = String.valueOf(cal.get(Calendar.YEAR));
        d+="-";
        switch (cal.get(Calendar.MONTH)){
            case 0: d+="01";break;
            case 1: d+="02";break;
            case 2: d+="03";break;
            case 3: d+="04";break;
            case 4: d+="05";break;
            case 5: d+="06";break;
            case 6: d+="07";break;
            case 7: d+="08";break;
            case 8: d+="09";break;
            case 9: d+="10";break;
            case 10: d+="11";break;
            case 11: d+="12";break;
        }

        return d;
    }

    public List<Pair<String, Double>> getMetric(String _from, String _to, String type){
        ticks = new ArrayList<>();

        Date from = strToDate(_from);
        Date to = strToDate(_to);

        List<Statistics> statisticsList = new ArrayList<>();
        Iterable<Statistics> stats = statisticsRepo.findAll();

        for(Statistics s: stats){
            if(from.getTime() <= s.getDate().getTime() && to.getTime() >= s.getDate().getTime()){
                statisticsList.add(s);
            }
        }

        List<Pair<String, Double>> mp = new ArrayList<>();

        if(statisticsList.size() <= 1)
            return mp;

        for(Statistics s: statisticsList){
            switch (type){
                case "CRonAd":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getNumberOfClicksOnAd() / s.getNumberOfCoverage()) * 100)));
                    break;
                case "CRusrReq":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getNumberOfUserRequest() / s.getNumberOfCoverage()) * 100)));
                    break;
                case "CRusrSub":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getNumberOfUserSubscribe() / s.getNumberOfCoverage()) * 100)));
                    break;
                case "CRusrOrd":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getNumberOfUserOrder() / s.getNumberOfCoverage()) * 100)));
                    break;
                case "CTR":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getNumberOfClicksOnAd() / s.getNumberOfShowing()) * 100)));
                    break;
                case "CPM":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getTotalExpense() / s.getNumberOfShowing()) * 1000)));
                    break;
                case "CPC":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getTotalExpense() / s.getNumberOfClicksOnAd()))));
                    break;
                case "CPL":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getTotalExpense() / s.getNumberOfUserRequest()))));
                    break;
                case "CPA":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) (s.getTotalExpense() / s.getNumberOfTargetedActions())));
                    break;
                case "CPO":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) (s.getTotalExpense() / s.getNumberOfOrders())));
                    break;
                case "DRR":
                    mp.add(new Pair<>(getDateStr(s.getDate()), (double) ((s.getTotalExpense() / s.getTotalIncome()) * 100)));
                    break;

            }
        }

        return mp;
    }

    public List<String> getTicks(String type){
        switch (type){
            case "DRR":
            case "CRonAd":
            case "CRusrReq":
            case "CRusrSub":
            case "CRusrOrd":
            case "CTR":
                ticks = getPercentageTicks();
                break;
            case "CPM":
            case "CPC":
            case "CPL":
            case "CPA":
            case "CPO":
                ticks = getCostTicks();
                break;


        }
        return ticks;
    }

    public Double getMaxTick(String type){
        switch (type){
            case "DRR":
            case "CRonAd":
            case "CRusrReq":
            case "CRusrSub":
            case "CRusrOrd":
            case "CTR":
                return 100.0;
            case "CPM":
            case "CPC":
            case "CPL":
            case "CPA":
            case "CPO":
                return 1000.0;
        }
        return 0.0;
    }

    private List<String> getPercentageTicks(){
        List<String> l = new ArrayList<>();
        l.add("10%");
        l.add("8%");
        l.add("6%");
        l.add("4%");
        l.add("2%");
        return l;
    }

    private List<String> getCostTicks(){
        List<String> l = new ArrayList<>();
        l.add("1000 руб.");
        l.add("800 руб.");
        l.add("600 руб.");
        l.add("400 руб.");
        l.add("200 руб.");
        return l;
    }

    public String getTypeFormat(String type){
        switch (type){
            case "CRonAd":
                return "CR кликов по объявлению";
            case "CRusrReq":
                return "CR пользователей, оставивших заявку";
            case "CRusrSub":
                return "CR пользователей, которые подписались";
            case "CRusrOrd":
                return "CR пользователей, совершивших покупку";
            case "CTR":
                return "CTR";
            case "CPM":
                return "CPM";
            case "CPC":
                return "CPC";
            case "CPL":
                return "CPL";
            case "CPA":
                return "CPA";
            case "CPO":
                return "CPO";
            case "DRR":
                return "DRR";
        }
        return "";
    }
}
