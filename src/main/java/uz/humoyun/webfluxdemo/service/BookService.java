package uz.humoyun.webfluxdemo2.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.humoyun.webfluxdemo2.domen.BookEntity;
import uz.humoyun.webfluxdemo2.model.BookDto;
import uz.humoyun.webfluxdemo2.model.request.BookRequest;
import uz.humoyun.webfluxdemo2.repository.BookRepository;

import java.awt.print.Pageable;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;


    public BookService(final BookRepository bookRepository, final ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }


    public Mono<UUID> add(final BookRequest request) {
        BookEntity entity = objectMapper.convertValue(request, BookEntity.class);

        return bookRepository.save(entity).map(BookEntity::getId);
    }

    public Flux<BookDto> getAll(final Integer page, final Integer size) {
        return bookRepository.findAllBy(PageRequest.of(page, size))
                .map(bookEntity -> objectMapper.convertValue(bookEntity, BookDto.class));
    }

    public Mono<BookDto> getById(final UUID id) {
        return bookRepository.findById(id).
                switchIfEmpty(Mono.error(new NoSuchElementException("Argument not found by this id : " + id)))
                .map(bookEntity ->
                        objectMapper.convertValue(bookEntity, BookDto.class));
    }

    public Mono<Void> update(final UUID id, final BookRequest request) {

        return bookRepository
                .findById(id)
                .flatMap(bookEntity -> {
                    try {
                        BookEntity update = objectMapper.updateValue(bookEntity, request);
                        return bookRepository.save(update);
                    } catch (Exception e) {
                        return Mono.error(new IllegalArgumentException(e));
                    }
                })
                .map(o -> objectMapper.convertValue(o, BookDto.class))
                .then();

    }

    public Mono<Void> delete(final UUID id) {
        return bookRepository.deleteById(id);
    }

}
