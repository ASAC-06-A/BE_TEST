package com.asac.study_hub.domain;

import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
@Builder
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;


    public void update(User newUser) {
//        this.name = Optional.ofNullable(newUser.getName()).orElse(this.name); / => 프로필 엔티티로이동
        this.password = Optional.ofNullable(newUser.getPassword()).orElse(this.password);

    }
    public void logout() {
        this.userStatus = UserStatus.INACTIVE;
    }

}
