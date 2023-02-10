package uz.humoyun.webfluxdemo.api.v1;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.humoyun.webfluxdemo.model.BookDto;
import uz.humoyun.webfluxdemo.model.request.BookRequest;
import uz.humoyun.webfluxdemo.service.BookService;

import java.util.UUID;

import static uz.humoyun.webfluxdemo.utils.Constants.DEFAULT_VALUE_PAGE;
import static uz.humoyun.webfluxdemo.utils.Constants.DEFAULT_VALUE_SIZE;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Mono<UUID> add(@RequestBody BookRequest request) {
        return bookService.add(request);
    }

    @GetMapping
    public Flux<BookDto> getAll(@RequestParam(name = "page", defaultValue = DEFAULT_VALUE_PAGE) Integer page,
                                @RequestParam(name = "size", defaultValue = DEFAULT_VALUE_SIZE) Integer size) {
        return bookService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Mono<BookDto> getById(@PathVariable(name = "id") UUID id) {
        return bookService.getById(id);
    }

    @PatchMapping("/{id}/{version}")
    public Mono<Void> update(@PathVariable(name = "id") UUID id, @PathVariable Long version, @RequestBody final BookRequest request) {
        return bookService.update(id, version, request);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(value = "id") UUID id) {
        return bookService.delete(id);
    }

}
