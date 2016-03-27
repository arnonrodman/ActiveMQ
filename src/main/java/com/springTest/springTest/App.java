package com.springTest.springTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "test spring freamwork" );

    	ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");
    	Restaruant restaruant1 = (Restaruant)context.getBean("restaruantBean");
    	restaruant1.greetCustomer();
    	
    	((AbstractApplicationContext)context).registerShutdownHook();
    	
    	
    	
    }
}
