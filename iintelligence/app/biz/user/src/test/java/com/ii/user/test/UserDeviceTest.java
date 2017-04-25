package com.ii.user.test;



import com.ii.biz.user.service.IUserDeviceService;
import com.ii.domain.base.DeviceType;
import com.ii.domain.user.UserDevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class UserDeviceTest {

    @Autowired
    private IUserDeviceService userDevice;

    @Test
    public void createUserDevice(){
        List<UserDevice> list = userDevice.createUserDevice("123456", DeviceType.Switch);
        System.out.println(list);
    }

}
