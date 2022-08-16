package bg.hoteltrip.web;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application.yml")
public class UserControllerIT {

    private static final String TEST_USER_EMAIL = "test@example.com";
    private static final String TEST_USER_FIRST_NAME = "Test";
    private static final String TEST_USER_LAST_NAME = "Testov";
    private static final String TEST_USER_PASSWORD = "testPass";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    private UserEntity testUser;

    @BeforeEach
    public void setUp() {
        testUser = testDataUtil.initUser();
    }

    @AfterEach
    public void tearDown() {
        testDataUtil.cleanUp();
    }

    @Test
    void testLogin_confirmLogin() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

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
    @WithAnonymousUser
    void testLoginError_RedirectToLogin() throws Exception {
        mockMvc.perform(post("/users/login-error")
                        .param("username", "dani")
                        .param("password", "1234")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void profilePageOpenShouldRedirectWhenNoUserIsLoggedIn() throws Exception {
        mockMvc.perform(get("/users/profile")).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "user@user.com", roles = "USER")
    void testUserGetProfile() throws Exception {
        mockMvc.perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("user-profile"));
    }


    @Test
    @WithMockUser(username = "user@user.com", roles = "USER")
    void testUserGetProfile_EditUser() throws Exception {
        mockMvc.perform(get("/users/profile/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-update"));
    }
}
