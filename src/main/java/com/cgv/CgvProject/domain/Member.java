package com.cgv.CgvProject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    @Setter
    private String name;
    private String password;
    @Setter
    private String number;

    public Member(String userId, String password, String name, String number) {
        this.userId = userId;
        this.name = name;
        this.setPassword(password);
        this.number = number;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password) {
        return passwordEncoder.matches(password,this.password);
    }
}
