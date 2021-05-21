package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;


/**
 * 管理者テーブルを操作するリポジトリ
 * @author yuta.sanehisa
 *
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * Administratorオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
	
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * 管理者情報を挿入する.
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String insertSql = "INSERT INTO administrators(name, mail_address, password) VALUES(:name, :mail_address, :password)";
		
		template.update(insertSql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報を取得する.
	 * @param mailAddress
	 * @param password
	 * @return　管理者情報を取ってくる(1件も存在しない場合はnullを返す.)
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password){
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress && password = :password ORDER BY id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);	
		
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);
	}
	
}