package com.flz.common.persist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDO {
    @Id
    protected String id;
    protected String createBy;
    @CreatedDate
    protected LocalDateTime createTime;
    protected String updateBy;
    @LastModifiedDate
    protected LocalDateTime updateTime;
    @Version
    protected Integer version;
}
