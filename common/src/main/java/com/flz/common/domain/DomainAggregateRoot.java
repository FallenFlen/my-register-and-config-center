package com.flz.common.domain;

import com.flz.common.Constants;
import com.flz.common.utils.IdGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(value = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class DomainAggregateRoot {
    protected String id;
    protected String createBy;
    protected LocalDateTime createTime;
    protected String updateBy;
    protected LocalDateTime updateTime;
    protected Integer version;

    protected void generateId() {
        this.id = IdGenerator.randomId();
    }

    protected void createBySystem() {
        createBy(Constants.SYSTEM_USER);
    }

    protected void createBy(String userName) {
        this.createBy = userName;
        this.createTime = LocalDateTime.now();
        updateBy(userName);
    }

    protected void updateBySystem() {
        updateBy(Constants.SYSTEM_USER);
    }

    protected void updateBy(String userName) {
        this.updateBy = userName;
        this.updateTime = LocalDateTime.now();
    }
}
