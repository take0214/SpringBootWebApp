package app.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TrainingRecordEntity {
	
	private Date date;
	private int weight;
	private String feeling;
}
