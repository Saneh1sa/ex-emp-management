package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面を表示する処理
 * 
 * @author yuta.sanehisa
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/**
	 * InsertAdministratorForm(管理者情報登録フォーム)をインスタンス化している
	 * @return　インスタンス化されたInsertAdministratorFormを返す
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm insertAdministratorForm = new InsertAdministratorForm();
		return insertAdministratorForm;
	};
	
	/**
	 * 管理者登録画面へフォワードしている
	 * @return insert.html
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	} 
}
