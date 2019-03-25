package spring;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "controller", "services", "mapper", "dao" })
public class SpringConfig implements WebMvcConfigurer, WebApplicationInitializer {

  private static Logger logger = LoggerFactory.getLogger(SpringConfig.class);


  /**
   * . Hakari DataSource getter
   *
   * @return HakariDataSource
   */

  @Bean
  public HikariDataSource getDataSource() {
    // HikariConfig config = new HikariConfig("C:\\Users\\jakub\\git\\repository\\Training\\src.main.resources\\datasource.properties");
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(
        "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
    config.setUsername("root");
    config.setPassword("");
    config.setDriverClassName("com.mysql.jdbc.Driver");
    return new HikariDataSource(config);
  }

  /**
   * .
   *
   * @param propertyFile : Properties
   * @return List<String>
   */

  public static List<String> getConnectionURL(String propertyFile) {

    Properties prop = new Properties();
    List<String> data = new ArrayList<>();

    try (InputStream input = new FileInputStream(propertyFile);) {
      prop.load(input);
      data.add(prop.getProperty("dbURL"));
      data.add(prop.getProperty("dbUser"));
      data.add(prop.getProperty("dbPassword"));

    } catch (IOException e) {
      e.printStackTrace();
      logger.error("Properties file error");
    }
    return data;
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(SpringConfig.class);
    servletContext.addListener(new ContextLoaderListener(context));

    ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
        new DispatcherServlet(context));
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
  }

  /**
   * . view resolver bean
   * @return ViewResolver
   */
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/");
    bean.setSuffix(".jsp");
    return bean;
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
  }

  /**
   * . Hibernate session setup
   *
   * @return ListFactoryBean
   */

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(getDataSource());
      sessionFactory.setPackagesToScan("model");
      sessionFactory.setHibernateProperties(hibernateProperties());
      return sessionFactory;
  }

  /**
   * . Hibernate setup
   *
   * @return PlatformTransactionManager
   */

  @Bean
  public PlatformTransactionManager hibernateTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(sessionFactory().getObject());
      return transactionManager;
  }

  /**
   * . Hibernate setup
   *
   * @return Properties
   */

  private Properties hibernateProperties() {
      Properties hibernateProperties = new Properties();
      //hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
      hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

      return hibernateProperties;
  }
  

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("WEB-INF/i18n/msg");
    messageSource.setDefaultEncoding("ISO-8859-1");
    return messageSource;
}
  

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(new Locale("fr"));
    resolver.setCookieName("localCookie");
    resolver.setCookieMaxAge(4800);
    return resolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("lang");
    registry.addInterceptor(interceptor);
}
}
