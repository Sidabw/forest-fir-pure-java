package com.brew.home.jvm.jmx.l2;

import com.brew.home.jvm.jmx.l2.bean.MyUserImpl;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class DemoMain {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName mxbeanName = new ObjectName("com.demo.jmx.mbean:type=MyUserImpl, name=custom");
        MyUserImpl myUser = new MyUserImpl();
        mbs.registerMBean(myUser, mxbeanName);

        TimeUnit.SECONDS.sleep(1000);
    }

}
