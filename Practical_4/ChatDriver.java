import java.util.Scanner;


public class ChatDriver {


    public static void main(String[] args) {


        String[] logs = {

            "10:05 alice Hello there",

            "10:06 bob How are you",

            "10:07"

        };


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter keyword: ");

        String keyword = sc.nextLine();


        int count = 0;


        StringBuilder report = new StringBuilder();



        for(String line : logs) {


            String[] parts = line.split(" ", 3);



            if(parts.length < 3) {

                continue;

            }



            String time = parts[0];

            String user = parts[1];

            String message = parts[2];



            if(ChatFilter.containsKeyword(message, keyword)) {


                count++;


                report.append(time)
                      .append(" ")
                      .append(user)
                      .append(": ")
                      .append(message)
                      .append("\n");

            }

        }



        System.out.println("Matches: " + count);

        System.out.println(report);



        sc.close();

    }

}
