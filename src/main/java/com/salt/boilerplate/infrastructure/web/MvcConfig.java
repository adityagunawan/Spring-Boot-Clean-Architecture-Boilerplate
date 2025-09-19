package com.salt.boilerplate.infrastructure.web;

import com.salt.boilerplate.domain.admin.gateway.AdminGateway;
import com.salt.boilerplate.infrastructure.db.repository.UserRepository;
import com.salt.boilerplate.infrastructure.gateway_adapter.AdminPersistentGateway;
import com.salt.boilerplate.usecase.admin.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.ResourceBundle;

@Configuration
public class MvcConfig {
    @Bean
    public ResourceBundle messageBundle() {
        return ResourceBundle.getBundle("ValidationMessages");
    }

    @Bean
    public LocaleResolver sessionLocaleResolver() {
        return new AcceptHeaderResolver();
    }

    @Bean
    public CreateAdminUseCase createAdminUseCase(UserRepository userRepository) {
        AdminGateway adminGateway = new AdminPersistentGateway(userRepository);
        return new CreateAdminUseCase(adminGateway);
    }

    @Bean
    public GetAdminUseCase getAdminUseCase(UserRepository userRepository) {
        AdminGateway adminGateway = new AdminPersistentGateway(userRepository);
        return new GetAdminUseCase(adminGateway);
    }

    @Bean
    public SearchAdminUseCase searchAdminUseCase(UserRepository userRepository) {
        AdminGateway adminGateway = new AdminPersistentGateway(userRepository);
        return new SearchAdminUseCase(adminGateway);
    }

    @Bean
    public UpdateAdminUseCase updateAdminUseCase(UserRepository userRepository) {
        AdminGateway adminGateway = new AdminPersistentGateway(userRepository);
        return new UpdateAdminUseCase(adminGateway);
    }

    @Bean
    public DeleteAdminUseCase deleteAdminUseCase(UserRepository userRepository) {
        AdminGateway adminGateway = new AdminPersistentGateway(userRepository);
        return new DeleteAdminUseCase(adminGateway);
    }
}
