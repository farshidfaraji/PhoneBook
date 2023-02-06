package arya.phonebook.dao.h2.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arya.phonebook.model.abstracts.Entity;

public class GenerateCommand<E extends Entity> {
	private Class<E> entity;
	
	public GenerateCommand(Class<E> entity) {
		this.entity = entity;
	}
	
	public String createTable() {
		return new CreateTable().createQuery(entity);
	}
	public String insertTable() {
		return new InsertTable().createQuery(entity);
	}
	public String deleteTable() {
		return new DeleteTable().createQuery(entity);
	}
	public String updateTable() {
		return new UpdateTable().createQuery(entity);
	}
	public String select() {
		return new Select().createQuery(entity);
	}
	public String selectAll() {
		return new SelectAll().createQuery(entity);
	}
	private class CreateTable {
		private List<String> foreignKeys = new ArrayList<>();
		
		public String createQuery(Class<E> entity) {
			Map<String, String> dataType = new HashMap<>();
			dataType.put("int", "INT");
			dataType.put("string", "VARCHAR(255)");
			dataType.put("calendar", "DATE");
			dataType.put("byte[]", "BINARY");
			
			StringBuffer stringBuffer = new StringBuffer("CREATE TABLE IF NOT EXISTS ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S (ID INT AUTO_INCREMENT PRIMARY KEY, ");
			Arrays.stream(entity.getDeclaredFields()).forEach(field -> {
			if (field.getType().isEnum()) {
				stringBuffer.append(field.getType().getSimpleName().toUpperCase()+ " VARCHAR(255), ");
			}else {
				String dataTypeField = dataType.get(field.getType().getSimpleName().toLowerCase());
				if (dataTypeField == null) {
					dataTypeField = innerEntity(field.getType().getSimpleName().toUpperCase());
					System.out.println(dataTypeField);
				} else {
					stringBuffer.append(field.getName().toUpperCase());
					stringBuffer.append(" ");
				}
				stringBuffer.append(dataTypeField);
				if (!(dataTypeField == null || dataTypeField.isEmpty())) {
					stringBuffer.append(", ");
				}
			}
			});
			stringBuffer.delete(stringBuffer.lastIndexOf(", "), stringBuffer.length());
			stringBuffer.append(");");
			
			return stringBuffer.toString();
		}
		
		public String innerEntity(String entity) {
			if (entity.equalsIgnoreCase("list")) {
				return "";
			} else {
				String field = "ID_" + entity;
				foreignKeys.add("FOREIGN KEY (" + field + ") REFERENCES " + entity + "S(ID)");
				return field + " INT";
			}
		}
	}
	private class InsertTable{
		
		public String createQuery(Class<E> entity) {
			StringBuffer stringBuffer = new StringBuffer("INSERT INTO ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S (");
			StringBuffer values = new StringBuffer();
			Arrays.stream(entity.getDeclaredFields()).forEach(field -> {
				String typeField = field.getType().getSimpleName().toLowerCase();
				String nameField = field.getName().toLowerCase();
				if (typeField.equals(nameField) || typeField.equals("list")) {
					stringBuffer.append("ID_");
					stringBuffer.append(field.getName().toUpperCase());
					stringBuffer.append(", ");
				}else {
					stringBuffer.append(field.getName().toUpperCase());
					stringBuffer.append(", ");
				}
				values.append("?");
				values.append(", ");
			});
			stringBuffer.delete(stringBuffer.lastIndexOf(", "), stringBuffer.length());
			stringBuffer.append(") VALUES (");
			values.delete(values.lastIndexOf(", "), values.length());
			stringBuffer.append(values.toString());
			stringBuffer.append(");");
			return stringBuffer.toString();
		}
		
	}
	private class DeleteTable{
		
		public String createQuery(Class<E> entity) {
			
			StringBuffer stringBuffer = new StringBuffer("DELETE FROM ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S WHERE (id = ?);");
			return stringBuffer.toString();
		}
	}
	private class UpdateTable{
		public String createQuery(Class<E> entity) {
			StringBuffer stringBuffer = new StringBuffer("UPDATE ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S SET ");
			Arrays.stream(entity.getDeclaredFields()).forEach(field -> {
				String typeField = field.getType().getSimpleName().toLowerCase();
				String nameField = field.getName().toLowerCase();
				if (typeField.equals(nameField) || (typeField.equals("list"))) {
					stringBuffer.append("ID_");
					stringBuffer.append(field.getName().toUpperCase());
					stringBuffer.append(" = ? ");
					stringBuffer.append(", ");
				} else {
					stringBuffer.append(field.getName().toUpperCase());
					stringBuffer.append(" = ? ");
					stringBuffer.append(", ");
				}
			});
			stringBuffer.delete(stringBuffer.lastIndexOf(", "), stringBuffer.length());
			stringBuffer.append("WHERE (id = ?);");
			return stringBuffer.toString();
		}
	}
	private class Select{
		public String createQuery(Class<E> entity) {
			StringBuffer stringBuffer = new StringBuffer("SELECT * FROM ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S WHERE (id = ?);");
			return stringBuffer.toString();
		}
	}
	private class SelectAll{
		public String createQuery(Class<E> entity) {
			StringBuffer stringBuffer = new StringBuffer("SELECT * FROM ");
			stringBuffer.append(entity.getSimpleName().toUpperCase());
			stringBuffer.append("S;");
			return stringBuffer.toString();
		}
	}
	
}
