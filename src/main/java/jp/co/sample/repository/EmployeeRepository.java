package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員テーブルを操作するリポジトリ.
 * 
 * @author yuta.sanehisa
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> Employee_ROW_MAPPER = (rs, i) -> {

		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("departments_count"));
		return employee;
	};

	/**
	 * 従業員一覧情報を入社日降順で習得します.
	 * 
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します.
	 */
	public List<Employee> findAll() {
		String findAllSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC";

		List<Employee> employeeList = template.query(findAllSql, Employee_ROW_MAPPER);

		return employeeList;
	}

	/**
	 * 主キー検索を行います.
	 * 
	 * @param id ID
	 * @return　employee 従業員情報(検索されなかった場合は例外が発生します.)
	 */
	public Employee load(Integer id) {
		String loadSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(loadSql, param, Employee_ROW_MAPPER);

		return employee;
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 従業員情報
	 */
	public void update(Employee employee) {
        
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		
		String updateSql = "UPDATE employees "
				+ "SET name = :name,image = :image, gender = :gender,hire_date = :hireDate, mail_address = :mailAddress, zip_code = :zipCode,"
				+ " address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependentsCount = :dependentsCount "
				+ "WHERE id = :id";
		
		template.update(updateSql, param);
	}

}
