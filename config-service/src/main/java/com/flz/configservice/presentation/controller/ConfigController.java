package com.flz.configservice.presentation.controller;

import com.flz.configservice.application.service.ConfigService;
import com.flz.configservice.presentation.dto.ConfigResponseDTO;
import com.flz.configservice.presentation.dto.ConfigSwitchStatusRequestDTO;
import com.flz.configservice.presentation.dto.ConfigUpsertRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {
    private final ConfigService configService;

    @GetMapping("/all")
    public List<ConfigResponseDTO> findAll() {
        return configService.findAll();
    }

    @GetMapping("/{id}")
    public ConfigResponseDTO findById(@PathVariable("id") String id) {
        return configService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody @Valid ConfigUpsertRequestDTO requestDTO) {
        configService.save(requestDTO);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") String id, @RequestBody @Valid ConfigUpsertRequestDTO requestDTO) {
        configService.edit(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        configService.delete(id);
    }

    @PutMapping("/{id}/switch-status")
    public void switchStatus(@PathVariable("id") String id, @RequestBody @Valid ConfigSwitchStatusRequestDTO requestDTO) {
        configService.switchStatus(id, requestDTO.getConfigStatus());
    }
}
