package arya.phonebook.dao.h2.commands;

public interface ICommands {
	//------------------------------------CREATE_TABLE----------------------------------------------
	public static final String CREATE_TABLE_USER = "create table if not exists USERS (ID int auto_increment primary key,FIRSTNAME varchar(255),LASTNAME varchar(255),PHOTO binary,EMAIL varchar(255),NATIONAL_ID varchar(255),ADDRESS varchar(255),BIRTHDAY date,PHONE varchar(255),GENDER varchar(255));";
	public static final String CREATE_TABLE_USERNAMEPASSWORD = "create table if not exists USERNAME_PASSWORDS (ID int auto_increment primary key,USERNAME varchar(255),PASSWORD varchar(255));";
	public static final String CREATE_TABLE_LOGIN = "create table if not exists LOGINS (ID int auto_increment primary key,ID_USERS int,ID_USERNAME_PASSWORDS int,foreign key (ID_USERS) REFERENCES USERS(ID),foreign key (ID_USERNAME_PASSWORDS) REFERENCES USERNAME_PASSWORDS(ID));";
	public static final String CREATE_TABLE_CONTACT = "create table if not exists CONTACTS (ID int auto_increment primary key,TYPE varchar(255),PHONE varchar(255),ADDRESS varchar(255),DESCRIPTION varchar(255));";
	public static final String CREATE_TABLE_EMAILDETAIL = "create table if not exists EMAIL_DETAILS (ID int auto_increment primary key,EMAIL varchar(255),DESCRIPTION varchar(255));";
	public static final String CREATE_TABLE_USERCONTACT = "create table if not exists USER_CONTACTS (ID int auto_increment primary key,FIRSTNAME varchar(255),LASTNAME varchar(255),BIRTHDAY date,DESCRIPTION varchar(255),ID_CONTACTS int,ID_EMAIL_DETAILS int,foreign key (ID_CONTACTS) REFERENCES CONTACTS (ID),foreign key (ID_EMAUL_DETAILS) REFERENCES EMAIL_DETAILS (ID));";
	public static final String CREATE_TABLE_GROUP = "create table if not exists GROUPS (ID int auto_increment primary key,NAME varchar(255),DESCRIPTION varchar(255));";
	public static final String CREATE_TABLE_RELLOGINPHONEBOOK = "create table if not exists REL_LOGIN_PHONEBOOKS (ID int auto_increment primary key,ID_LOGINS int,foreign key (ID_LOGINS) REFERENCES LOGINS (ID));";
	public static final String CREATE_TABLE_PHONEBOOK = "create table if not exists PHONEBOOKS (ID int auto_increment primary key,ID_REL_LOGIN_PHONEBOOKS int,ID_USER_CONTACTS int,ID_GROUPS int,foreign key (ID_REL_LOGIN_PHONEBOOKS) REFERENCES REL_LOGIN_PHONEBOOK (ID),foreign key (ID_USER_CONTACTS) REFERENCES USER_CONTACTS (ID),foreign key (ID_GROUPS) REFERENCES GROUPS (ID));";
	//------------------------------------INSERT_INTO----------------------------------------------
	public static final String INSERT_USER = "insert into USERS (FIRSTNAME, LASTNAME, PHOTO, EMAIL, NATIONAL_ID, ADDRESS, BIRTHDAY, PHONE, GENDER) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String INSERT_USERNAMEPASSWORD = "insert into USERNAME_PASSWORDS (USERNAME, PASSWORD) values (?, ?);";
	public static final String INSERT_LOGIN = "insert into LOGINS (ID_USERS, ID_USERNAME_PASSWORDS) values (?, ?);";
	public static final String INSERT_CONTACT = "insert into CONTACTS (TYPE, PHONE, ADDRESS, DESCRIPTION) values (?, ?, ?, ?);";
	public static final String INSERT_EMAILDETAIL = "insert into EMAIL_DETAILS (EMAIL, DESCRIPTION) values (?, ?);";
	public static final String INSERT_USERCONTACT = "insert into USER_CONTACTS (FIRSTNAME, LASTNAME, BIRTHDAY, DESCRIPTION, ID_CONTACTS, ID_EMAIL_DETAILS) values (?, ?, ?, ?, ?, ?);";
	public static final String INSERT_GROUP = "insert into GROUPS (NAME, DESCRIPTION) values (?, ?);";
	public static final String INSERT_RELLOGINPHONEBOOK = "insert into REL_LOGIN_PHONEBOOKS (ID_LOGINS) values (?);";
	public static final String INSERT_PHONEBOOK = "insert into PHONEBOOKS (ID_REL_LOGIN_PHONEBOOKS, ID_USER_CONTACTS, ID_GROUPS) values (?, ?, ?);";
	//------------------------------------DELETE----------------------------------------------
	
	//------------------------------------UPDATE----------------------------------------------
	
	//------------------------------------SELECT----------------------------------------------
	
	//------------------------------------SELECT_LIST----------------------------------------------
	

}
