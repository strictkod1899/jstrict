-----------------------------
------- Id - INTEGER --------
-----------------------------

CREATE TABLE country(
  id INTEGER NOT NULL,
  caption VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (caption)
);

CREATE TABLE city(
  id INTEGER NOT NULL,
  caption VARCHAR(255) NOT NULL,
  country_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (caption, country_id),
  FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE role(
  id INTEGER NOT NULL,
  code VARCHAR(255) NOT NULL,
  description TEXT,
  PRIMARY KEY (id),
  UNIQUE (code)
);

CREATE TABLE userx(
  id INTEGER NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  passwordencode TEXT NOT NULL,
  is_blocked BOOLEAN NOT NULL,
  is_deleted BOOLEAN NOT NULL,
  is_confirm_email BOOLEAN NOT NULL,
  salt VARCHAR(255),
  secret VARCHAR(255),
  PRIMARY KEY (id),
  UNIQUE (username),
  UNIQUE (email)
);

CREATE TABLE user_on_role(
  id INTEGER NOT NULL,
  userx_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (userx_id, role_id),
  FOREIGN KEY (userx_id) REFERENCES userx(id)ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE permission_on_role(
  id INTEGER NOT NULL,
  permission_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (permission_id, role_id),
  FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE file_storage(
  id INTEGER NOT NULL,
  filename VARCHAR(255) NOT NULL,
  extension VARCHAR(25) NOT NULL,
  displayname VARCHAR(255),
  content BYTEA,
  filepath TEXT,
  create_date TIMESTAMP NOT NULL,
  type INTEGER NOT NULL,
  status INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE token(
  id INTEGER NOT NULL,
  access_token VARCHAR(255) NOT NULL,
  refresh_token VARCHAR(255) NOT NULL,
  expire_time_access DATE NOT NULL,
  expire_time_refresh DATE NOT NULL,
  issued_at DATE NOT NULL,
  issuer TEXT,
  subject TEXT,
  not_before DATE,
  audience TEXT,
  secret TEXT,
  algorithm TEXT,
  type TEXT,
  userx_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
  UNIQUE (access_token),
  UNIQUE (refresh_token)
);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(
  id INTEGER NOT NULL,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  userx_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// 2 - ProfileDetails
CREATE TABLE profile(
  id INTEGER NOT NULL,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  middlename VARCHAR(255),
  userx_id INTEGER NOT NULL,
  is_man NUMERIC,
  datebirth DATE,
  phone VARCHAR(51),
  city_id INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-----------------------------
--------- Id - UUID ---------
-----------------------------

CREATE TABLE country(
  id UUID NOT NULL,
  caption VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (caption)
);

CREATE TABLE city(
  id UUID NOT NULL,
  caption VARCHAR(255) NOT NULL,
  country_id UUID NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (caption, country_id),
  FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE role(
  id UUID NOT NULL,
  code VARCHAR(255) NOT NULL,
  description TEXT,
  PRIMARY KEY (id),
  UNIQUE (code)
);

CREATE TABLE userx(
  id UUID NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  passwordencode TEXT NOT NULL,
  is_blocked BOOLEAN NOT NULL,
  is_deleted BOOLEAN NOT NULL,
  is_confirm_email BOOLEAN NOT NULL,
  salt VARCHAR(255),
  secret VARCHAR(255),
  PRIMARY KEY (id),
  UNIQUE (username),
  UNIQUE (email)
);

CREATE TABLE user_on_role(
  id UUID NOT NULL,
  userx_id UUID NOT NULL,
  role_id UUID NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (userx_id, role_id),
  FOREIGN KEY (userx_id) REFERENCES userx(id)ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE permission_on_role(
  id UUID NOT NULL,
  permission_id INTEGER NOT NULL,
  role_id UUID NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (permission_id, role_id),
  FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE file_storage(
  id UUID NOT NULL,
  filename VARCHAR(255) NOT NULL,
  extension VARCHAR(25) NOT NULL,
  displayname VARCHAR(255),
  content BYTEA,
  filepath TEXT,
  create_date TIMESTAMP NOT NULL,
  type INTEGER NOT NULL,
  status INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE token(
  id UUID NOT NULL,
  access_token VARCHAR(255) NOT NULL,
  refresh_token VARCHAR(255) NOT NULL,
  expire_time_access DATE NOT NULL,
  expire_time_refresh DATE NOT NULL,
  issued_at DATE NOT NULL,
  issuer TEXT,
  subject TEXT,
  not_before DATE,
  audience TEXT,
  secret TEXT,
  algorithm TEXT,
  type TEXT,
  userx_id UUID NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
  UNIQUE (access_token),
  UNIQUE (refresh_token)
);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(
  id UUID NOT NULL,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  userx_id UUID NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// 2 - ProfileDetails
CREATE TABLE profile(
  id UUID NOT NULL,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  middlename VARCHAR(255),
  userx_id UUID NOT NULL,
  is_man NUMERIC,
  datebirth DATE,
  phone VARCHAR(51),
  city_id UUID,
  PRIMARY KEY (id),
  FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE
);
