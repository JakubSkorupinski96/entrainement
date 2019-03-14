package spring;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import model.Company;
import model.Computer;

@Configuration
@ComponentScan(basePackages = {"controller", "services", "dao", "mapper"})
public class SpringConfig {
  
  private static Logger logger = LoggerFactory.getLogger(SpringConfig.class);
  
  private static final String PROPERTY_FILE_NAME = "C:\\Users\\jakub\\git\\repository\\Training\\db.properties";
  
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;
  
  static List<String> data = getConnectionURL(PROPERTY_FILE_NAME);
  private static String URL = data.get(0);;
  private static String USERNAME = data.get(1);
  private static String PASSWORD = data.get(2);
  
  static {
    config.setJdbcUrl(URL);
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    config.setDriverClassName("com.mysql.jdbc.Driver");
    config.addDataSourceProperty( "cachePrepStmts" , "true" );
    config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    ds = new HikariDataSource( config );
}


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
  
  @Bean
  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
  
  @Bean 
  public static HikariDataSource getDataSource() {
    return ds;
  }
  
//  @Autowired
//  private Environment environement;
//  
  
//  @Bean
//  public HikariDataSource dataSource() {
//    HikariDataSource dataSource = new HikariDataSource();
//    dataSource.setJdbcUrl(environement.getRequiredProperty(URL));
//    dataSource.setUsername(environement.getRequiredProperty(USERNAME));
//    dataSource.setPassword(environement.getRequiredProperty(PASSWORD));
//    dataSource.setDriverClassName(environement.getRequiredProperty("com.mysql.jdbc.Driver"));
//    return dataSource;
//}

  
}
