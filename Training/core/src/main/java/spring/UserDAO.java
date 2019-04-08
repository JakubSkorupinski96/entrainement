package spring;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.zaxxer.hikari.HikariDataSource;

import model.QUser;
import model.User;

@Repository
public class UserDAO {

  private HikariDataSource dataSource;
  private SessionFactory sessionFactory;
  
  @Autowired
  public UserDAO(HikariDataSource dataSource, SessionFactory sessionFactory) {
    this.dataSource = dataSource;
    this.sessionFactory = sessionFactory;
  }
  
  public User showUser(String username) {
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QUser qUser = QUser.user;
    User user = queryF.selectFrom(qUser).where(qUser.username.eq(username)).fetchOne();
    System.out.println(user.getPassword());
    session.close();
    return user;
  }

}
