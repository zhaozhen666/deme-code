package com.zhao.elasticjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.internal.schedule.JobScheduleController;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class JobMain {
    public static void main(String[] args) {
        //初始化注册中心
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration("119.45.52.68:2181", "data-job");
        CoordinatorRegistryCenter coordinatorRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        coordinatorRegistryCenter.init();
        //创建任务
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder("a-job", "*/2 * * * * ?", 3)
                .shardingItemParameters("0=张三,1=李四,2=王五").build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, BackupJob.class.getName());
        //执行任务
        new JobScheduler(coordinatorRegistryCenter, LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build()).init();
    }
}
