package ru.zharko.sberlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zharko.sberlib.model.SeasonTicket;
import java.util.Optional;

@Repository
public interface SeasonTicketRepository extends JpaRepository<SeasonTicket, Long> {
    Optional<SeasonTicket> findByClientFullNameIgnoreCase(String clientName);
    Optional<SeasonTicket> findByClientUsernameIgnoreCase(String userName);
    boolean existsBySeasonTicketId(Long seasonTicketId);
}
