/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parking.carplaterecognize;

import parking.computervision.RunOpenCV;
import java.io.File;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Bin
 */
public class CarPlateRecognize {

    public CarPlateRecognize() {

    }

    public String recognize(String image) {
        // Get api key from https://app.platerecognizer.com/start/ and replace MY_API_KEY
        String token = "6893514a5c29126ecfef4858f8a10969bf287d5f";
        String file = image;
        String plateNumber = null;
        try {
            HttpResponse<String> response = Unirest.post("https://api.platerecognizer.com/v1/plate-reader/")
                    .header("Authorization", "Token " + token)
                    .field("upload", new File(file))
                    .asString();
            System.out.println("Recognize:");
            System.out.println(response.getBody().toString());
            String jsonString = response.getBody().toString();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            if (resultsArray.length() > 0) {
                // Lấy đối tượng result đầu tiên
                JSONObject firstResult = resultsArray.getJSONObject(0);

                // Lấy giá trị của plate từ result
                plateNumber = firstResult.getString("plate");

                // Hiển thị kết quả
                System.out.println("Plate Number: " + plateNumber);
            } else {
                System.out.println("No results found.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            HttpResponse<String> response = Unirest.get("https://api.platerecognizer.com/v1/statistics/")
                    .header("Authorization", "Token " + token)
                    .asString();
            System.out.println("Usage:");
            System.out.println(response.getBody().toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return plateNumber;
    }

}
