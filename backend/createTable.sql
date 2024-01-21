CREATE TABLE IF NOT EXISTS training_menu 
(
	name varchar(50),
	english_name varchar(50),
	part varchar (50),
	max_weight int,
	reps int,
	create_date timestamp,
	update_date timestamp
);

CREATE TABLE IF NOT EXISTS training_data 
(
	date timestamp,
	weight int,
	feeling varchar(50)
);

SELECT relname AS table_name FROM pg_stat_user_tables;

INSERT INTO chestpures( date, weight, feeling ) VALUES( CURRENT_DATE, 10, 'good' ); 

select * from chestpures; 
select * from Leg_Extention;

delete from training_menu


DROP TABLE training_menu;