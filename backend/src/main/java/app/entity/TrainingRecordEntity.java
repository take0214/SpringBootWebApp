package app.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainingRecordEntity {
	
	private int id;
	private Date date;
	private int weight;
	private int reps;
	private int trainingId;
	private Timestamp createDate;
	private Timestamp updateDate;
}
