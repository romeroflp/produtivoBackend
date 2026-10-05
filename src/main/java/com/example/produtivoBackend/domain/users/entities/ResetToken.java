package com.example.produtivoBackend.domain.users.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "reset_token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetToken {
    @Id
    private UUID token;
    private UUID userId;
    private Instant expiredAt;
    private boolean isUsed;

}
