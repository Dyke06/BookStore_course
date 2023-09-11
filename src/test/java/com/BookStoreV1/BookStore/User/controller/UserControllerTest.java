package com.BookStoreV1.BookStore.User.controller;

import com.BookStoreV1.BookStore.Controller.UserController;
import com.BookStoreV1.BookStore.Service.UserService;
import com.BookStoreV1.BookStore.User.builder.UserDTOBuilder;
import com.BookStoreV1.BookStore.dto.UserDTO;
import com.BookStoreV1.BookStore.utils.JsonConversionUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.BookStoreV1.BookStore.utils.JsonConversionUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private UserDTOBuilder userDTOBuilder;

    @BeforeEach
    void setUp() {
        userDTOBuilder = UserDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPostIsCalledThenStatusCreatedShouldBeReturned() throws Exception {
        UserDTO expectedCreatedUserDTO = userDTOBuilder.builderUserDTO();

        when(userService.create(expectedCreatedUserDTO))
                .thenReturn(expectedCreatedUserDTO);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedCreatedUserDTO)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id", Is.is((int) expectedCreatedUserDTO.getId())))
                        .andExpect(jsonPath("$.nome", Is.is(expectedCreatedUserDTO.getNome())))
                        .andExpect(jsonPath("$.endereco", Is.is(expectedCreatedUserDTO.getEndereco())))
                        .andExpect(jsonPath("$.cidade", Is.is(expectedCreatedUserDTO.getCidade())))
                        .andExpect(jsonPath("$.email", Is.is(expectedCreatedUserDTO.getEmail())));
    }

    @Test
    void whenPostIsCalledWhithoutRequiredfiledThenBadRequetsStatusShouldBeInfromed() throws Exception {
        UserDTO expectedCreatedUserDTO = userDTOBuilder.builderUserDTO();
        expectedCreatedUserDTO.setNome(null);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(expectedCreatedUserDTO)))
                        .andExpect(status().isBadRequest());

    }
}
