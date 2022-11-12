DROP DATABASE IF EXISTS APPLY_FOR_ME;
CREATE DATABASE APPLY_FOR_ME;


CREATE TABLE IF NOT EXISTS APPLIER (
	`id` BIGSERIAL NOT NULL,

  	`member_id` INT NOT NULL,
  	`professional_id` INT NOT NULL,

	PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS PROFESSIONAL (
	`id` BIGSERIAL NOT NULL,
	`available_for_interview` BOOL NOT NULL DEFAULT 'f',

  	`member_id` INT NOT NULL,

    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS PROFESSIONAL_ATTACHMENT (
	`id` BIGSERIAL NOT NULL,
	`passport_link` VARCHAR(400) NOT NULL,
	`resume_link` VARCHAR(400) NOT NULL,
	`cover_letter_link` VARCHAR(400) NOT NULL,
	`date_of_birth` DATE NOT NULL DEFAULT CURRENT_DATE,
	`created_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	`professional_id` INT NOT NULL,
	primary key (`id`)
);

CREATE TABLE IF NOT EXISTS PROFESSIONAL_METADATA (
	`id` BIGSERIAL NOT NULL,
	`salary_range` VARCHAR(30) NOT NULL,
 	`country_of_residence` VARCHAR(150) NOT NULL,
 	`nationality` VARCHAR(150) NOT NULL,
 	`preferred_job_location_type` VARCHAR(150) NOT NULL,
 	`job_seniority` VARCHAR(150) NOT NULL,
 	`desired_job_title` VARCHAR(150) NOT NULL,
   	`industry` VARCHAR(150) NOT NULL,
 	`years_of_experience` VARCHAR(50) NOT NULL,
 	`linkedin_link` VARCHAR(300) NULL,
 	`other_link_1` VARCHAR(300),
 	`other_link_2` VARCHAR(300) NULL,
 	`hobbies` VARCHAR(300) NOT NULL,
  	`other_comment` TEXT NULL,
 	`other_skills` TEXT NULL,
	`created_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	`professional_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS MEMBER (
	`id` BIGSERIAL NOT NULL,
	`first_name` VARCHAR(40) NOT NULL,
 	`last_name` VARCHAR(40) NOT NULL,
 	`nationality` VARCHAR(150) NOT NULL,
	`date_of_birth` DATE NOT NULL DEFAULT CURRENT_DATE,
  	`job_title` VARCHAR(150) NOT NULL,
  	`phone_number` VARCHAR(15) NOT NULL,
  	`email_address` VARCHAR(50) NOT NULL,
  	`password` TEXT NOT NULL,
  	`avatar` VARCHAR(300) NOT NULL,
  	`active` BOOL NOT NULL DEFAULT 't',
  	`created_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS JOB_SUBMISSION (
	`id` BIGSERIAL NOT NULL,
  	`job_title` VARCHAR(300) NOT NULL,
  	`job_link` VARCHAR(300) NOT NULL,
  	`other_comment` TEXT NOT NULL,
  	`created_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_on` TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  	`professional_id` INT NOT NULL,
  	`applier_id` INT NOT NULL,
	primary key (`id`)
);


CREATE TABLE IF NOT EXISTS ROLES (
	`id` BIGSERIAL NOT NULL,
  	`title` VARCHAR(200) NOT NULL,

	primary key (`id`)
);

CREATE TABLE `MEMBER_ROLES` (
  `member_id` INT NOT NULL,
  `role_id` INT NOT NULL
);

CREATE UNIQUE INDEX
    CONCURRENTLY `email_phone_uq`
        ON MEMBER (`email_address`, `phone_number`);

ALTER TABLE MEMBER
    ADD CONSTRAINT `email_address_phone_number_uq`
        UNIQUE USING INDEX `email_phone_uq`;

ALTER TABLE APPLIER
    ADD CONSTRAINT `member_fk`
        FOREIGN KEY (`member_id`)
            REFERENCES MEMBER (`id`);

ALTER TABLE PROFESSIONAL
    ADD CONSTRAINT `member_fk`
        FOREIGN KEY (`member_id`)
            REFERENCES MEMBER (`id`);

ALTER TABLE PROFESSIONAL_ATTACHMENT
    ADD CONSTRAINT `professional_fk`
        FOREIGN KEY (`professional_id`)
            REFERENCES PROFESSIONAL (`id`);

ALTER TABLE PROFESSIONAL_METADATA
    ADD CONSTRAINT `professional_fk`
        FOREIGN KEY (`professional_id`)
            REFERENCES PROFESSIONAL (`id`);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT `professional_fk`
        FOREIGN KEY (`professional_id`)
            REFERENCES PROFESSIONAL (`id`);

ALTER TABLE JOB_SUBMISSION
    ADD CONSTRAINT `applier_fk`
        FOREIGN KEY (`applier_id`)
            REFERENCES APPLIER (`id`);

ALTER TABLE MEMBER_ROLES
    ADD CONSTRAINT `role_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `ROLES` (`id`)
                ON CASCADE DELETE;

ALTER TABLE MEMBER_ROLES
    ADD CONSTRAINT `member_fk`
        FOREIGN KEY (`member_id`)
            REFERENCES `MEMBER` (`id`)
                ON CASCADE DELETE;

ALTER TABLE MEMBER_ROLES
    ADD CONSTRAINT member_role_uq
        UNIQUE(`member_id`, `role_id`);