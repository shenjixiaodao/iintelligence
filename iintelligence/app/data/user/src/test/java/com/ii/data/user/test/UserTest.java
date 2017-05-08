package com.ii.data.user.test;




import com.ii.data.user.Entity.UserDeviceEntity;
import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.data.user.query.UserDeviceQueryManagement;
import com.ii.domain.user.criteria.UserDeviceCriteria;
import com.ii.domain.base.Device;
import com.ii.domain.user.User;
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
        criteria.setDeviceBindingStatus(Device.BindingStatus.Created.toString());
        List<UserDeviceEntity> list = queryManagement.queryUserDevice(criteria);
        System.out.println(list);
    }

    @Test
    public void findUser(){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid("123456");
        criteria.setDeviceBindingStatus(Device.BindingStatus.Binding.toString());
        User user = userDeviceMapper.findUser(criteria);
        System.out.println(user);
    }

    @Test
    public void findDevices(){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid("123456");
        criteria.setDeviceBindingStatus(Device.BindingStatus.Binding.toString());
        List<Device> devices= userDeviceMapper.findDevices(criteria);
        System.out.println(devices);
    }

}
