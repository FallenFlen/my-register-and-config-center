package com.flz.configservice.presentation.dto;

import com.flz.common.enums.config.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigSaveRequestDTO {
    @NotNull
    private String content;
    @NotNull
    private ConfigType type;
    @NotNull
    private String belongingApplicationName;
    private String description;
}
