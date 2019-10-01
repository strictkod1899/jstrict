CREATE UNIQUE INDEX i_country_id ON country (id);
CREATE UNIQUE INDEX i_country_caption ON country (caption);

CREATE UNIQUE INDEX i_city_id ON city (id);
CREATE INDEX i_city_caption ON city (caption);
CREATE INDEX i_city_country_id ON city (country_id);
CREATE UNIQUE INDEX i_city_caption_country_id ON city (caption, country_id);

CREATE UNIQUE INDEX i_role_id ON role (id);
CREATE UNIQUE INDEX i_role_code ON role (code);

CREATE UNIQUE INDEX i_userx_id ON userx (id);
CREATE UNIQUE INDEX i_userx_username ON userx (username);
CREATE UNIQUE INDEX i_userx_email ON userx (email);

CREATE UNIQUE INDEX i_user_on_role_id ON user_on_role (id);
CREATE INDEX i_user_on_role_userx_id ON user_on_role (userx_id);
CREATE INDEX i_user_on_role_role_id ON user_on_role (role_id);
CREATE UNIQUE INDEX i_user_on_role_group ON user_on_role (userx_id, role_id);

CREATE UNIQUE INDEX i_permission_on_role_id ON permission_on_role (id);
CREATE INDEX i_permission_on_role_userx_id ON permission_on_role (permission_id);
CREATE INDEX i_permission_on_role_role_id ON permission_on_role (role_id);
CREATE UNIQUE INDEX i_permission_on_role_group ON permission_on_role (permission_id, role_id);

CREATE UNIQUE INDEX i_file_storage_id ON file_storage (id);
CREATE INDEX i_file_storage_filename ON file_storage (filename);
CREATE INDEX i_file_storage_extension ON file_storage (extension);
CREATE INDEX i_file_storage_filename_extension ON file_storage (filename, extension);
CREATE INDEX i_file_storage_displayname ON file_storage (displayname);
CREATE INDEX i_file_storage_displayname_extension ON file_storage (displayname, extension);
CREATE INDEX i_file_storage_type ON file_storage (type);
CREATE INDEX i_file_storage_status ON file_storage (status);
CREATE INDEX i_file_storage_create_date ON file_storage (create_date);

CREATE UNIQUE INDEX i_profile_id ON profile (id);
CREATE INDEX i_profile_userx_id ON profile (userx_id);
CREATE INDEX i_profile_name ON profile (name);
CREATE INDEX i_profile_surname ON profile (surname);
CREATE INDEX i_profile_middlename ON profile (middlename);
CREATE INDEX i_profile_fio ON profile (name, surname, middlename);

CREATE UNIQUE INDEX i_token_id ON token (id);
CREATE UNIQUE INDEX i_token_access_token ON token (access_token);
CREATE UNIQUE INDEX i_token_refresh_token ON token (refresh_token);
CREATE INDEX i_token_userx_id ON token (userx_id);
