package com.zp.test;

import com.zp.DeliverApplication8091;
import com.zp.feignclient.ResumeFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeliverApplication8091.class)
public class FeignTest {
    @Autowired
    ResumeFeignClient feignClient;

    @Test
    public void feignTest() {
        Integer port = feignClient.findDefaultResumeState(2195321L);
        System.out.println("测试的结果" + port);
    }

}
