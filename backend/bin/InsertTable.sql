select * from training_menu;
delete from training_menu;

INSERT INTO training_menu(name, english_name, part,max_weight,reps,create_date,update_date)
VALUES('トレ1','train1','chest',30,10,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO training_menu(name, english_name, part,max_weight,reps,create_date,update_date)
VALUES('トレ2','train2','chest',30,10,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO training_menu(name, english_name, part,max_weight,reps,create_date,update_date)
VALUES('トレ3','train3','chest',30,10,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);