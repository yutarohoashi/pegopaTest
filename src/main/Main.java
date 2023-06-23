package main;

import org.openqa.selenium.*;


import org.openqa.selenium.chrome.ChromeDriver;

import methodTest.InsertTest;
import methodTest.UpdateTest;
import methodTest.ListTest;
import methodTest.DetailTest;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// ドライバ指定
		System.setProperty("webdriver.chrome.driver", "./exe/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// 開きたいサイトのURLを取得
		driver.get("http://localhost:8080/pegopa/");

		// ログイン
		Thread.sleep(500);
		Login(driver);

		//driver.quit();
	}

	public static void Login(WebDriver driver) {

		// ユーザー欄とパスワード欄にkandaitと入力
		WebElement user = driver.findElement(By.name("name"));
		user.sendKeys("email");

		WebElement password = driver.findElement(By.name("pw"));
		password.sendKeys("pw");

		// ログインボタンを押す
		driver.findElement(By.cssSelector("input[value='ログイン']")).click();
	}

}
