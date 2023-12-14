package com.flz.configservice.starter.dto;

import com.flz.common.dto.BaseResponseDTO;
import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;

import java.time.LocalDateTime;

public class ConfigResponseDTO extends BaseResponseDTO {
    private String content;
    private ConfigType type;
    private ConfigStatus status;
    private String belongingApplicationName;
    private String description;
    private String md5;

    public ConfigResponseDTO(String id, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime, String content, ConfigType type, ConfigStatus status, String belongingApplicationName, String description, String md5) {
        super(id, createBy, createTime, updateBy, updateTime);
        this.content = content;
        this.type = type;
        this.status = status;
        this.belongingApplicationName = belongingApplicationName;
        this.description = description;
        this.md5 = md5;
    }

    public ConfigResponseDTO(String content, ConfigType type, ConfigStatus status, String belongingApplicationName, String description, String md5) {
        this.content = content;
        this.type = type;
        this.status = status;
        this.belongingApplicationName = belongingApplicationName;
        this.description = description;
        this.md5 = md5;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ConfigType getType() {
        return type;
    }

    public void setType(ConfigType type) {
        this.type = type;
    }

    public ConfigStatus getStatus() {
        return status;
    }

    public void setStatus(ConfigStatus status) {
        this.status = status;
    }

    public String getBelongingApplicationName() {
        return belongingApplicationName;
    }

    public void setBelongingApplicationName(String belongingApplicationName) {
        this.belongingApplicationName = belongingApplicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}


