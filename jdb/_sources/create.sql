CREATE TABLE country(id INTEGER NOT NULL, caption TEXT NOT NULL, CONSTRAINT pk_country PRIMARY KEY (id), CONSTRAINT uq_country_caption UNIQUE (caption));

CREATE TABLE city(id INTEGER NOT NULL, caption TEXT NOT NULL, country_id INTEGER NOT NULL, CONSTRAINT pk_city PRIMARY KEY (id), CONSTRAINT uq_city_caption UNIQUE (caption, country_id), CONSTRAINT fk_city_country_id FOREIGN KEY (country_id) REFERENCES country(id));

CREATE TABLE roleuser(id INTEGER NOT NULL, code TEXT NOT NULL, description TEXT, CONSTRAINT pk_roleuser PRIMARY KEY (id), CONSTRAINT uq_roleuser_code UNIQUE (code));

CREATE TABLE userx(id INTEGER NOT NULL, username TEXT NOT NULL, passwordencode TEXT NOT NULL, CONSTRAINT pk_userx PRIMARY KEY (id), CONSTRAINT uq_userx_username UNIQUE (username));

CREATE TABLE user_on_role(id INTEGER NOT NULL, userx_id INTEGER NOT NULL, roleuser_id INTEGER NOT NULL, CONSTRAINT pk_user_on_role PRIMARY KEY (id), CONSTRAINT uq_userx__role_transit UNIQUE (userx_id, roleuser_id), CONSTRAINT fk_user__role_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_user__role_roleuser_id FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(id INTEGER NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL, middlename TEXT, userx_id INTEGER NOT NULL, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE);

// 2 - ProfileInfo
CREATE TABLE profile(id INTEGER NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL, middlename TEXT, userx_id INTEGER NOT NULL, datebirth DATE, phone TEXT, city_id INTEGER, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_profile_city_id FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE);

// Два варианта токена
// 1 - JwtToken
CREATE TABLE token(id INTEGER NOT NULL, accessToken TEXT NOT NULL, refreshToken TEXT NOT NULL, expireTimeAccess DATE NOT NULL, expireTimeRefresh DATE NOT NULL, issuedAt DATE NOT NULL, issuer TEXT, subject TEXT, notBefore DATE, audience TEXT, secret TEXT, algorithm TEXT, type TEXT);

// 2 - JwtUserToken
CREATE TABLE token(id INTEGER NOT NULL, accessToken TEXT NOT NULL, refreshToken TEXT NOT NULL, expireTimeAccess DATE NOT NULL, expireTimeRefresh DATE NOT NULL, issuedAt DATE NOT NULL, issuer TEXT, subject TEXT, notBefore DATE, audience TEXT, secret TEXT, algorithm TEXT, type TEXT, userx_id INTEGER NOT NULL, roleuser_id INTEGER NOT NULL, CONSTRAINT pk_token PRIMARY KEY (id), CONSTRAINT fk_token_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_token_roleuser_id FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE);

---------------------
------- UUID --------
---------------------

CREATE TABLE country(id TEXT NOT NULL, caption TEXT NOT NULL, CONSTRAINT pk_country PRIMARY KEY (id), CONSTRAINT uq_country_caption UNIQUE (caption));

CREATE TABLE city(id TEXT NOT NULL, caption TEXT NOT NULL, country_id TEXT NOT NULL, CONSTRAINT pk_city PRIMARY KEY (id), CONSTRAINT uq_city_caption UNIQUE (caption, country_id), CONSTRAINT fk_city_country_id FOREIGN KEY (country_id) REFERENCES country(id));

CREATE TABLE roleuser(id TEXT NOT NULL, code TEXT NOT NULL, description TEXT, CONSTRAINT pk_roleuser PRIMARY KEY (id), CONSTRAINT uq_roleuser_code UNIQUE (code));

CREATE TABLE userx(id TEXT NOT NULL, username TEXT NOT NULL, passwordencode TEXT NOT NULL, CONSTRAINT pk_userx PRIMARY KEY (id), CONSTRAINT uq_userx_username UNIQUE (username));

CREATE TABLE user_on_role(id TEXT NOT NULL, userx_id TEXT NOT NULL, roleuser_id TEXT NOT NULL, CONSTRAINT pk_user_on_role PRIMARY KEY (id), CONSTRAINT uq_userx__role_transit UNIQUE (userx_id, roleuser_id), CONSTRAINT fk_user__role_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_user__role_roleuser_id FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(id TEXT NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL, middlename TEXT, userx_id TEXT NOT NULL, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE);

// 2 - ProfileInfo
CREATE TABLE profile(id TEXT NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL, middlename TEXT, userx_id TEXT NOT NULL, datebirth DATE, phone TEXT, city_id TEXT, CONSTRAINT pk_profile PRIMARY KEY (id), CONSTRAINT uq_profile_userx_id UNIQUE (userx_id), CONSTRAINT fk_profile_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_profile_city_id FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE);

// Два варианта токена
// 1 - JwtToken
CREATE TABLE token(id TEXT NOT NULL, accessToken TEXT NOT NULL, refreshToken TEXT NOT NULL, expireTimeAccess DATE NOT NULL, expireTimeRefresh DATE NOT NULL, issuedAt DATE NOT NULL, issuer TEXT, subject TEXT, notBefore DATE, audience TEXT, secret TEXT, algorithm TEXT, type TEXT);

// 2 - JwtUserToken
CREATE TABLE token(id TEXT NOT NULL, accessToken TEXT NOT NULL, refreshToken TEXT NOT NULL, expireTimeAccess DATE NOT NULL, expireTimeRefresh DATE NOT NULL, issuedAt DATE NOT NULL, issuer TEXT, subject TEXT, notBefore DATE, audience TEXT, secret TEXT, algorithm TEXT, type TEXT, userx_id TEXT NOT NULL, roleuser_id TEXT NOT NULL, CONSTRAINT pk_token PRIMARY KEY (id), CONSTRAINT fk_token_userx_id FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT fk_token_roleuser_id FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE);
