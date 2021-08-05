package dao;

import com.zhao.RunBoot;
import com.zhao.entity.Position;
import com.zhao.entity.PositionDetail;
import com.zhao.repository.PositionDetailRepository;
import com.zhao.repository.PositionRepository;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestShardingTransaction {

    @Resource
    PositionRepository positionRepository;
    @Resource
    PositionDetailRepository positionDetailRepository;

    @Test
    @Transactional
    //@ShardingTransactionType(TransactionType.XA)
    public void testTransaction() {
        TransactionTypeHolder.set(TransactionType.BASE);
        for (long i = 1; i <= 3; i++) {
            Position position = new Position();
            //position.setId(i);
            position.setCity("shanghai");
            position.setName("zhao");
            position.setSalary("100.86");
            positionRepository.save(position);
            if (i == 3) {
                //long j =i/0;
                throw new RuntimeException("人为制造异常");
            }
            PositionDetail detail = new PositionDetail();
            detail.setPid(position.getId());
            detail.setDescription("This is root  " + i);
            positionDetailRepository.save(detail);
        }
    }
}
