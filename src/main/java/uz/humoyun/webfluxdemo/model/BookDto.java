package uz.humoyun.webfluxdemo.model;

import java.time.Instant;
import java.util.UUID;

public record BookDto(
        String author,
        Long version,
        Instant createdDate,
        UUID id,
        Instant lastModifiedDate,
        String bookName,
        Double price) {
}
