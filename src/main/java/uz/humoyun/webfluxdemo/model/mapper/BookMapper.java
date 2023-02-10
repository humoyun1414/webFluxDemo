package uz.humoyun.webfluxdemo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import uz.humoyun.webfluxdemo.domen.BookEntity;
import uz.humoyun.webfluxdemo.model.BookDto;
import uz.humoyun.webfluxdemo.model.request.BookRequest;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "bookName", source = "name")
    public abstract BookEntity toEntity(BookRequest bookRequest);

    public abstract BookDto toDto(BookEntity bookEntity);

    @Mapping(target = "bookName", source = "name")
    public abstract void update(@MappingTarget BookEntity bookEntity, BookRequest bookRequest);

}
