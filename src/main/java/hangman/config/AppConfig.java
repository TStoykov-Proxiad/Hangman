package hangman.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "hangman")
public class AppConfig {

  @Bean
  public Random getRandom() {
    return new Random();
  }

  //  @Bean
  //  @Primary
  //  public Dictionary getDictionary() {
  //    return new Dictionary(getRandom());
  //  }
  //
  //  @Bean
  //  @Primary
  //  @Scope("prototype")
  //  public String getWord() {
  //    return getDictionary().getWord();
  //  }
  //
  //  @Bean
  //  @Primary
  //  @Scope("prototype")
  //  public Game getGame() {
  //    return new HangMan(getWord());
  //  }
}
