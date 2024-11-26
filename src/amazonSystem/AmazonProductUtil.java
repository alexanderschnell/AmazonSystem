package amazonsystem;

import java.util.ArrayList;

public class AmazonProductUtil {

	public static float convertStrToFloat(String nextLine) {
		return Float.parseFloat(nextLine);
	}

	public static String[] lineReader(String line) {

		ArrayList<String> fields = new ArrayList<>();

		StringBuilder currentField = new StringBuilder();

		boolean inQuotes = false;

		for (char c : line.toCharArray()) {
			if (c == '"') {
				inQuotes = !inQuotes;
				currentField.append(c);
			} else if (c == ',' && !inQuotes) {
				fields.add(currentField.toString().trim());
				currentField = new StringBuilder();
			} else {
				currentField.append(c);
			}
		}

		fields.add(currentField.toString().trim());

		for (int i = 0; i < fields.size(); i++) {
			String field = fields.get(i);
			if (field.startsWith("\"") && field.endsWith("\"")) {
				field = field.substring(1, field.length() - 1);
			}

			   if ((i == 8 || i == 9) && (field.contains("₹") || field.contains("â") || field.contains("?"))) {
		            field = field.replaceAll("[^0-9.]", "");
			}
			
			fields.set(i, field);
		}

		return fields.toArray(new String[0]);

	}
}
