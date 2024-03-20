package app.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import app.entity.TrainingRecordEntity;

@Mapper
public interface TrainingRecordMapper {

	@Select("SELECT "
			+ "id, "
			+ "date, "
			+ "weight, "
			+ "reps, "
			+ "training_id, "
			+ "create_date, "
			+ "update_date "
			+ "FROM training_record "
			+ "ORDER BY date DESC")
	public TrainingRecordEntity[] find();
	
	@Select("SELECT "
			+ "id, "
			+ "date, "
			+ "weight, "
			+ "reps, "
			+ "training_id, "
			+ "create_date, "
			+ "update_date "
			+ "FROM training_record "
			+ "WHERE training_id = #{trainingId} "
			+ "ORDER BY date DESC")
	public TrainingRecordEntity[] findByTrainingId(int trainingId);
	
	@Select("SELECT "
			+ "id, "
			+ "date, "
			+ "weight, "
			+ "reps, "
			+ "training_id, "
			+ "create_date, "
			+ "update_date "
			+ "FROM training_record "
			+ "WHERE date = #{date}"
			+ "ORDER BY date DESC")
	public TrainingRecordEntity[] findByDate(Date date);
	
	@Insert("INSERT INTO training_record("
			+ "date, "
			+ "weight, "
			+ "reps, "
			+ "training_id, "
			+ "create_date, "
			+ "update_date "
			+ ") VALUES ("
			+ "#{date}, "
			+ "#{weight}, "
			+ "#{reps}, "
			+ "#{trainingId}, "
			+ "CURRENT_TIMESTAMP, "
			+ "CURRENT_TIMESTAMP"
			+ ")")
	public int insert(Date date, int weight, int reps, int trainingId);
	
	@Delete("DELETE FROM training_record "
			+ "WHERE id = #{id};")
	public int detele(int id);

	@Delete("DELETE FROM training_record "
			+ "WHERE training_id = #{trainingId};")
	public int deteleByTrainingId(int trainingId);	
}
