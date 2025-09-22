package com.salt.boilerplate.api.features.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salt.boilerplate.api.features.admin.dto.AdminRegistrationRequest;
import com.salt.boilerplate.domain.admin.model.Admin;
import com.salt.boilerplate.domain.admin.value_object.Email;
import com.salt.boilerplate.domain.admin.value_object.PasswordHash;
import com.salt.boilerplate.domain.admin.value_object.PersonName;
import com.salt.boilerplate.domain.admin.value_object.Username;
import com.salt.boilerplate.usecase.admin.CreateAdminUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreateAdminController.class)
class CreateAdminControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateAdminUseCase createAdminUseCase;

    @Test
    void createAdmin_returns200_andResponseBody() throws Exception {
        Admin persisted = new Admin(
                1L,
                new Username("Alice"),
                new Email("alice@example.com"),
                new PersonName("Alice"),
                new PasswordHash("$2b$hash")
        );
        given(createAdminUseCase.execute(any())).willReturn(persisted);

        AdminRegistrationRequest req = new AdminRegistrationRequest(
                "Alice",
                "$2b$hash",
                "Alice",
                "alice@example.com"
        );

        mockMvc.perform(post("/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(true)))
                .andExpect(jsonPath("$.message", is("Success")))
                .andExpect(jsonPath("$.data.id", is("1")))
                .andExpect(jsonPath("$.data.username", is("Alice")))
                .andExpect(jsonPath("$.data.name", is("Alice")))
                .andExpect(jsonPath("$.data.email", is("alice@example.com")));
    }

    @Test
    void createAdmin_validationError_returns400() throws Exception {
        // missing required fields triggers MethodArgumentNotValidException handled by GlobalExceptionHandler
        String invalidJson = "{\n  \"username\": \"\", \"password\": \"\", \"name\": \"\", \"email\": \"not-an-email\"\n}";

        mockMvc.perform(post("/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}

