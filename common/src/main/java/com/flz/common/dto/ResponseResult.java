package com.flz.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
@Setter
public class ResponseResult {
    private String message;
    private Object data;

    public static ResponseResult success() {
        return ResponseResult.of("Success", null);
    }
}
