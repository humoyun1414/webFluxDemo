package uz.humoyun.webfluxdemo.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;
import uz.humoyun.webfluxdemo.model.BookDto;
import uz.humoyun.webfluxdemo.model.request.BookRequestBuilder;
import uz.humoyun.webfluxdemo.repository.BookRepository;

class BookServiceTest {

    private final BookRepository bookRepository;
    private final BookService bookService;
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private BookRepository bookRepository;
    Faker FAKER= new Faker();

    BookServiceTest(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @BeforeEach
    void cleanDb() {
        bookRepository.deleteAll().subscribe();
    }

    @Test
    void add() {
        StepVerifier
                .create(bookService.add(
                        new BookRequestBuilder()
                                .name(FAKER.name().name())
                                .author(FAKER.book().author())
                                .price(FAKER.random().nextDouble())
                                .build()
                )).expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void getAll() {
        StepVerifier
                .create(bookService.getAll(0, 10)
                        .map(BookDto::price))

                .expectNext(1000.0).expectComplete().verify();
    }

    @Test
    void getById() {
        StepVerifier
                .create(bookService.add(
                        new BookRequestBuilder()
                                .name("Headway")
                                .author(FAKER.book().author())
                                .price(FAKER.random().nextDouble())
                                .build()
                ).flatMap(bookService::getById))
                .expectNextMatches(bookDto -> bookDto.bookName().equals("Headway"))
                .expectComplete()
                .verify();
    }

    @Test
    void update() {
        StepVerifier
                .create(
                        bookService.add(
                                new BookRequestBuilder()
                                        .name(FAKER.name().name())
                                        .author(FAKER.book().author())
                                        .price(FAKER.random().nextDouble())
                                        .build()
                        ).flatMap(uuid -> bookService.update(uuid,
                                5L,
                                new BookRequestBuilder()
                                        .name(FAKER.name().name())
                                        .author(FAKER.book().author())
                                        .price(FAKER.random().nextDouble())
                                        .build())
                        )
                ).expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void delete() {
        StepVerifier
                .create(
                        bookService.add(
                                new BookRequestBuilder()
                                        .name(FAKER.name().name())
                                        .author(FAKER.book().author())
                                        .price(FAKER.random().nextDouble())
                                        .build()
                        ).flatMap(bookService::delete)
                )
                .expectComplete()
                .verify();
    }
}