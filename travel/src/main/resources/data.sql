INSERT IGNORE INTO `categories` (`category_id`,`category_name`)
VALUES(1,'食事'),(2,'移動'),(3,'買い物'),(4,'準備'),(5,'レジャー'),(6,'観光'),(7,'就寝'),(8,'その他');

INSERT IGNORE INTO `chats` (`chat_id`,`send_time`,`text`,project_id)
VALUES(1,'2111-01-01 12:10:10','test',1),(2,'2111-01-01 12:10','test',2),(3,'2111-01-01 12:10','test',4);


INSERT IGNORE INTO `projects` (`project_id`,`created_date`,`last_date`,`project_name`,`start_date`,`user_id`)
VALUES(1,'2019-11-12','2019-12-11','test','2019-12-14',1),
(2,'2019-12-12','2020-01-11','test1','2019-01-14',1),
(3,'2019-11-12','2019-12-11','test2','2019-12-14',2)
;


INSERT IGNORE INTO `members` (`project_id`,`user_id`,`auth_id`)
VALUES(3,1,1),(2,1,1),(2,1,3);

INSERT IGNORE INTO `answers` (`answer_id`,`question_id`,`user_id`,choice_id)
VALUES(1,1,1,1),(2,1,2,1),(3,1,3,2);

INSERT IGNORE INTO `choices` (`choice_id`,`question_id`,`choice_text`)
VALUES(1,1,'testtext'),(2,1,'testtext2'),(3,1,'testtext3');

INSERT IGNORE INTO `questions` (`question_id`,`title`,`question_detail`,`limit_time`,`answer_fin`,project_id)
VALUES
(1,'ques2','test','2019-06-20 12:20','false',1),
(2,'ques2','test','2019-06-20 12:20','true',2),
(3,'ques2','test','2019-06-20 12:20',b'1',3),
(4,'ques2','testtest','2019-06-20 12:20',b'0',1);
;

INSERT IGNORE INTO `schedules` (`sc_id`,`project_id`,`sc_name`,`category_id`,`start_time`,`last_time`,`details`)
VALUES
(1,1,'test',2,'2019-11-11','2019-11-13','詳細'),
(2,1,'test2',2,'2019-11-11','2019-11-13','詳細aaa');


INSERT IGNORE INTO `users` (`user_id`,`account_name`,`password`,`mailAddress`)
VALUES
(1,'travel','$2a$10$U/0Ud7i39FxjyRJ3F4TyN.IIYCuVZX1rrxHYPoTT35RdWrrDN5m.6','travel@tcmobile.jp');



