package com.flz.configservice.presentation.dto;

import com.flz.common.dto.BaseResponseDTO;
import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConfigResponseDTO extends BaseResponseDTO {
    private String content;
    private ConfigType type;
    private ConfigStatus status;
    private String belongingApplicationName;
    private String fileName;
    private String description;
    private String md5;
}
