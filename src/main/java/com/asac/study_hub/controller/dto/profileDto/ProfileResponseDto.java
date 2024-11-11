package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponseDto {

    Integer userId;
    String name;
    String email;
    String password; //password가 들어갈 필요가 있나? 클라이언트로 넘겨줄 이유가 없다고 생각 -> 정현님과 협의
    Status status; //status같은 경우도 서버쪽에서만 알아도 된다고 생각
    //네트워크 통해서 정보를 넘기는 것이므로 민감한 정보는 최대한 제외

    private ProfileResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }

    public static ProfileResponseDto of(User user) {
        return new ProfileResponseDto(user);
    }
}
