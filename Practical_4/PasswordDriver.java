public class PasswordDriver {

    public static void main(String[] args) {

        String[] passwords = {
            "abc",
            "abcdef12",
            "Abcd1234!"
        };


        for (String pw : passwords) {

            PasswordChecker.checkRules(pw);

        }
    }
}
