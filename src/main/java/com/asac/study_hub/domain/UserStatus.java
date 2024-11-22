package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum UserStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    DELETED("삭제된 데이터");

    String status;
}
