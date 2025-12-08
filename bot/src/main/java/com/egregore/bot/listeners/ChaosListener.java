package com.egregore.bot.listeners;

import com.egregore.bot.persistence.entity.UserReputation;
import com.egregore.bot.persistence.repository.UserReputationRepository;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChaosListener extends ListenerAdapter {

    private final UserReputationRepository reputationRepo;

    // Inyección de dependencias por constructor
    public ChaosListener(UserReputationRepository reputationRepo) {
        this.reputationRepo = reputationRepo;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        long userId = event.getAuthor().getIdLong();
        String userName = event.getAuthor().getName();
        String message = event.getMessage().getContentRaw();

        // --- LÓGICA DE MEMORIA ---
        // Buscamos al usuario en la BD, si no existe, creamos uno nuevo
        UserReputation user = reputationRepo.findById(userId)
                .orElse(new UserReputation(userId, userName, 0, 0, LocalDateTime.now()));

        // Actualizamos sus datos
        user.setMessagesSent(user.getMessagesSent() + 1);
        user.setLastInteraction(LocalDateTime.now());
        user.setUsername(userName); // Actualizamos nombre por si lo cambió

        // Mecánica simple: +1 rep por hablar
        user.setReputation(user.getReputation() + 1);

        // Guardamos en BD
        reputationRepo.save(user);

        // --- LÓGICA DE RESPUESTA ---

        // Comando para ver mis stats
        if (message.equalsIgnoreCase("!stats")) {
            String response = String.format("📊 **Stats de %s**\nReputación: %d\nMensajes: %d",
                    user.getUsername(), user.getReputation(), user.getMessagesSent());
            event.getChannel().sendMessage(response).queue();
        }

        // Tu lógica anterior de Ping...
        if (message.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }

        if (message.equalsIgnoreCase("!nose")) {
            event.getChannel().sendMessage("que quieres kbro!").queue();
        }

        if (message.contains("el darian es")) {
            event.getChannel().sendMessage("kbro.").queue();
        }
    }
}
