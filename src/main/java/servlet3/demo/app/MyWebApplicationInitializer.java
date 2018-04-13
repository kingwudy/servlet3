package servlet3.demo.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import servlet3.demo.filter.MdcFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {
//    @Override
    public void onStartup0(ServletContext container) {
        // InitParameter
        container.setInitParameter("contextConfigLocation", "classpath:spring/applicationContext.xml");
        // listener
        container.addListener(ContextLoaderListener.class);
        // servlet
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        registration.setInitParameter("contextConfigLocation", "classpath:spring/dispatcher-servlet.xml");
        // filter
        MdcFilter mdcFilter = new MdcFilter();
        container.addFilter("mdcFilter", mdcFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,"/");
    }

    @Override
    public void onStartup(ServletContext container) {
        // listener
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ConfigSpring.class);
        container.addListener(new ContextLoaderListener(rootContext));
        // servlet
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(ConfigMvc.class);
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        // filter
        MdcFilter mdcFilter = new MdcFilter();
        container.addFilter("mdcFilter", mdcFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,"/*");
    }
}