package com.cooperawiki.Wiki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.models.DiscordAccount;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.DiscordAccountRepository;
import com.cooperawiki.Wiki.domain.repositories.UserRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.UserInputDto;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private DiscordAccountRepository discordAccountRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    public User createUser(UserInputDto dto) {
        User newUser = new User(dto, null);
        
        createDiscordAccount(dto.discordUsername(), dto.discordId());

        return userRepository.save(newUser);
    }

    public DiscordAccount createDiscordAccount(String username, String discordId) {
        DiscordAccount discordAccount = new DiscordAccount(username, discordId);

        return discordAccountRepository.save(discordAccount);
    }
}
