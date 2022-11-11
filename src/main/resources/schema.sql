DROP DATABASE IF EXISTS APPLY_FOR_ME;
CREATE DATABASE APPLY_FOR_ME;


CREATE TABLE IF NOT EXISTS APPLIER (
	id bigserial NOT NULL,

  	user_id INT NOT NULL,
  	professional_id INT NOT NULL,
	primary key (id)
);


CREATE TABLE IF NOT EXISTS PROFESSIONAL (
	id bigserial NOT NULL,
	available_for_interview BOOL NOT NULL DEFAULT 'f',

  	user_id INT NOT NULL,
	primary key (id)
);


CREATE TABLE IF NOT EXISTS PROFESSIONAL_ATTACHMENT (
	id bigserial NOT NULL,
	passport_link VARCHAR(400) NOT NULL,
	resume_link VARCHAR(400) NOT NULL,
	cover_letter_link VARCHAR(400) NOT NULL,
	date_of_birth DATE NOT NULL DEFAULT CURRENT_DATE,
	created_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	professional_id INT NOT NULL,
	primary key (id)
);

CREATE TABLE IF NOT EXISTS PROFESSIONAL_METADATA (
	id bigserial NOT NULL,
	salary_range VARCHAR(30) NOT NULL,
 	country_of_residence VARCHAR(150) NOT NULL,
 	nationality VARCHAR(150) NOT NULL,
 	preferred_job_location_type VARCHAR(150) NOT NULL,
 	job_seniority VARCHAR(150) NOT NULL,
 	desired_job_title VARCHAR(150) NOT NULL,
   	industry VARCHAR(150) NOT NULL,
 	years_of_experience VARCHAR(50) NOT NULL,
 	linkedin_link VARCHAR(300) NULL,
 	other_link_1 VARCHAR(300),
 	other_link_2 VARCHAR(300) NULL,
 	hobbies VARCHAR(300) NOT NULL,
  	other_comment TEXT NULL,
 	other_skills TEXT NULL,
	created_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	professional_id INT NOT NULL,
	primary key (id)
);


CREATE TABLE IF NOT EXISTS MEMBER (
	id bigserial NOT NULL,
	first_name VARCHAR(40) NOT NULL,
 	last_name VARCHAR(40) NOT NULL,
 	nationality VARCHAR(150) NOT NULL,
	date_of_birth DATE NOT NULL DEFAULT CURRENT_DATE,
  	job_title VARCHAR(150) NOT NULL,
  	email_address VARCHAR(50) NOT NULL,
  	password TEXT NOT NULL,
  	active BOOL NOT NULL DEFAULT 't',
  	created_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	primary key (id)
);


CREATE TABLE IF NOT EXISTS JOB_SUBMISSION (
	id bigserial NOT NULL,
  	job_title VARCHAR(300) NOT NULL,
  	job_link VARCHAR(300) NOT NULL,
  	other_comment TEXT NOT NULL,
  	created_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_on TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	professional_id INT NOT NULL,
  	applier_id INT NOT NULL,
	primary key (id)
);


CREATE TABLE IF NOT EXISTS MEMBER_ROLE (
	id bigserial NOT NULL,
  	title VARCHAR(200) NOT NULL,

  	member_id INT NOT NULL,
	primary key (id)
);


ALTER TABLE APPLIER
    ADD CONSTRAINT applier_member_id
        FOREIGN KEY (member_id)
            REFERENCES MEMBER (id);

ALTER TABLE APPLIER
    ADD CONSTRAINT applier_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE PROFESSIONAL
    ADD CONSTRAINT professional_member_id
        FOREIGN KEY (member_id)
            REFERENCES MEMBER (id);

ALTER TABLE PROFESSIONAL_ATTACHMENT
    ADD CONSTRAINT attachment_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE PROFESSIONAL_METADATA
    ADD CONSTRAINT metadata_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT job_submission_professional_id
        FOREIGN KEY (professional_id)
            REFERENCES PROFESSIONAL (id);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT job_submission_applier_id
        FOREIGN KEY (applier_id)
            REFERENCES APPLIER (id);

ALTER TABLE MEMBER_ROLE
    ADD CONSTRAINT member_role_member_id
        FOREIGN KEY (member_id)
            REFERENCES MEMBER (id);