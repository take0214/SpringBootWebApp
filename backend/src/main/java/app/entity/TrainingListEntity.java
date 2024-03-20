package app.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainingListEntity {
	
	private String id;
	private String name;
	private String bodyPart;
	private int maxWeight;
	private Date lastDay;
	private Timestamp createDate;
	private Timestamp updateDate;
}
