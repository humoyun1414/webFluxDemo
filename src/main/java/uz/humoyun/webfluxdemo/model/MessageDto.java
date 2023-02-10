package uz.humoyun.webfluxdemo.model;

import org.immutables.builder.Builder;

import java.util.UUID;

public record MessageDto(UUID id, String message, String owner, String something) {
    @Builder.Constructor
    public MessageDto {
    }
}
