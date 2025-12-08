package com.egregore.bot.configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.egregore.bot.listeners.ChaosListener; // Importa tu listener

@Configuration
public class BotConfiguration {

    @Value("${discord.token}")
    private String token;

    // Spring inyecta tu ChaosListener aquí automáticamente
    @Bean
    public JDA jda(ChaosListener chaosListener) {
        return JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.watching("tus pecados"))
                .addEventListeners(chaosListener) // <--- AQUÍ REGISTRAMOS EL LISTENER
                .build();
    }
}