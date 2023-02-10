package uz.humoyun.webfluxdemo.v1;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.humoyun.webfluxdemo2.model.BookDto;
import uz.humoyun.webfluxdemo2.model.request.BookRequest;
import uz.humoyun.webfluxdemo2.service.BookService;

import java.util.UUID;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public Mono<UUID> add(@RequestBody BookRequest request) {
        return bookService.add(request);
    }

    @GetMapping
    public Flux<BookDto> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return bookService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Mono<BookDto> getById(@PathVariable(name = "id") UUID id) {
        return bookService.getById(id);
    }

    @PatchMapping("/{id}")
    public Mono<Void> update(@PathVariable(name = "id") UUID id, final BookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(value = "id") UUID id) {
        return bookService.delete(id);
    }

}
