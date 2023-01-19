create table if not exists USERS (
ID int auto_increment primary key,
FIRSTNAME varchar(255),
LASTNAME varchar(255),
PHOTO binary,
EMAIL varchar(255),
NATIONAL_ID varchar(255),
ADDRESS varchar(255),
BIRTHDAY date,
PHONE varchar(255),
GENDER varchar(255),
);

create table if not exists USERNAME_PASSWORDS (
ID int auto_increment primary key,
USERNAME varchar(255),
PASSWORD varchar(255)
);

create table if not exists LOGINS (
ID int auto_increment primary key,
ID_USERS int;
ID_USERNAMEPASSWORDS int,
foreign key (ID_USERS) REFERENCES USERS(ID),
foreign key (ID_USERNAMEPASSWORDS) REFERENCES USERNAME_PASSWORDS(ID),
);

create table if not exists GROUPS (
ID int auto_increment primary key,
NAME varchar(255),
DESCRIPTION varchar(255)
);

create table if not exists CONTACTS (
ID int auto_increment primary key,
TYPE varchar(255),
PHONE varchar(255),
ADDRESS varchar(255),
DESCRIPTION varchar(255)
);


create table if not exists EMAIL_DETAILS (
ID int auto_increment primary key,
EMAIL varchar(255),
DESCRIPTION varchar(255)
);

create table if not exists USER_CONTACTS (
ID int auto_increment primary key,
FIRSTNAME varchar(255),
LASTNAME varchar(255),
BIRTHDAY date,
DESCRIPTION varchar(255)
ID_CONTACTS int,
ID_EMAIL_DETAILS int,
foreign key (ID_CONTACTS) REFERENCES CONTACTS (ID),
foreign key (ID_EMAUL_DETAILS) REFERENCES EMAIL_DETAILS (ID)
);

create table if not exists REL_LOGIN_PHONEBOOKS (
ID int auto_increment primary key,
ID_LOGINS int,
foreign key (ID_LOGINS) REFERENCES LOGINS (ID)
);

create table if not exists PHONEBOOKS (
ID int auto_increment primary key,
ID_REL_LOGIN_PHONEBOOKS int,
ID_USER_CONTACTS int,
ID_GROUPS int,
foreign key (ID_REL_LOGIN_PHONEBOOKS) REFERENCES REL_LOGIN_PHONEBOOK (ID),
foreign key (ID_USER_CONTACTS) REFERENCES USER_CONTACTS (ID),
foreign key (ID_GROUPS) REFERENCES GROUPS (ID)
);
