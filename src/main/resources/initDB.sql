CREATE TABLE customers
(customer_id SERIAL PRIMARY KEY,
 customer_name VARCHAR(100) NOT NULL);

CREATE TABLE developers
(developer_id SERIAL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
gender VARCHAR(20) NOT NULL,
salary int NOT NULL);

CREATE TABLE companies
(company_id SERIAL PRIMARY KEY,
company_name VARCHAR(100) NOT NULL,
headquarters VARCHAR(100) NOT NULL);

CREATE TABLE projects
(project_id SERIAL PRIMARY KEY,
project_name VARCHAR(100) NOT NULL,
project_description VARCHAR(100) NOT NULL,
cost int NOT NULL,
start_date DATE NOT NULL);

CREATE TABLE skills
(skill_id SERIAL PRIMARY KEY,
branch VARCHAR(50) NOT NULL,
stage VARCHAR(100) NOT NULL);

CREATE TABLE customer_projects
(customer_id int NOT NULL,
project_id int NOT NULL,
PRIMARY KEY (customer_id, project_id),
FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
FOREIGN KEY (project_id) REFERENCES projects(project_id));

CREATE TABLE company_projects
(company_id int NOT NULL,
project_id int NOT NULL,
PRIMARY KEY (company_id, project_id),
FOREIGN KEY (company_id) REFERENCES companies(company_id),
FOREIGN KEY (project_id) REFERENCES projects(project_id));

CREATE TABLE project_developers
(project_id int NOT NULL,
developer_id int NOT NULL,
PRIMARY KEY (project_id, developer_id),
FOREIGN KEY (project_id) REFERENCES projects(project_id),
FOREIGN KEY (developer_id) REFERENCES developers(developer_id));

CREATE TABLE developer_skills
(skill_id int NOT NULL,
developer_id int NOT NULL,
PRIMARY KEY (skill_id, developer_id),
FOREIGN KEY (skill_id) REFERENCES skills(skill_id),
FOREIGN KEY (developer_id) REFERENCES developers(developer_id));