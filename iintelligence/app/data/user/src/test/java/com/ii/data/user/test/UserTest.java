package com.ii.data.user.test;




import com.ii.data.user.Entity.UserDeviceEntity;
import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.data.user.query.UserDeviceQueryManagement;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.domain.user.UserDevice;
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

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Test
    public void queryUserDevice(){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid("123456");
        criteria.setDeviceStatus(UserDevice.DeviceStatus.Binding.toString());
        List<UserDeviceEntity> list = queryManagement.queryUserDevice(criteria);
        System.out.println(list);
    }

    @Test
    public void find(){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid("123456");
        criteria.setDeviceStatus(UserDevice.DeviceStatus.Binding.toString());
        List<UserDevice> list = userDeviceMapper.find(criteria);
        System.out.println(list);
    }

}
