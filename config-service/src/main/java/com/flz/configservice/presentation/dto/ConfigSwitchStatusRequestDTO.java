package com.flz.configservice.presentation.dto;

import com.flz.common.enums.config.ConfigStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigSwitchStatusRequestDTO {
    @NotNull
    private ConfigStatus configStatus;
}
