package uz.humoyun.webfluxdemo2.model;

import java.time.Instant;
import java.util.UUID;

public record BookDto(String author, Instant createdDate, UUID id, Instant lastModifiedDate, String bookName,
                      Double price) {
}
