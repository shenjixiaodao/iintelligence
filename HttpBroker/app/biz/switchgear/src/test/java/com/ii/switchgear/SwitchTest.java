package com.ii.switchgear;




import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class SwitchTest {
// 带数据源单元测试
//public class SwitchTest extends AbstractTransactionalJUnit4SpringContextTests {



}