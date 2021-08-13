INSERT INTO developers (first_name, last_name, gender, salary)
				VALUES ('Denis', 'Ivanov', 'male', 2100),
					   ('Olga', 'Petrova', 'female', 800),
					   ('Ivan', 'Denisov', 'male', 1100);

INSERT INTO projects (project_name, project_description, cost, start_date)
			  VALUES ('PBB', 'Pocket Bank Bot', 3200, '2020-10-20'),
				     ('SL', 'SmartLibrary project', 4000, '2019-01-10'),
				     ('SDB', 'SmartDayBook project', 2900, '2021-05-27');

INSERT INTO customers (customer_name)
			   VALUES ('Petr Bubnov'),
			   		  ('Irina Sokolova'),
					  ('Vasilii Petrenko');

INSERT INTO skills (branch, stage)
			VALUES ('Java', 'Junior'),
				   ('Java', 'Middle'),
				   ('Java', 'Senior'),
				   ('Ruby', 'Junior'),
				   ('Ruby', 'Middle'),
				   ('Ruby', 'Senior'),
				   ('PHP', 'Junior'),
				   ('PHP', 'Middle'),
				   ('PHP', 'Senior'),
				   ('Python', 'Junior'),
				   ('Python', 'Middle'),
				   ('Python', 'Senior');

INSERT INTO companies (company_name, headquarters)
			   VALUES ('SmartShop', 'USA'),
			          ('AMAZON', 'USA'),
		       	      ('ProMANAGENT', 'Ukraine');

INSERT INTO customer_projects (customer_id, project_id)
                        VALUES (1, 3),
                                (1, 1),
                                (2, 2),
                                (2, 3),
                                (3, 1),
                                (3, 2);

INSERT INTO company_projects (company_id, project_id)
                        VALUES (3, 3),
                                (3, 1),
                                (2, 2),
                                (2, 3),
                                (3, 2),
                                (1, 2);

INSERT INTO project_developers (project_id, developer_id)
                        VALUES (1, 3),
                               (1, 1),
                               (2, 2),
                               (2, 3),
                               (2, 1),
                               (3, 1),
                               (3, 2);

INSERT INTO developer_skills (skill_id, developer_id)
                        VALUES (3, 1),
                               (4, 1),
                               (8, 1),
                               (6, 2),
                               (9, 2),
                               (2, 3),
                               (5, 3),
                               (8, 3),
                               (11, 3);