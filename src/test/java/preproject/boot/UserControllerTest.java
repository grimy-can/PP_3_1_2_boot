package preproject.boot;

import static org.hamcrest.Matchers.containsString;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import preproject.boot.controller.UserController;
import preproject.boot.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final String TASK_TITLE = "Практическая задача 3.1.2";
    private final String USERS_PAGE_TITLE = "База данных пользователей";
    private final String NEW_USER_PAGE_TITLE = "Новый пользователь";
    private final String EDIT_USER_PAGE_TITLE = "Изменить пользователя";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;
    @Test
    public void testIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(
                        containsString(TASK_TITLE)));
    }

    @Test
    public void testUsersPage() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(content().string(
                        containsString(USERS_PAGE_TITLE)));
    }
    @Test
    public void testNewUserPage() throws Exception {
        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("new"))
                .andExpect(content().string(
                        containsString(NEW_USER_PAGE_TITLE)));
    }


}
