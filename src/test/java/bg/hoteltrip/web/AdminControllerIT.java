package bg.hoteltrip.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(value = "admin@admin.com", roles = "ADMIN")
    public void testAdminPageShown() throws Exception {
        mockMvc.perform(get("/admin")).
                andExpect(status().isOk()).
                andExpect(view()
                        .name("admin"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminShouldFindAllUsers() throws Exception {
        mockMvc.perform(get("/admin/all-users"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-users"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminShouldOpenAddHotelPage() throws Exception {
        mockMvc.perform(get("/admin/add-hotel"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-hotel"));
    }

    @Test
    @WithMockUser
    void testUserShouldNotHaveAccessToAllUserPage() throws Exception {
        mockMvc.perform(get("/admin/all-users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void testUserShouldNotHaveAccessToAddHotelPage() throws Exception {
        mockMvc.perform(get("/admin/add-hotel"))
                .andExpect(status().isForbidden());
    }
}
