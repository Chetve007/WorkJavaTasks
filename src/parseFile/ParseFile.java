package parseFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseFile {
	
	private static String fileName = "newFile.txt";
	private static String column = "0,3";
	private static int increment = 10;

	public static void main(String[] args) {
		
		ParseFile.changeColumnByNumder(fileName, column, increment);
		
	}
	
//	@When("File: в файле \"$fileName\" в колонках \"$column\" увеличили значения на \"$increment\"")
    public static void changeColumnByNumder(String fileName, String column, int increment) {
        BufferedWriter out = null;
        String filePath = "./src/parseFile/" + fileName;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String data = br.readLine();
            if (data == null) {
                throw new RuntimeException("В файле " + filePath + "нет данных для считывания");
            }
            String[] elements = data.split("\\|");
            String[] columns = column.split("[\\W]");
            Pattern p = Pattern.compile("\\d+");
            for (int i = 0; i < columns.length; i++) {
                int index = Integer.parseInt(columns[i]);
                Matcher m = p.matcher(elements[index]);
                if (m.find()) {
                    int sizeMatcher = m.group().toCharArray().length;
                    int newNum = Integer.parseInt(m.group()) + increment;
                    String numFormat = String.format("%0" + sizeMatcher + "d", newNum);
                    elements[index] = elements[index].replaceAll(m.group(), numFormat);
                } else
                    elements[index] = elements[index] + increment;
            }
            String newLine = String.join("|", elements);

            out = new BufferedWriter(new FileWriter(filePath, false));
            out.write(newLine);
            out.flush();
            System.err.println("New line is " + newLine);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
