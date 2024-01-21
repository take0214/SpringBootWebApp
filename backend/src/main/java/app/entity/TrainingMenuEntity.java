package app.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainingMenuEntity {
	
	private String name;
	private String englishName;
	private String part;
	private int maxWeight;
	private int reps;
	private int set;
	private Timestamp createDate;
	private Timestamp updateDate;
}
