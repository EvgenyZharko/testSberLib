package ru.zharko.sberlib.mapper;

import org.mapstruct.Mapper;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.model.SeasonTicket;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SeasonTicketMapper {
    SeasonTicket toEntity (SeasonTicketDto seasonTicketDto);
    SeasonTicketDto toDto (SeasonTicket seasonTicket);
    List<SeasonTicket> toListEntity (List<SeasonTicketDto> seasonTicketDtoList);
    List<SeasonTicketDto> toListDto (List<SeasonTicket> seasonTicketList);
}
