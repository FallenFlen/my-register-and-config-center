package com.flz.registryservice.presentation.controller;

import com.flz.registryservice.application.service.ServiceInfoService;
import com.flz.registryservice.presentation.dto.ServiceInfoRegisterRequestDTO;
import com.flz.registryservice.presentation.dto.ServiceInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-info")
public class ServiceInfoController {
    private final ServiceInfoService serviceInfoService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid ServiceInfoRegisterRequestDTO requestDTO) {
        serviceInfoService.register(requestDTO);
    }

    @GetMapping
    public ServiceInfoResponseDTO fetchServiceInfo(@RequestParam("name") String name) {
        return serviceInfoService.fetchServiceInfo(name);
    }
}
