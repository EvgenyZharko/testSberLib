package ru.zharko.sberlib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zharko.sberlib.dto.BookRentalDto;
import ru.zharko.sberlib.model.BookRental;
import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class, SeasonTicketMapper.class})
public interface BookRentalMapper {
    @Mapping(source = "seasonTicketDto", target = "seasonTicket")
    @Mapping(source = "bookDto", target = "book")
    BookRental toEntity (BookRentalDto bookRentalDto);

    @Mapping(source = "seasonTicket", target = "seasonTicketDto")
    @Mapping(source = "book", target = "bookDto")
    BookRentalDto toDto (BookRental bookRental);

    @Mapping(source = "seasonTicketDto", target = "seasonTicket")
    @Mapping(source = "bookDto", target = "book")
    List<BookRental> toListEntity (List<BookRentalDto> bookRentalDtoList);

    @Mapping(source = "seasonTicket", target = "seasonTicketDto")
    @Mapping(source = "book", target = "bookDto")
    List<BookRentalDto> toListDto (List<BookRental> bookRentalList);
}
