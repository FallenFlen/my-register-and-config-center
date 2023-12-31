package com.flz.configservice.presentation.dto;

import com.flz.common.enums.config.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigUpsertRequestDTO {
    @NotBlank
    private String content;
    @NotNull
    private ConfigType type;
    @NotBlank
    private String belongingApplicationName;
    @NotBlank
    private String fileName;
    private String description;
}
