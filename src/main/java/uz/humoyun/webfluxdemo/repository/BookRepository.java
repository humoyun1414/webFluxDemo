package uz.humoyun.webfluxdemo2.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.humoyun.webfluxdemo2.domen.BookEntity;

import java.util.UUID;
@Repository
public interface BookRepository extends R2dbcRepository<BookEntity, UUID> {
    Flux<BookEntity> findAllBy(final Pageable pageable);
}
