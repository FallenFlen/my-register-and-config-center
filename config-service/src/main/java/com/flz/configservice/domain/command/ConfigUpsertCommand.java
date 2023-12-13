package com.flz.configservice.domain.command;

import com.flz.common.enums.config.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigUpsertCommand {
    private String content;
    private ConfigType type;
    private String belongingApplicationName;
    private String description;
}
