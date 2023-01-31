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
			stringBuffer.append("S (");
			Arrays.stream(entity.getDeclaredFields()).forEach(field -> {
			if (field.getType().isEnum()) {
				stringBuffer.append(field.getType().getSimpleName().toUpperCase()+ " VARCHAR(255), ");
			}else {
				String dataTypeField = dataType.get(field.getType().getSimpleName().toLowerCase());
				if (dataTypeField == null) {
					dataTypeField = innerEntity(field.getType().getSimpleName().toUpperCase());
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
			
			stringBuffer.append("ID INT AUTO_INCREMENT PRIMARY KEY);");
			
			return stringBuffer.toString();
		}
		
		public String innerEntity(String entity) {
			if (entity.equalsIgnoreCase("list")) {
				return "";
			} else if (entity.equalsIgnoreCase("list")) {
				return "=>Enum<";
			} else {
				String field = "ID_" + entity;
				foreignKeys.add("FOREIGN KEY (" + field + ") REFERENCES " + entity + "S(ID)");
				return field + " INT";
			}
		}
	}

}
