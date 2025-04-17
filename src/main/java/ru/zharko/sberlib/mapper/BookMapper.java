package ru.zharko.sberlib.mapper;

import org.mapstruct.Mapper;
import ru.zharko.sberlib.dto.BookDto;
import ru.zharko.sberlib.model.Book;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity (BookDto bookDto);
    BookDto toDto (Book book);
    List<Book> toListEntity (List<BookDto> bookDtoList);
    List<BookDto> toListDto (List<Book> bookList);
}
