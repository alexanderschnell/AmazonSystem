package amazonsystem;

import java.util.ArrayList;

public class AmazonSystemUtil {

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
	
    // New utility methods
    public static boolean isValidFloat(String value) {
        try {
            float f = Float.parseFloat(value);
            return f >= 0; 
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidInt(String value) {
        try {
            int i = Integer.parseInt(value);
            return i >= 0; 
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

