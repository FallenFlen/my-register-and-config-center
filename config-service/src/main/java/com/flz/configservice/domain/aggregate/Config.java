package com.flz.configservice.domain.aggregate;

import com.flz.common.domain.DomainAggregateRoot;
import com.flz.common.enums.config.ConfigStatus;
import com.flz.common.enums.config.ConfigType;
import com.flz.configservice.domain.command.ConfigSaveCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Config extends DomainAggregateRoot {
    private String content;
    private ConfigType type;
    private ConfigStatus status;
    private String belongingApplicationName;
    private String description;
    private String md5;

    public static Config create(ConfigSaveCommand command) {
        Config config = Config.builder()
                .content(command.getContent())
                .type(command.getType())
                .belongingApplicationName(command.getBelongingApplicationName())
                .description(command.getDescription())
                .status(ConfigStatus.ENABLE)
                .md5(DigestUtils.md5DigestAsHex(command.getContent().getBytes(StandardCharsets.UTF_8)))
                .build();
        config.generateId();
        config.createBySystem();
        return config;
    }
}
