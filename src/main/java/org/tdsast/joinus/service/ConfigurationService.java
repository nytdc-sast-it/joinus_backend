package org.tdsast.joinus.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Configuration;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.repository.ConfigurationRepository;
import org.tdsast.joinus.repository.UserRepository;
import org.tdsast.joinus.utils.AuthUtils;

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
