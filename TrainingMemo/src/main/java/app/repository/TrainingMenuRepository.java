package app.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingMenuRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> find() {

		String query = "SELECT * FROM training_menu";

		return jdbcTemplate.queryForList(query);
	}
	
	public int findMaxWeight(String englishName) {
		
		String query = "SELECT max_weight FROM training_menu WHERE english_name = ?;";
		
		Map<String, Object> object = jdbcTemplate.queryForMap(query, englishName);
		
		return (int)object.get("max_weight");
	}

	public void insert(String name, String englishName, String part, int reps, int set) {

		String query = "INSERT INTO "
				+ "training_menu( "
				+ "name, "
				+ "english_name, "
				+ "part, "
				+ "max_weight, "
				+ "reps, "
				+ "set, "
				+ "create_date, "
				+ "update_date "
				+ ") "
				+ "VALUES("
				+ "'" + name + "', "
				+ "'" + englishName + "', "
				+ "'" + part + "', "
				+ "0, "
				+ "'" + reps + "', "
				+ "'" + set + "', "
				+ "CURRENT_TIMESTAMP, "
				+ "CURRENT_TIMESTAMP "
				+ ");";

		jdbcTemplate.update(query);
	}
	
	public void updateMaxWeight(int weight) {
		
		String query = "UPDATE training_menu SET max_weight = " + weight + ";";
		
		jdbcTemplate.update(query);
	}
	
	public void delete(String englishName) {
		
		String query = "DELETE FROM training_menu "
				+ "WHERE english_name = '" + englishName +"';";
		
		jdbcTemplate.update(query);
	}
}
