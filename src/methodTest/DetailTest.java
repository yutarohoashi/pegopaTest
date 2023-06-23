package methodTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;

import dao.BookDAO;
import bean.Book;

public class DetailTest {

	private WebDriver driver;

	//DBからリストを取得
	BookDAO bookdao = new BookDAO();
	ArrayList<Book> book_list = bookdao.selectAll();

	// 既に登録されている先頭のISBNを取得
	String isbn = book_list.get(1).getIsbn();

	public DetailTest(WebDriver driver) {
		this.driver = driver;
	}

	//一覧機能をテストするメソッドを呼び出す
	public void Detail() throws InterruptedException {
		DetailTest detailtest = new DetailTest(driver);

		// 正常動作確認
		detailtest.detail100();
		Thread.sleep(1000);

		// リンク押下する予定の書籍情報をMySQL上から削除する
		detailtest.detail202();
		Thread.sleep(1000);

		// リンク押下する予定の書籍情報をMySQL上から削除する
		detailtest.detail204();
		Thread.sleep(1000);
	}



	// 正常に一覧表示処理が行われるか
	public void detail100() throws InterruptedException {

		// No.101 書籍一覧画面でISBNに設定されているリンク押下
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		// No.102 更新ボタン押下
		driver.findElement(By.linkText(isbn)).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value='変更']")).submit();
		driver.findElement(By.linkText("書籍一覧")).click();

		// No.103 更新ボタンをクリック
		driver.findElement(By.linkText("削除")).click();
		Thread.sleep(1000);

		// 正常処理終了
		//メニュー画面に戻る
		driver.findElement(By.linkText("メニュー")).click();
		Thread.sleep(1000);
	}

	// 異常処理
	// 画面表示している書籍情報をMySQL上から削除し
	// 更新ボタンを押下
	public void detail202() throws InterruptedException {

		// 書籍登録画面まで遷移する
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("【ログアウト】")).click();
		}

		ArrayList<Book> book_list = bookdao.selectAll();

		// 既に登録されている先頭のISBNを取得
		String isbn = book_list.get(0).getIsbn();

		driver.findElement(By.linkText(isbn)).click();
		Thread.sleep(1000);
		bookdao.delete(isbn);

		// 変更ボタンクリック
		driver.findElement(By.cssSelector("input[value='変更']")).submit();
		Thread.sleep(1000);

		// 一覧に戻るをクリック
		driver.findElement(By.linkText("[一覧に戻る]")).click();

		// 処理終了
		//メニュー画面に戻る
		driver.findElement(By.linkText("メニュー")).click();

	}

	// 画面表示している書籍情報をMySQL上から削除し
	// 削除ボタンを押下
		public void detail204() throws InterruptedException {
			// 書籍登録画面まで遷移する
			try {
				driver.findElement(By.linkText("【書籍一覧】")).click();
			} catch (NoSuchElementException e) {
				driver.findElement(By.linkText("【ログアウト】")).click();
			}

			ArrayList<Book> book_list = bookdao.selectAll();

			// 既に登録されている先頭のISBNを取得
			String isbn = book_list.get(0).getIsbn();
			driver.findElement(By.linkText(isbn)).click();
			Thread.sleep(1000);
			bookdao.delete(isbn);

			// 変更ボタンクリック
			driver.findElement(By.cssSelector("input[value='削除']")).submit();
			Thread.sleep(1000);

			// 一覧に戻るをクリック
			driver.findElement(By.linkText("[一覧に戻る]")).click();

			// 処理終了
			//メニュー画面に戻る
			driver.findElement(By.linkText("メニュー")).click();
		}
}
