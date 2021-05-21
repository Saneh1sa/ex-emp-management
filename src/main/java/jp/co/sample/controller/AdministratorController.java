package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面を表示する処理.
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
	 * InsertAdministratorForm(管理者情報登録フォーム)をインスタンス化している.
	 * 
	 * @return　インスタンス化されたInsertAdministratorFormを返す.
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	};
	
	/**
	 * LoginForm(ログイン時フォーム)をインスタンス化している.
	 * 
	 * @return インスタンス化されたLoginFormを返す.
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面に移行する.
	 * 
	 * @return　administrator/login ログイン画面にフォワード.
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * 管理者登録画面へフォワードしている.
	 * 
	 * @return insert 管理者登録画面へフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	} 
	
	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 管理者登録時使用フォーム
	 * @return ログイン画面にリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		
		Administrator administrator = new Administrator();
		
		BeanUtils.copyProperties(form,administrator);
		
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	
}
