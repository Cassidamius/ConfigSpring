java-base config：基于java的配置的核心是基于@Configuration注解的类和基于@Bean注解的方法


1、定义bean
<beans>
<bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>

可以用如下代码代替：

@Configuration
public class AppConfig {
@Bean
public MyService myService() {
return new MyServiceImpl();
}
}

2、扫描包，查找所有具有@Component注解的类
<beans>
<context:component-scan base-package="com.acme"/>
</beans>
可以用如下代码代替：
public static void main(String[] args) {
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.acme");
ctx.refresh();
MyService myService = ctx.getBean(MyService.class);
}