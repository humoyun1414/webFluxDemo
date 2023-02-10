package uz.humoyun.webfluxdemo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.humoyun.webfluxdemo.domen.BookEntity;
import uz.humoyun.webfluxdemo.model.BookDto;
import uz.humoyun.webfluxdemo.model.mapper.BookMapper;
import uz.humoyun.webfluxdemo.model.request.BookRequest;
import uz.humoyun.webfluxdemo.repository.BookRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = BookMapper.INSTANCE;
    }


    public Mono<UUID> add(final BookRequest request) {
        BookEntity bookEntity = bookMapper.toEntity(request);


        return bookRepository.save(bookEntity).map(BookEntity::getId);
    }

    public Flux<BookDto> getAll(final Integer page, final Integer size) {
        return bookRepository.findAllBy(PageRequest.of(page, size))
                .map(bookMapper::toDto);
    }

    public Mono<BookDto> getById(final UUID id) {
        return bookRepository.findById(id).
                switchIfEmpty(Mono.error(new NoSuchElementException("Argument not found by this id : " + id)))
                .map(bookMapper::toDto);
    }

    public Mono<Void> update(final UUID id, final Long version, final BookRequest request) {

        return bookRepository
                .findById(id)
                .flatMap(bookEntity -> {
                    bookEntity.setVersion(version);
                    bookMapper.update(bookEntity, request);
                    return bookRepository.save(bookEntity);
                })
                .then();

    }

    public Mono<Void> delete(final UUID id) {
        return bookRepository.deleteById(id);
    }

}
