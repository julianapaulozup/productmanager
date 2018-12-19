package productManager.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import productManager.controller.UserControllerFeignClientBuilder;
import productManager.service.user.User;
import productManager.service.user.UserClient;
import productManager.service.user.UserResource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class UserFeignTest {
    private UserClient userClient;

    @Before
    public void setup() {
        UserControllerFeignClientBuilder feignClientBuilder = new UserControllerFeignClientBuilder();

        userClient = feignClientBuilder.getUserClient();
    }

    @Test
    public void givenUserClient_shouldRunSuccessfully() throws Exception {
        List<User> users = userClient.findAll().stream()
                .map(UserResource::getUser)
                .collect(Collectors.toList());

        assertTrue(users.size() > 2);
    }

    @Test
    public void givenUserClient_shouldFindOneUser() throws Exception {
        User user = userClient.findByLogin("takeo");
        Assertions.assertThat(user.getLogin()).isEqualTo("takeo");
        Assertions.assertThat(user.getId()).isEqualTo(23L);
    }

}
