package app.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import app.entity.TrainingListEntity;

@Mapper
public interface TrainingListMapper {

	@Select("SELECT "
			+ "id, "
			+ "name, "
			+ "body_part, "
			+ "max_weight, "
			+ "last_day, "
			+ "create_date, "
			+ "update_date "
			+ "FROM training_list "
			+ "ORDER BY last_day DESC")
	public TrainingListEntity[] find();
	
	@Select("SELECT "
			+ "id, "
			+ "name, "
			+ "body_part, "
			+ "max_weight, "
			+ "last_day, "
			+ "create_date, "
			+ "update_date "
			+ "FROM training_list "
			+ "WHERE id = #{id}")
	public TrainingListEntity findById(int id);
	
	@Insert("INSERT INTO training_list("
			+ "name, "
			+ "body_part, "
			+ "max_weight, "
			+ "last_day, "
			+ "create_date, "
			+ "update_date"
			+ ") VALUES ("
			+ "#{name}, "
			+ "#{bodyPart}, "
			+ "0, "
			+ "CURRENT_DATE, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP"
			+ ")")
	public int insert(String name, String bodyPart);
	
	@Update("UPDATE training_list "
			+ "SET max_weight = #{weight}, "
			+ "last_day = #{date}, "
			+ "update_date = CURRENT_TIMESTAMP "
			+ "WHERE id = #{id};")
	public int update(int id, int weight, Date date);
	
	@Delete("DELETE FROM training_list "
			+ "WHERE id = #{id};")
	public int detele(int id);
}
