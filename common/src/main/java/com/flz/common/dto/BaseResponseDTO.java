package com.flz.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseResponseDTO {
    protected String id;
    protected String createBy;
    protected LocalDateTime createTime;
    protected String updateBy;
    protected LocalDateTime updateTime;
}
