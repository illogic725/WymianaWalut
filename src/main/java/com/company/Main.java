package com.company;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import java.util.Scanner;

public class Main {

    public static void main(String [] args) {
        String inline = "";

        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);

            if (responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                System.out.println("\nJSON Response in String format");
                System.out.println(inline);
                sc.close();
            }
            System.out.println(inline);
            JSONObject obj = new JSONObject(inline);
            Scanner scanner = new Scanner(System.in);

            while(true) {
                System.out.println("Podaj walutę");
                System.out.println("Aby wyjść z programu wpisz exit");
                String code = scanner.nextLine();
                if (code.equals("exit")) {
                    break;
                } else {
                    try {
                        Number usd = obj.getJSONObject("rates").getNumber(code.toUpperCase());
                        System.out.println(usd);
                    } catch (Exception e) {
                        System.out.println("Niepoprawny kod waluty");

                    }
                }

            }
            conn.disconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}









