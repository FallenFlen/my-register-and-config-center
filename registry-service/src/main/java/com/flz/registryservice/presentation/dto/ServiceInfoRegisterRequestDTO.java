package com.flz.registryservice.presentation.dto;

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
public class ServiceInfoRegisterRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String host;
    @NotNull
    private Integer port;
}
