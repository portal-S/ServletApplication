CREATE TABLE files
(id integer NOT NULL AUTO_INCREMENT,
 name varchar(255) NOT NULL,
 PRIMARY KEY (id)
);

/*CREATE TABLE teams
(id integer NOT NULL AUTO_INCREMENT,
 name varchar(55) NOT NULL,
 PRIMARY KEY (id)
);

CREATE TABLE developers
(id integer NOT NULL AUTO_INCREMENT,
 firstName varchar(55) NOT NULL,
 lastName varchar(55) NOT NULL,
 team_id integer ,
 PRIMARY KEY (id),
 FOREIGN KEY(team_id) REFERENCES teams(id) ON DELETE SET NULL
);

create table skills_developers
(skill_id integer not null,
 developer_id integer not null,
 FOREIGN KEY(developer_id) REFERENCES developers(id) ON DELETE CASCADE,
 FOREIGN KEY(skill_id) REFERENCES skills(id) ON DELETE CASCADE
);*/