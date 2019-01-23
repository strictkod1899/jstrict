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

CREATE TABLE roleuser(
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
isBlocked BOOLEAN NOT NULL,
isDeleted BOOLEAN NOT NULL,
isConfirmEmail BOOLEAN NOT NULL,
PRIMARY KEY (id),
UNIQUE (username),
UNIQUE (email)
);

CREATE TABLE user_on_role(
id INTEGER NOT NULL,
userx_id INTEGER NOT NULL,
roleuser_id INTEGER NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id, roleuser_id),
FOREIGN KEY (userx_id) REFERENCES userx(id)ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(
id INTEGER NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id INTEGER NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// 2 - ProfileInfo
CREATE TABLE profile(
id INTEGER NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id INTEGER NOT NULL,
datebirth DATE,
phone VARCHAR(51),
city_id INTEGER,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта токена
// 1 - JwtToken
CREATE TABLE token(
id INTEGER NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT
);

// 2 - JwtUserToken
CREATE TABLE token(
id INTEGER NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT,
userx_id INTEGER NOT NULL,
roleuser_id INTEGER NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-----------------------------
------- Id - UUID --------
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

CREATE TABLE roleuser(
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
isBlocked BOOLEAN NOT NULL,
isDeleted BOOLEAN NOT NULL,
isConfirmEmail BOOLEAN NOT NULL,
PRIMARY KEY (id),
UNIQUE (username),
UNIQUE (email)
);

CREATE TABLE user_on_role(
id UUID NOT NULL,
userx_id UUID NOT NULL,
roleuser_id UUID NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id, roleuser_id),
FOREIGN KEY (userx_id) REFERENCES userx(id)ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(
id UUID NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id UUID NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// 2 - ProfileInfo
CREATE TABLE profile(
id UUID NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id UUID NOT NULL,
datebirth DATE,
phone VARCHAR(51),
city_id UUID,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта токена
// 1 - JwtToken
CREATE TABLE token(
id UUID NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT
);

// 2 - JwtUserToken
CREATE TABLE token(
id UUID NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT,
userx_id UUID NOT NULL,
roleuser_id UUID NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);

--------------------------
------- Id - TEXT --------
--------------------------

CREATE TABLE country(
id TEXT NOT NULL,
caption VARCHAR(255) NOT NULL,
PRIMARY KEY (id),
UNIQUE (caption)
);

CREATE TABLE city(
id TEXT NOT NULL,
caption VARCHAR(255) NOT NULL,
country_id TEXT NOT NULL,
PRIMARY KEY (id),
UNIQUE (caption, country_id),
FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE roleuser(
id TEXT NOT NULL,
code VARCHAR(255) NOT NULL,
description TEXT,
PRIMARY KEY (id),
UNIQUE (code)
);

CREATE TABLE userx(
id TEXT NOT NULL,
username VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
passwordencode TEXT NOT NULL,
isBlocked BOOLEAN NOT NULL,
isDeleted BOOLEAN NOT NULL,
isConfirmEmail BOOLEAN NOT NULL,
PRIMARY KEY (id),
UNIQUE (username),
UNIQUE (email)
);

CREATE TABLE user_on_role(
id TEXT NOT NULL,
userx_id TEXT NOT NULL,
roleuser_id TEXT NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id, roleuser_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта профиля
// 1 - Profile
CREATE TABLE profile(
id TEXT NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id TEXT NOT NULL,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// 2 - ProfileInfo
CREATE TABLE profile(
id TEXT NOT NULL,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
middlename VARCHAR(255),
userx_id TEXT NOT NULL,
datebirth DATE,
phone VARCHAR(51),
city_id TEXT,
PRIMARY KEY (id),
UNIQUE (userx_id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (city_id) REFERENCES city(id) ON UPDATE CASCADE ON DELETE CASCADE
);

// Два варианта токена
// 1 - JwtToken
CREATE TABLE token(
id TEXT NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT
);

// 2 - JwtUserToken
CREATE TABLE token(
id TEXT NOT NULL,
accessToken TEXT NOT NULL,
refreshToken TEXT NOT NULL,
expireTimeAccess DATE NOT NULL,
expireTimeRefresh DATE NOT NULL,
issuedAt DATE NOT NULL,
issuer TEXT,
subject TEXT,
notBefore DATE,
audience TEXT,
secret TEXT,
algorithm TEXT,
type TEXT,
userx_id TEXT NOT NULL,
roleuser_id TEXT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (userx_id) REFERENCES userx(id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (roleuser_id) REFERENCES roleuser(id) ON UPDATE CASCADE ON DELETE CASCADE
);
