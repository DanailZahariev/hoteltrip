package bg.hoteltrip.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerMockBeanIT {

    private static final String TEST_USER_EMAIL = "test@example.com";
    private static final String TEST_USER_FIRST_NAME = "Test";
    private static final String TEST_USER_LAST_NAME = "Testov";
    private static final String TEST_USER_PASSWORD = "testPass";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    public void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", TEST_USER_EMAIL).
                        param("firstName", TEST_USER_FIRST_NAME).
                        param("lastName", TEST_USER_LAST_NAME).
                        param("password", TEST_USER_PASSWORD).
                        param("confirmPassword", TEST_USER_PASSWORD).
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }

    @Test
    public void profilePageOpenShouldRedirectWhenNoUserIsLoggedIn() throws Exception {
        mockMvc.perform(get("/users/profile")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void profilePageShouldOpenWhenAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/users/profile")).
                andExpect(status().isOk()).
                andExpect(view().name("user-profile"));
    }
}
