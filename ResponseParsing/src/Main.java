import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // Json String 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String jsonString = "";
        while(true) {
            String line = br.readLine();
            jsonString += line;
            if(line.charAt(line.length()-1) == '}') break;

        }

        // String -> Json Object 변환
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(jsonString);

        //
        HashMap map = jsonObject;
        System.out.println(map.toString());
        String output = generateJson(map);
        System.out.println(output);
    }

    public static String generateJson(HashMap input) {
        JSONObject root = new JSONObject();
        List<String> order = new ArrayList<>();
        JSONObject columns = new JSONObject();

        Iterator i = input.keySet().iterator();
        while(i.hasNext()) {
            String key = (String)i.next(); // key 추출

            // order에 추가
            order.add(key);

            // columns에 추가
            JSONObject column = new JSONObject();
            List<JSONObject> contents = new ArrayList<>();
            JSONObject content = new JSONObject();
            content.put("key", key);
            content.put("type","string");
            contents.add(content);

            column.put("label",key);
            column.put("contents", contents);
            columns.put(key, column);


        }

        root.put("order",order);
        root.put("columns",columns);

return root.toString();
    }
}