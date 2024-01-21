package app.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRecordRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createTable(String englishName) {

		String query = "CREATE TABLE "
				+ englishName + "( "
				+ "date DATE, "
				+ "weight INT "
				+ "); ";

		jdbcTemplate.execute(query);
	}

	public void insert(String englishName, Date date, int weight) {

		String query = "INSERT INTO "
				+ englishName + "( "
				+ "date, "
				+ "weight "
				+ ") "
				+ "VALUES( "
				+ "'" + date + "', "
				+ weight + " "
				+ "); ";
		
		jdbcTemplate.update(query);
	}
	
	public List<Map<String, Object>> find (String menu){
		
		String query = "SELECT * FROM " + menu + " ORDER BY date DESC;";
		
		return jdbcTemplate.queryForList(query);
	}
	
	public void dropTable(String tableName) {
		
		String query = "DROP TABLE " + tableName + ";";
		
		jdbcTemplate.execute(query);
	}
}
