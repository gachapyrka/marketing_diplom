package com.example.sweater.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Report {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    private String text;
}
