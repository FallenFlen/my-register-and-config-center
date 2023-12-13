package com.flz.configservice.presentation.controller;

import com.flz.configservice.application.service.ConfigService;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;

    @GetMapping("/all")
    private List<ConfigResponseDTO> findAll() {
        return configService.findAll();
    }
}
