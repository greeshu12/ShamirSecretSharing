import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShamirSecretSharing {

    public static void main(String[] args) {
        String jsonInput = "{ \"keys\": { \"n\": 4, \"k\": 3 }, \"1\": { \"base\": \"10\", \"value\": \"4\" }, \"2\": { \"base\": \"2\", \"value\": \"111\" }, \"3\": { \"base\": \"10\", \"value\": \"12\" }, \"6\": { \"base\": \"4\", \"value\": \"213\" } }";

        JSONObject jsonObject = new JSONObject(jsonInput);
        int n = jsonObject.getJSONObject("keys").getInt("n");
        int k = jsonObject.getJSONObject("keys").getInt("k");

        // Collect points from the JSON
        Map<Integer, Integer> points = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            if (!key.equals("keys")) {
                int base = Integer.parseInt(jsonObject.getJSONObject(key).getString("base"));
                int value = Integer.parseInt(jsonObject.getJSONObject(key).getString("value"));
                points.put(base, value);
            }
        }

        // Calculate the constant term c of the polynomial
        int c = calculateConstantTerm(points, k);
        System.out.println("The constant term c of the polynomial is: " + c);
    }

    private static int calculateConstantTerm(Map<Integer, Integer> points, int k) {
        int[] x = new int[k];
        int[] y = new int[k];
        int index = 0;

        // Extract the first k points
        for (Map.Entry<Integer, Integer> entry : points.entrySet()) {
            if (index < k) {
                x[index] = entry.getKey();
                y[index] = entry.getValue();
                index++;
            } else {
                break;
            }
        }

        // Calculate the constant term using Lagrange interpolation
        int c = 0;
        for (int i = 0; i < k; i++) {
            int term = y[i];
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    term *= (0 - x[j]) / (x[i] - x[j]); // x=0 for calculating c
                }
            }
            c += term;
        }

        return c;
    }
}
