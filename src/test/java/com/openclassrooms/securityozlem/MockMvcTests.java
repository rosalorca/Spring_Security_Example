package com.openclassrooms.securityozlem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MockMvcTests {
    @Autowired
    private MockMvc mock;
    @Autowired
    private WebApplicationContext context;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        mock.perform(get("/login")). // perform ---> crée une requête GET en dehors de la méthode de test.
                andDo(print()) // andDo ---> pour exécuter une action générale
                .andExpect(status().isOk()); // ---> pour vérifier  un certain résultat
    }

    @Test
    public void userLoginTest() throws Exception {
        mock.perform(formLogin("/login").user("springuser").password("spring123")).andExpect(authenticated());
    }

    @Test
    public void userLoginFail() throws Exception {
        mock.perform(formLogin("/login")
                .user("springuser")
                .password("password"))
                .andExpect(unauthenticated());
    }

    @BeforeEach
    public void setup() {
        mock = MockMvcBuilders // ---> pour évaluer les codes de réponse sur mon app test MVC
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
}
