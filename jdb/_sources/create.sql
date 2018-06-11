CREATE TABLE country(id integer NOT NULL, caption text NOT NULL, CONSTRAINT pk_country PRIMARY KEY (id), CONSTRAINT uq_country_caption UNIQUE (caption));

CREATE TABLE city(id integer NOT NULL, caption text NOT NULL, country_id integer NOT NULL, CONSTRAINT pk_city PRIMARY KEY (id), CONSTRAINT uq_city_caption UNIQUE (caption), CONSTRAINT fk_city_country_id FOREIGN KEY (country_id) REFERENCES country(id));

CREATE TABLE roleuser(id integer NOT NULL, code text NOT NULL, description text, CONSTRAINT pk_roleuser PRIMARY KEY (id), CONSTRAINT uq_roleuser_code UNIQUE (code));

CREATE TABLE userx(id integer NOT NULL, username text NOT NULL, passwordencode text NOT NULL, token text NOT NULL, CONSTRAINT pk_userx PRIMARY KEY (id), CONSTRAINT uq_userx_username UNIQUE (username), CONSTRAINT uq_userx_token UNIQUE (token));

CREATE TABLE user_on_role(id integer NOT NULL, userx_id integer NOT NULL, roleuser_id integer NOT NULL, CONSTRAINT pk_user_on_role PRIMARY KEY (id), CONSTRAINT uq_userx__role_transit UNIQUE (userx_id, roleuser_id), CONSTRAINT fk_user__role_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_user__role_roleuser_id FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE);

// Два варианта профиля
CREATE TABLE profile(id integer NOT NULL, name text NOT NULL, surname text NOT NULL, middlename text, userx_id integer NOT NULL, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id));

CREATE TABLE profile(id integer NOT NULL, name text NOT NULL, surname text NOT NULL, middlename text, userx_id integer NOT NULL, datebirth DATE, phone text, city_id integer, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id), CONSTRAINT fk_profile_city_id FOREIGN KEY (city_id) REFERENCES city(id));

