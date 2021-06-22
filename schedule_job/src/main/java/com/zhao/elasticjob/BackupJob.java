package com.zhao.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.util.List;
import java.util.Map;

public class BackupJob implements SimpleJob {
    public void execute(ShardingContext shardingContext) {
        int shardingitem = shardingContext.getShardingItem();
        System.out.println("当前分片"+shardingitem);
        String shardingParamter = shardingContext.getShardingParameter();
        System.out.println(shardingParamter);
            String selectSql = "select * from resume where state='未归档' and name='"+shardingParamter+"' limit 1";
        List<Map<String, Object>> list =
                JdbcUtil.executeQuery(selectSql);
        if(list == null || list.size() == 0) {
            return;
        }
        Map<String, Object> stringObjectMap = list.get(0);
        long id = (long) stringObjectMap.get("id");
        String name = (String) stringObjectMap.get("name");
        String education = (String)
                stringObjectMap.get("education");
// 打印出这条记录
        System.out.println("======>>>id：" + id + " name：" +
                name + " education：" + education);
// 更改状态
        String updateSql = "update resume set state='已归档' where id=?";
        JdbcUtil.executeUpdate(updateSql,id);
// 归档这条记录
        String insertSql = "insert into resume_bak select * from resume where id=?";
        JdbcUtil.executeUpdate(insertSql,id);
    }

}
