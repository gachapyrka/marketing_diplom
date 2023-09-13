package com.example.sweater.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "ФИО не может быть пустым")
    private String credentials;

    @NotBlank(message = "Отдел не может быть пустым")
    private String department;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "userInfoId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserInfo userInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Order> orders;
}
