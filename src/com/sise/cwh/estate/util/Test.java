package com.sise.cwh.estate.util;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {
	String[] paths = new String[] {"/com/sise/cwh/estate/config/spring/spring-beans.xml","/com/sise/cwh/estate/config/spring/spring-common.xml"};
	ApplicationContext context00 = new ClassPathXmlApplicationContext(paths);
	@org.junit.Test
	public void test() {
		QueryFactory queryFactory = (QueryFactory) context00.getBean("queryFactory");
		queryFactory.getAutoIncrement("hossellcrcs");
	}

	@org.junit.Test
	public void test1(){
		System.out.println(1+2/2);
	}
}
