package uz.humoyun.webfluxdemo.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import uz.humoyun.webfluxdemo.domen.BookEntity;
import uz.humoyun.webfluxdemo.model.request.BookRequest;
import uz.humoyun.webfluxdemo.repository.BookRepository;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    public abstract BookEntity toEntity(BookRequest bookRequest);

    public abstract BookDto toDto(BookEntity bookEntity);

    public abstract BookEntity update(@MappingTarget BookEntity bookEntity , BookRequest bookRequest);

}
