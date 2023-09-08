package com.BookStoreV1.BookStore.User.builder;

import com.BookStoreV1.BookStore.dto.UserDTO;
import lombok.Builder;

@Builder
public class UserDTOBuilder {

    @Builder.Default
    private long id = 1L;

    @Builder.Default
    private final String nome = "Igor Dantas";

    @Builder.Default
    private final String endereco = "Casa";

    @Builder.Default
    private final String cidade = "Fortaleza";

    @Builder.Default
    private final String email = "Fort@gmail.com";

    public UserDTO builderUserDTO(){
        return new UserDTO(id, nome, endereco, cidade, email);
    }
}
