package com.ii.data.user.test;




import com.ii.data.user.query.Entity.UserDeviceEntity;
import com.ii.data.user.query.UserDeviceQueryManagement;
import com.ii.data.user.query.criteria.UserDeviceCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class UserTest {

    @Autowired
    private UserDeviceQueryManagement queryManagement;

    @Test
    public void queryUserDevice(){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid("123456");
        List<UserDeviceEntity> list = queryManagement.queryUserDevice(criteria);
        System.out.println(list);
    }

}
