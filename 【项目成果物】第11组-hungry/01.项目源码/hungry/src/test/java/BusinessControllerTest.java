import com.hust.hungry.HungryApplication;
import com.hust.hungry.entity.Business;
import com.hust.hungry.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HungryApplication.class)
public class BusinessControllerTest {
    @Autowired
    public BusinessService businessService;
    @Test
    public  void getbusinesslist() {
        List<Business> businessList = businessService.getBusinessListByKey("黄");
        System.out.println(businessList);
    }
}
