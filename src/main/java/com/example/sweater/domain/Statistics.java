package com.example.sweater.domain;

import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private int numberOfCoverage; //охват, кол-во уникальных пользователей, которые видели рекламу
    private int numberOfShowing; //общее кол-во показов рекламы (1 человек мог видеть одну рекламу несколько раз)
    private int showingFrequencyForUser; //частота показов, кол-во показов для каждого отдельного пользователя
    private int numberOfClicksOnAd; //кол-во кликов по объявлению
    private int numberOfUserRequest; //кол-во пользователей, оставивших заявку
    private int numberOfUserSubscribe; //кол-во пользователей, которые подписались
    private int numberOfUserOrder; //кол-во пользователей, которые совершили покупку

    private int numberOfRequests; //кол-во оставленных заявок;
    private int numberOfSubscribes; //кол-во подписок;
    private int numberOfOrders; //кол-во совершенных покупок;
    private int numberOfTargetedActions; //кол-во совершенных целевых действий (покупка, добавление товара в корзину, заказ звонка, подписка и т.д.)

    private double totalExpense; //кол-во общих расходов на рекламу;
    private double totalIncome; //кол-во дохода с рекламы;

    public String getDateStr(){
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


    public boolean setDateStr(String s){
        String pattern = "yyyy-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
