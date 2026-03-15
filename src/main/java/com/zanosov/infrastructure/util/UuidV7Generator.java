package com.zanosov.infrastructure.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import com.zanosov.domain.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidV7Generator implements IdGenerator {

    private static final TimeBasedEpochGenerator GENERATOR = Generators.timeBasedEpochGenerator();

    @Override
    public UUID generate() {
        return GENERATOR.generate();
    }
}
