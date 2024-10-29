package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
public class User {

    Integer id;
    String name;
    String email;
    String password;
    Enum<Status> status;


}
