package ru.zharko.sberlib.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "season_tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeasonTicket {

    @Id
    @Column(name = "season_ticket_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "season_tickets_id_seq")
    @SequenceGenerator(name = "season_tickets_id_seq", allocationSize = 1)
    private Long seasonTicketId;

    @Column(name = "client_username", nullable = false, unique = true)
    private String clientUsername;

    @Column(name = "clientFullName", nullable = false)
    private String clientFullName;

    @Column(name = "season_ticket_status")
    private Boolean seasonTicketStatus;
}
