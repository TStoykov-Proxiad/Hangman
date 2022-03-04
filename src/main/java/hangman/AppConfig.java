package hangman;

import java.util.Random;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "hangman")
public class AppConfig {

  @Bean
  public Random getRandom() {
    return new Random();
  }

  @Bean
  @Primary
  public Dictionary getDictionary() {
    return new Dictionary(getRandom());
  }

  @Bean
  @Primary
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public Game getGame() {
    return new HangMan(getDictionary());
  }
}
