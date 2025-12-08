package com.egregore.bot.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // Esto le dice a Spring: "Crea una tabla SQL para esto"
@Table(name = "user_reputation")
@Data // Lombok: Genera Getters, Setters, ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserReputation {

    @Id
    private Long discordId; // El ID único de Discord del usuario

    private String username; // Guardamos el nombre para saber quién es

    private int reputation; // Puntos de karma (-100 a +100)

    private int messagesSent; // Contador de mensajes

    private LocalDateTime lastInteraction; // Cuándo habló por última vez
}