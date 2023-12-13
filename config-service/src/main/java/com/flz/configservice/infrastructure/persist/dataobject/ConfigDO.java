package com.flz.configservice.infrastructure.persist.dataobject;

import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;
import com.flz.common.persist.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("config")
public class ConfigDO extends BaseDO {
    private String content;
    private ConfigType type;
    private ConfigStatus status;
    private String belongingApplicationName;
    private String description;
    private String md5;
}
