package com.mx.procesamiento.configuraciones;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware{
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
