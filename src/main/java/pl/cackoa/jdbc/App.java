package pl.cackoa.jdbc;

import lombok.extern.log4j.Log4j;
import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.cackoa.jdbc.dao.CityDao;

@Log4j
@ComponentScan
public class App {

  public static void main(String[] args) {
    val ctx = new AnnotationConfigApplicationContext(App.class);
  }
}
