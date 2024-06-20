import com.hust.hungry.HungryApplication;
import com.hust.hungry.entity.User;
import com.hust.hungry.mapper.UserMapper;
import com.hust.hungry.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = HungryApplication.class)
class UserControllerTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RestTemplate restTemplate;
    @MockBean
    private UserService userService;
     @Test
    public void testGetUserById() {
        User user = new User("44444444444","john","123456",1,"elwsifbwvbdivu",1);
        when(userService.getById(1L)).thenReturn(user);
        String url = "http://localhost:8080/api/users/1"; // 确保端口号和上下文路径是正确的
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(url, User.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
//        assertThat(responseEntity.getBody().getUsername()).isEqualTo("张三");
    }
}