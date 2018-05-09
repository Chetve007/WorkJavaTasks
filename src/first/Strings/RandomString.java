package first.Strings;

public class RandomString {

    public static String upperRUSWords = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static String upperENGWords = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String digits = "0123456789";
    public static String symbols = ".,?!@#$%^&*-+=_`~";

    public static String getRandomValue(int stringLength, String typeValue) {
        switch (typeValue) {
            case "Numbers":
                return randomCreator(stringLength, digits);
            case "RussianLetters":
                return randomCreator(stringLength, upperRUSWords + upperRUSWords.toLowerCase());
            case "EnglishLetters":
                return randomCreator(stringLength, upperENGWords + upperENGWords.toLowerCase());
            case "SpecialSymbols":
                return randomCreator(stringLength, symbols);
            case "HEX":
                if(stringLength > 16)
                    throw new RuntimeException("Длинна строки не может превышать 16 символов");
                return randomCreator(stringLength, digits + "abcdef");
            case "CapitalEnglishLetters":
                return randomCreator(stringLength, upperENGWords);
            case "CapitalEnglishLettersAndNumbers":
                return randomCreator(stringLength, upperENGWords + digits);
        }
        throw new RuntimeException("Ошибка, введен неверный Тип");
    }

    private static String randomCreator(int stringLength, String line) {
        String variable = "";
        char[] c = line.toCharArray();
        for(int i = 0; i < stringLength; i++) {
            int rnd = (int)(Math.random() * c.length);
            variable += line.charAt(rnd);
        }
        return variable;
    }

    public static void main(String[] args) {

        System.out.println(getRandomValue(15, "HEX"));
    }
}
