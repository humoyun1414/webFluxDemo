package uz.humoyun.webfluxdemo.model.request;

import org.immutables.builder.Builder;

public record BookRequest(String author, String name, Double price) {

    @Builder.Constructor
    public BookRequest {
    }
}
