package com.triple.clubmileage.api.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType implements CommonCode {

    ADD("생성"),
    MOD("수정"),
    DELETE("삭제");

    private String description;
}
