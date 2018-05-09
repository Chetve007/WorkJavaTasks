package first.IOTask;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IODate {
	
	public static String line = "Hello world 06.04.2018!";

	public static void main(String[] args) throws IOException, ParseException {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        File input = new File("src/test/input");
            if(!input.exists())
                input.mkdirs();

        try {
            fos = new FileOutputStream("src/test/input/in.txt");
            byte[] bytes = line.getBytes();
            fos.write(bytes);

            File output = new File("src/test/output");
            if(!output.exists())
                output.mkdirs();
            fos = new FileOutputStream("src/test/output/out.txt");
            int numeric;
            fis = new FileInputStream("src/test/input/in.txt");
            while((numeric = fis.read()) != -1) {
                fos.write(numeric);
            }

            line = "Hello world " + createDate() + "!";
            System.out.println(line);

            fos = new FileOutputStream("src/test/output/out.txt");
            byte[] bbb = line.getBytes();
            fos.write(bbb);

        } finally {
            if (fos != null)
                fos.close();
            if (fis != null)
                fis.close();
        }
    }

    public static String regExString() {
        Pattern p = Pattern.compile("\\d{2}[.\\\\/-]\\d{2}[.\\\\/-]\\d{4}");
        Matcher m = p.matcher(line);
        String stringDate = null;
        while(m.find()) {
            stringDate = line.substring(m.start(), m.end());
            System.out.println(stringDate);
        }
        return stringDate;
    }

    public static String createDate() throws ParseException {
        SimpleDateFormat today = new SimpleDateFormat("dd.MM.yyyy");
        Date docDate = today.parse(regExString());
        Calendar calc = Calendar.getInstance();
        calc.setTime(docDate);
        calc.add(Calendar.DAY_OF_MONTH, 20);
        Date todayPlusSomeDays = calc.getTime();
        return today.format(todayPlusSomeDays);
    }
}

