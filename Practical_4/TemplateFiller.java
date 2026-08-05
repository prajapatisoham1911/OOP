import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class TemplateFiller {


    public static String fillTemplate(
            String template,
            String[] names,
            String[] values) {


        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");

        Matcher matcher = pattern.matcher(template);



        StringBuilder result = new StringBuilder();



        while(matcher.find()) {


            String key = matcher.group(1);


            String replacement = "[?]";



            for(int i = 0; i < names.length; i++) {


                if(names[i].equals(key)) {

                    replacement = values[i];

                    break;

                }

            }



            matcher.appendReplacement(result, replacement);


        }


        matcher.appendTail(result);



        return result.toString();

    }

}
