package hangman;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SpringContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    sce.getServletContext().setAttribute("context", ctx);
    ServletContextListener.super.contextInitialized(sce);
  }
}
