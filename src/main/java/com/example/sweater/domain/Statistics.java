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
        String d = "";

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.MONTH)){
            case 1: d+="Январь ";break;
            case 2: d+="Февраль ";break;
            case 3: d+="Март ";break;
            case 4: d+="Апрель ";break;
            case 5: d+="Май ";break;
            case 6: d+="Июнь ";break;
            case 7: d+="Июль ";break;
            case 8: d+="Август ";break;
            case 9: d+="Сентябрь ";break;
            case 10: d+="Октябрь ";break;
            case 11: d+="Ноябрь ";break;
            case 12: d+="Декабрь ";break;
        }

        d += String.valueOf(cal.get(Calendar.YEAR));
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
