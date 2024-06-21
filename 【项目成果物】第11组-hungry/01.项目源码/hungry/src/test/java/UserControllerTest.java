import com.fasterxml.jackson.databind.ObjectMapper;
import com.hust.hungry.HungryApplication;
import com.hust.hungry.controller.UserController;
import com.hust.hungry.entity.User;
import com.hust.hungry.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = HungryApplication.class)
class UserControllerTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testRegisterUser() {
        // 创建 RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();

        // 创建要发送的 User 对象
        User user = new User();
        user.setPassword("testpassword");
        user.setUserName("testuser");
        user.setPassword("testpassword");
        user.setUserSex(1);
        user.setUserImg("testimg.jpg");
        user.setDelTag(0);


    }
}
