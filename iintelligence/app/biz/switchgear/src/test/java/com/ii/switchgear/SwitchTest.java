package com.ii.switchgear;



import com.ect.common.error.Result;
import com.ii.biz.AyncContinuation.SwitchAyncContinuationService;
import com.ii.domain.base.DeviceId;
import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class SwitchTest {
// 带数据源单元测试
//public class SwitchTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SwitchAyncContinuationService switchContinuationService;

    @Test
    public void test(){
       switchContinuationService.registerStateChangedHandler(new SwitchHandler() {
           @Override
           public Switch getSwitch() {
               return new Switch(new DeviceId("111111"),
                       new SwitchState(SwitchState.State.ON, System.currentTimeMillis()));
           }

           @Override
           public void resultReadyEvent(Result result) {
                System.out.println(result.toString());
           }
       });
    }

}
