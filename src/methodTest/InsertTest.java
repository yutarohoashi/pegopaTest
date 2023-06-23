package methodTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;

import dao.BookDAO;
import bean.Book;

public class InsertTest {

	private WebDriver driver;

	public InsertTest(WebDriver driver) {
		this.driver = driver;
	}

	//登録機能をテストするメソッドを呼び出す
	public void Insert() throws InterruptedException {
		InsertTest inserttest = new InsertTest(driver);

		// 正常に新しい書籍を登録できるか
		inserttest.Insert201();
		Thread.sleep(500);
		// 既に登録されたISBNで登録した場合エラー画面に遷移するか
		inserttest.InsertExistingIsbn();
		Thread.sleep(500);
		// ISBNが未入力の場合エラー画面に遷移するか
		inserttest.InsertEmptyIsbn();
		Thread.sleep(500);
		// タイトルが未入力の場合エラー画面に遷移するか
		inserttest.InsertEmptyTitle();
		Thread.sleep(500);
		// 価格が未入力の場合エラー画面に遷移するか
		inserttest.InsertEmptyPrice();
		Thread.sleep(500);
	}


	// 正常処理
	// 正常に書籍を新規登録できるか
	public void Insert201() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍登録】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}


		// まだ登録されていないISBNを探す
		Book book;
		BookDAO bookdao = new BookDAO();
		String isbn = "";
		for (int i = 0; i < 10000; i++) {
			isbn = String.format("%5s", i).replace(" ", "0");
			book = bookdao.selectByIsbn(isbn);
			if (book.getIsbn() == null) {
				break;
			}
		}

		// ISBN入力
		WebElement InputIsbn = driver.findElement(By.name("isbn"));
		InputIsbn.sendKeys(isbn);

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("メニュー")).click();
	}

	// 異常処理
	// 既存のISBNで登録したときエラーがでるか
	public void InsertExistingIsbn() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍登録】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		BookDAO bookdao = new BookDAO();
		ArrayList<Book> book_list = bookdao.selectAll();

		// 既に登録されているISBNを入力
		String isbn = book_list.get(0).getIsbn();
		WebElement inputIsbn = driver.findElement(By.name("isbn"));
		inputIsbn.sendKeys(isbn);

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();
	}

	// ISBNを未入力で登録した場合エラーが出るか
	public void InsertEmptyIsbn() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("メニュー")).click();
		}

		// ISBNを未入力にする
		WebElement inputIsbn = driver.findElement(By.name("isbn"));
		inputIsbn.sendKeys("");

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();
	}

	// タイトルを未入力で登録した場合エラーが出るか
	public void InsertEmptyTitle() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("メニュー")).click();
		}

		// まだ登録されていないISBNを探す
		Book book;
		BookDAO bookdao = new BookDAO();
		String isbn = "";
		for (int i = 0; i < 10000; i++) {
			isbn = String.format("%5s", i).replace(" ", "0");
			book = bookdao.selectByIsbn(isbn);
			if (book.getIsbn() == null) {
				break;
			}
		}

		// ISBN入力
		WebElement InputIsbn = driver.findElement(By.name("isbn"));
		InputIsbn.sendKeys(isbn);

		// タイトルを未入力にする
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("1000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();
	}

	// 価格を未入力で登録した場合エラーが出るか
	public void InsertEmptyPrice() {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("書籍登録")).click();
		} catch (NoSuchElementException e) {
			try {
				driver.findElement(By.linkText("書籍登録")).click();
			} catch (NoSuchElementException f) {
				driver.findElement(By.linkText("【一覧表示に戻る】")).click();
				driver.findElement(By.linkText("書籍登録")).click();
			}
		}

		// まだ登録されていないISBNを探す
		Book book;
		BookDAO bookdao = new BookDAO();
		String isbn = "";
		for (int i = 0; i < 10000; i++) {
			isbn = String.format("%5s", i).replace(" ", "0");
			book = bookdao.selectByIsbn(isbn);
			if (book.getIsbn() == null) {
				break;
			}
		}

		// ISBN入力
		WebElement InputIsbn = driver.findElement(By.name("isbn"));
		InputIsbn.sendKeys(isbn);

		// タイトルを未入力にする
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.sendKeys("WebアプリケーションTEST");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.sendKeys("");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='登録']")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();

		// メニューボタンをクリック
		driver.findElement(By.linkText("メニュー")).click();
	}


}
