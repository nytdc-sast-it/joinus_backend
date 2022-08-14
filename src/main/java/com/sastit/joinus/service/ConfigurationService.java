package com.sastit.joinus.service;

import com.sastit.joinus.model.entity.Configuration;
import com.sastit.joinus.model.entity.User;
import com.sastit.joinus.repository.ConfigurationRepository;
import com.sastit.joinus.repository.UserRepository;
import com.sastit.joinus.utils.AuthUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final UserRepository userRepository;

    @Inject
    public ConfigurationService(ConfigurationRepository configurationRepository, UserRepository userRepository) {
        this.configurationRepository = configurationRepository;
        this.userRepository = userRepository;
    }

    @Cacheable(value = "configuration", key = "#root.methodName")
    public boolean isInstalled() {
        return configurationRepository.existsById(1);
    }

    @Cacheable(value = "configuration", key = "#root.methodName")
    public String getSiteName() {
        Configuration configuration = configurationRepository.findById(1).orElse(null);
        if (configuration == null) {
            return null;
        }
        return configuration.getSiteName();
    }

    @Cacheable(value = "configuration", key = "#root.methodName")
    public String getShortName() {
        Configuration configuration = configurationRepository.findById(1).orElse(null);
        if (configuration == null) {
            return null;
        }
        return configuration.getShortName();
    }

    @Cacheable(value = "api", key = "#root.methodName")
    public Boolean getApiClosed() {
        Configuration configuration = configurationRepository.findById(1).orElse(null);
        if (configuration == null) {
            return null;
        }
        return configuration.getApiClosed();
    }

    public void install(String siteName, String shortName, String admin, String password) {
        Configuration configuration = new Configuration();
        configuration.setSiteName(siteName);
        configuration.setShortName(shortName);
        userRepository.save(new User(
                admin,
                AuthUtils.getEnPassword(admin, password),
                null, null, true, null, null, null
            )
        );
        configurationRepository.save(configuration);
    }
}
