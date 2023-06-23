package methodTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;

import dao.BookDAO;
import bean.Book;

public class ListTest {

	private WebDriver driver;

	//DBからリストを取得
	BookDAO bookdao = new BookDAO();
	ArrayList<Book> book_list = bookdao.selectAll();

	// 既に登録されている先頭のISBNを取得
	String isbn = book_list.get(1).getIsbn();

	public ListTest(WebDriver driver) {
		this.driver = driver;
	}

	//一覧機能をテストするメソッドを呼び出す
	public void List() throws InterruptedException {
		ListTest listtest = new ListTest(driver);

		// 正常動作確認
		listtest.list100();
		Thread.sleep(1000);

		// リンク押下する予定の書籍情報をMySQL上から削除する
		listtest.list202();
		Thread.sleep(1000);
		// リンク押下する予定の書籍情報をMySQL上から削除する
		listtest.list204();
		Thread.sleep(1000);
		// タイトルが未入力の場合エラー画面に遷移するか
		listtest.list206();
		Thread.sleep(1000);
	}



	// 正常に一覧表示処理が行われるか
	public void list100() throws InterruptedException {

		// No.101 書籍一覧画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		// No.102 既に登録されているISBNを入力
		driver.findElement(By.linkText(isbn)).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("書籍一覧")).click();

		// No.103 更新ボタンをクリック
		driver.findElement(By.linkText("更新")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("書籍一覧")).click();

		// No.104 削除ボタンをクリック
		driver.findElement(By.linkText("削除")).click();
		Thread.sleep(1000);

		// No.105 登録ボタンをクリック
		driver.findElement(By.linkText("書籍登録")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("書籍一覧")).click();

		// 正常処理終了
		// No.106 メニュー画面に戻る
		driver.findElement(By.linkText("メニュー")).click();
		Thread.sleep(1000);
	}

	// 異常処理
	// ISBNリンク押下する予定の書籍情報をMySQL上から削除する
	public void list202() throws InterruptedException {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		bookdao.delete(isbn);
		driver.get("http://localhost:8080/bmsweb20j_oshiroyuiki/detail?isbn=" + isbn +"&cmd=detail");
		Thread.sleep(1000);

		// 一覧に戻るをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();

		// 処理終了
		//メニュー画面に戻る
		driver.findElement(By.linkText("メニュー")).click();

	}

	//更新リンク押下する予定の書籍情報をMySQL上から削除する
		public void list204() throws InterruptedException {
			// 書籍登録画面まで遷移する
			try {
				driver.findElement(By.linkText("【書籍一覧】")).click();
			} catch (NoSuchElementException e) {
				driver.findElement(By.linkText("【ログアウト】")).click();
			}

			driver.get("http://localhost:8080/bmsweb20j_oshiroyuiki/detail?isbn=" + isbn +"&cmd=update");
			Thread.sleep(1000);

			// メニューボタンをクリック
			driver.findElement(By.linkText("[一覧に戻る]")).click();
			// 処理終了
			//メニュー画面に戻る
			driver.findElement(By.linkText("メニュー")).click();
		}

		//削除リンク押下する予定の書籍情報をMySQL上から削除する
		public void list206() throws InterruptedException {
			// 書籍登録画面まで遷移する
			try {
				driver.findElement(By.linkText("【書籍一覧】")).click();
			} catch (NoSuchElementException e) {
				driver.findElement(By.linkText("【ログアウト】")).click();
			}

			driver.get("http://localhost:8080/bmsweb20j_oshiroyuiki/delete?isbn=" + isbn);
			Thread.sleep(1000);

			// メニューボタンをクリック
			driver.findElement(By.linkText("[一覧に戻る]")).click();

			// メニューボタンをクリック
			driver.findElement(By.linkText("メニュー")).click();

		}
}
