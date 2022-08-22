package com.sastit.joinus.service;

import com.sastit.joinus.model.entity.Configuration;
import com.sastit.joinus.model.entity.User;
import com.sastit.joinus.repository.ConfigurationRepository;
import com.sastit.joinus.repository.UserRepository;
import com.sastit.joinus.utils.AuthUtils;
import org.springframework.cache.annotation.CacheEvict;
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

    @Cacheable(value = "api")
    public Boolean getApiClosed() {
        Configuration configuration = configurationRepository.findById(1).orElse(null);
        if (configuration == null) {
            return null;
        }
        return configuration.getApiClosed();
    }

    @CacheEvict(value = "api")
    public void switchStatus() {
        Configuration configuration = configurationRepository.findById(1).orElse(null);
        if (configuration != null) {
            configuration.setApiClosed(!configuration.getApiClosed());
            configurationRepository.save(configuration);
        }
    }

    @CacheEvict(value = "configuration", allEntries = true)
    public void install(String siteName, String shortName, String admin, String password) {
        Configuration configuration = new Configuration();
        configuration.setSiteName(siteName);
        configuration.setShortName(shortName);
        userRepository.save(new User(
                admin,
                AuthUtils.getEnPassword(admin, password),
                null, null, true, false,
                null, null, null
            )
        );
        configurationRepository.save(configuration);
    }
}
