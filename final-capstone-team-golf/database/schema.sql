-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id CASCADE;
DROP TABLE IF EXISTS tag CASCADE;
DROP SEQUENCE IF EXISTS seq_tag_id CASCADE;
DROP TABLE IF EXISTS recipe CASCADE;
DROP SEQUENCE IF EXISTS seq_recipe_id CASCADE;
DROP TABLE IF EXISTS diet CASCADE ;
DROP SEQUENCE IF EXISTS seq_diet_id CASCADE;
DROP TABLE IF EXISTS tag_recipe CASCADE;
DROP TABLE IF EXISTS meal_plans CASCADE;
DROP SEQUENCE IF EXISTS seq_meal_id CASCADE;
DROP TABLE IF EXISTS cat_recipe CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP SEQUENCE IF EXISTS seq_category_id CASCADE;

CREATE SEQUENCE seq_recipe_id;

create table recipe (
recipe_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_recipe_id'), 
name varchar(100),
serving_size INTEGER,
preptime VARCHAR(10),
instructions VARCHAR(2000),
user_created BOOLEAN DEFAULT FALSE,
admin_created BOOLEAN DEFAULT TRUE,
ingredient_list VARCHAR(1000),
image_path VARCHAR(100) DEFAULT 'dinner',
user_uploaded_image BOOLEAN DEFAULT false


);

CREATE SEQUENCE seq_user_id;

CREATE TABLE users (
	user_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_user_id'),
	email VARCHAR(150) NOT NULL,
	password VARCHAR(150) NOT NULL,
	admin BOOLEAN DEFAULT FALSE,
	UNIQUE(email)
	
);

CREATE SEQUENCE seq_tag_id;

CREATE TABLE tag (
	tag_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_tag_id'), 
	tag_name varchar(100)
	
);



CREATE TABLE tag_recipe (
        tag_id INTEGER NOT NULL, 
        recipe_id INTEGER NOT NULL,
        
    CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tag(tag_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);

CREATE SEQUENCE seq_meal_id;

create table meal_plans(
	meal_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_meal_id'),
	user_id INTEGER NOT NULL,
	day_id Integer NOT NULL,
	recipe_id INTEGER NOT NULL,
	
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)

);

CREATE SEQUENCE seq_category_id;

CREATE TABLE category (
        category_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_category_id'),
        category_name varchar(50)
        
);

CREATE TABLE cat_recipe (
        category_id INTEGER,
        recipe_id INTEGER,
        
        CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(category_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);

CREATE SEQUENCE seq_diet_id;

CREATE TABLE diet (
	diet_id INTEGER PRIMARY KEY DEFAULT NEXTVAL('seq_diet_id'),
	name VARCHAR(150) NOT NULL,
	imageName VARCHAR(128),
	day_id INTEGER,
	user_id integer,
	recipe_id integer,
	
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);

COMMIT;