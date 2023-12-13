package com.flz.configservice.domain.aggregate;

import com.flz.common.domain.DomainAggregateRoot;
import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
public class Config extends DomainAggregateRoot {
    private String content;
    private ConfigType type;
    private ConfigStatus status;
    private String belongingApplicationName;
    private String description;
    private String md5;
}
