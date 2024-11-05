package com.asac.study_hub.domain;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
@Builder
public class User {

    Integer id;
    String name;
    String email;
    String password;
//    Category profileCategory;
//    String selfIntroduction;

    public void update(User newUser) {
        this.name = Optional.ofNullable(newUser.getName()).orElse(this.name);
        this.password = Optional.ofNullable(newUser.getPassword()).orElse(this.password);
//        this.profileCategory = Optional.ofNullable(newUser.getProfileCategory())
//            .orElse(this.profileCategory);
//        this.selfIntroduction = Optional.ofNullable(newUser.getSelfIntroduction())
//            .orElse(this.selfIntroduction);
    }

    public void logout() {
        this.status = Status.DELETED;
    }

    Status status;


}
