package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;
import java.util.Scanner;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        //setting the driver executable
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input a number: ");
        int repeat = Integer.parseInt(scan.nextLine());
        //Initiating your chromedriver
        WebDriver driver = new ChromeDriver();

        //Applied wait time
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //maximize window
        driver.manage().window().maximize();

        //open browser with desried URL
        driver.get("http://localhost:8080/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement newGame = driver.findElement(By.id("newGame"));
        newGame.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement guessBox;
        for (int i = 0; i < repeat; i++) {
            guessBox = driver.findElement(By.name("input"));
            do {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                String toInput = String.valueOf((char) (1 + rand.nextInt(126)));
                //guess box
                guessBox.sendKeys(toInput);
                guessBox.sendKeys(Keys.ENTER);
                try {
                    guessBox = driver.findElement(By.name("input"));
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    break;
                }
            } while (guessBox.isDisplayed());
            newGame = driver.findElement(By.id("newGame"));
            newGame.click();
        }
        //closing the browser
        driver.quit();
    }
}
