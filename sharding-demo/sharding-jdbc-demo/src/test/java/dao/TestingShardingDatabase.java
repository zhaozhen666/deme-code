package dao;

import com.zhao.RunBoot;
import com.zhao.entity.Border;
import com.zhao.entity.City;
import com.zhao.entity.Position;
import com.zhao.entity.PositionDetail;
import com.zhao.repository.BorderRepository;
import com.zhao.repository.CityRepository;
import com.zhao.repository.PositionDetailRepository;
import com.zhao.repository.PositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestingShardingDatabase {
    @Resource
    PositionRepository positionRepository;
    @Resource
    PositionDetailRepository positionDetailRepository;
    @Resource
    CityRepository cityRepository;
    @Resource
    BorderRepository borderRepository;

    /**
     * 测试是否分库
     */
    @Test
    public void testAdd(){
        for (long i=1;i<=20;i++){
            Position position = new Position();
            //position.setId(i);
            position.setCity("shanghai");
            position.setName("zhao");
            position.setSalary("100.86");
            positionRepository.save(position);
        }
    }

    /**
     * 分库时主表和字表配置的规则一样，所以这里测试的是主表和字表在同库对应
     */
    @Test
    public void testAddDetail(){
        for (long i=1;i<=20;i++){
            Position position = new Position();
            //position.setId(i);
            position.setCity("shanghai");
            position.setName("zhao");
            position.setSalary("100.86");
            positionRepository.save(position);
            PositionDetail detail = new PositionDetail();
            detail.setPid(position.getId());
            detail.setDescription("This is message "+i);
            positionDetailRepository.save(detail);
        }
    }

    /**
     * 测试加载数据分库时走的那个库
     */
    @Test
    public void testLoadData(){
        Object object = positionRepository.findPositionLoadById(607972934107004929L);
        Object[] objects = (Object[]) object;
        System.out.println(objects[0]+" "+objects[1]);
    }

    /**
     * 广播表会写入多个库
     */
    @Test
    public void testBroadCast(){
        City city = new City();
        city.setName("shanghai");
        city.setProvince("shanghai");
        cityRepository.save(city);
    }

    /**
     * 测试分表时如何插入
     */
    @Test
    @Repeat(100)
    public void testShardingBorder(){
        Random random = new Random();
        int companyId = random.nextInt(10);
        Border border = new Border();
        border.setIsDel(false);
        border.setCompanyId(companyId);
        border.setCreateTime(new Date());
        border.setName("zhao");
        border.setWorkYear("2");
        borderRepository.save(border);


    }
}
