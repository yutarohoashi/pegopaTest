package methodTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;


public class UpdateTest {

	private WebDriver driver;
	private ArrayList<WebElement> elem_list;

	public UpdateTest(WebDriver driver) {
		this.driver = driver;
	}

	public void MoveUpdatePage() {
		try {
			driver.findElement(By.linkText("【書籍一覧】")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.linkText("[一覧に戻る]")).click();
		} finally {
			elem_list = (ArrayList<WebElement>)driver.findElements(By.linkText("更新"));
			elem_list.get(0).click();
		}
	}

	//変更機能をテストするメソッドを呼び出す
	public void Update() throws InterruptedException {

		UpdateTest updatetest = new UpdateTest(driver);

		// 正常に書籍を変更できるか
		updatetest.Update100();
		// タイトル未入力の場合エラー画面に遷移するか
		updatetest.Update202();
		// 価格未入力の場合エラー画面に遷移するか
		updatetest.Update203();

	}

	// 正常処理
	// 正常に書籍情報を変更できるか
	public void Update100() {

		// 書籍変更画面まで遷移
		MoveUpdatePage();

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.clear();
		InputTitle.sendKeys("WebアプリケーションTEST：変更後");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.clear();
		inputPrice.sendKeys("2000");

		// 登録ボタンをクリック
		driver.findElement(By.cssSelector("input[value='変更完了']")).submit();

		// メニューボタンをクリック
		driver.findElement(By.linkText("メニュー")).click();
	}

	// 異常系
	// タイトル未入力の場合エラー画面に遷移するか
	public void Update202() {

		MoveUpdatePage();

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.clear();


		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.clear();
		inputPrice.sendKeys("2000");

		// 登録ボタンをクリック
		//driver.findElement(By.name("isbn")).click();
		driver.findElement(By.cssSelector("input[value='変更完了']")).submit();

	}

	// 価格未入力の場合エラー画面に遷移するか
	public void Update203() {

		MoveUpdatePage();

		// タイトル入力
		WebElement InputTitle = driver.findElement(By.name("title"));
		InputTitle.clear();
		InputTitle.sendKeys("WebアプリケーションTEST：変更後");

		// 価格入力
		WebElement inputPrice = driver.findElement(By.name("price"));
		inputPrice.clear();

		// 登録ボタンをクリック
		//driver.findElement(By.name("isbn")).click();
		driver.findElement(By.cssSelector("input[value='変更完了']")).submit();

		// 処理終了
		driver.findElement(By.linkText("[一覧に戻る]")).click();
		driver.findElement(By.linkText("メニュー")).click();
		driver.findElement(By.linkText("【ログアウト】")).click();
	}
}

