package com.example.sweater.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics")
public class Statistics {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private int coverage;
    private int displays;
    private int frequency;
    private int clicks;
    private int uniqueClicks;
}
