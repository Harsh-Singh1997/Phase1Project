package Project;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Phase1Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS );
        
        //To find searchbar
        WebElement Search = driver.findElement(By.id("twotabsearchtextbox"));
        Search.sendKeys("Samsung");
        
        //To click on search button
        WebElement SearchBtn = driver.findElement(By.id("nav-search-submit-button"));
        SearchBtn.click();
        
        List<WebElement> Product = driver.findElements(By.xpath("//div[@data-component-type = 's-search-result']//h2"));
        System.out.println("Size of Product List " + Product.size());
        
        List<WebElement> Price = driver.findElements(By.xpath("//div[@data-component-type = 's-search-result']//span[@class='a-price']"));
        System.out.println("Size of Price List " + Price.size());
        
        
        //List of all Products
        for(WebElement Prod: Product) {
        	
        	System.out.println(Prod.getText());
        		
        }
        
        
        //List of all Prices
        for(WebElement Pr: Price) {
        	
        	System.out.println(Pr.getText());
        		
        }
        
        List<WebElement> LinkBtn = driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));
        
        String ParentWin = driver.getWindowHandle(); 
        String ExpectedValue = LinkBtn.get(0).getText();
        
        //Click on first link
        LinkBtn.get(0).click();
        
        Set<String> AllWins = driver.getWindowHandles();
        for (String win : AllWins) {
			
			if(!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
        
        
        //Match the title of product
        WebElement childHeader = driver.findElement(By.id("productTitle"));
        
        String Str = childHeader.getText();
        
        if(Str.equals(ExpectedValue)) {
        	System.out.println("Title is Matching.");
        }else {
        	System.out.println("Title is not matching");
        }
        
              
	}

}
