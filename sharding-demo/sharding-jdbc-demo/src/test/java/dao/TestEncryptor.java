package dao;

import com.zhao.RunBoot;
import com.zhao.entity.CUser;
import com.zhao.repository.CUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestEncryptor {
    @Resource
    CUserRepository cUserRepository;

    @Test
    public void testEncryptor() {
        CUser cUser = new CUser();
        cUser.setName("zhen");
        cUser.setPwd("123456");
        cUserRepository.save(cUser);
    }

    @Test
    public void testEncryptorLoad() {

        List<CUser> list = cUserRepository.findByPwd("123456");
        list.forEach(c -> {
            System.out.println(c.getId() + " " + c.getName() + " " + c.getPwd());
        });
    }
}
