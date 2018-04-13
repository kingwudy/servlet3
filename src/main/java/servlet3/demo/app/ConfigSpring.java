package servlet3.demo.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(basePackages = "servlet3.demo", excludeFilters = {@ComponentScan.Filter(value = {Controller.class, RestController.class})})
@PropertySource("classpath:config.properties")
public class ConfigSpring {
      
    @Value("${jdbc.driverClassName}")
    private String driverClassName;  
      
    @Value("${jdbc.url}")  
    private String url;  
  
    @Value("${jdbc.username}")  
    private String username;  
  
    @Value("${jdbc.password}")  
    private String password;  
  
//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
      
    /** 
     * 必须加上static 
     */  
    @Bean  
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
        return configurer;  
    }  
}  