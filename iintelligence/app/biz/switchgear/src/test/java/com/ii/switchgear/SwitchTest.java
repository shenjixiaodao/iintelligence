package com.ii.switchgear;



import com.ect.common.error.Result;
import com.ii.biz.switchgear.AyncContinuation.SwitchAyncContinuationService;
import com.ii.biz.switchgear.common.SwitchHandlerHolder;
import com.ii.biz.switchgear.service.ISwitchService;
import com.ii.domain.base.DeviceId;
import com.ii.domain.switchgear.handler.SwitchHandler;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class SwitchTest {
// 带数据源单元测试
//public class SwitchTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SwitchAyncContinuationService switchContinuationService;

    @Autowired
    private ISwitchService switchService;

    @Test
    public void registerStatusChangedHandler(){
       switchContinuationService.registerStatusEventHandler(new SwitchHandler() {
           @Override
           public Switch getSwitch() {
               return new Switch(new DeviceId("111111"),
                       new SwitchStatus(SwitchStatus.Status.On, System.currentTimeMillis()));
           }

           @Override
           public void resultReadyEvent(Result result) {
                System.out.println(result.toString());
           }
       });
    }

    @Test
    public void registerSwitch(){
        DeviceId deviceId = new DeviceId("Switch20170425142755100156");
        SwitchStatus status = new SwitchStatus(SwitchStatus.Status.On, System.currentTimeMillis());
        Switch s = new Switch(deviceId, status);
        switchService.registerSwitch(s);
    }

    @Test
    public void initHelper(){
        SwitchHandlerHolder holder = SwitchHandlerHolder.getHolder();
        System.out.println(holder);
    }

}
