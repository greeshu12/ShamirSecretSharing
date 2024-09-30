import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class ShamirSecretSharing {
   public ShamirSecretSharing() {
   }

   public static void main(String[] var0) {
      String var1 = "{ \"keys\": { \"n\": 4, \"k\": 3 }, \"1\": { \"base\": \"10\", \"value\": \"4\" }, \"2\": { \"base\": \"2\", \"value\": \"111\" }, \"3\": { \"base\": \"10\", \"value\": \"12\" }, \"6\": { \"base\": \"4\", \"value\": \"213\" } }";
      JSONObject var2 = new JSONObject(var1);
      int var3 = var2.getJSONObject("keys").getInt("n");
      int var4 = var2.getJSONObject("keys").getInt("k");
      HashMap var5 = new HashMap();
      Iterator var6 = var2.keySet().iterator();

      while(var6.hasNext()) {
         String var7 = (String)var6.next();
         if (!var7.equals("keys")) {
            int var8 = Integer.parseInt(var2.getJSONObject(var7).getString("base"));
            int var9 = Integer.parseInt(var2.getJSONObject(var7).getString("value"));
            var5.put(var8, var9);
         }
      }

      int var10 = calculateConstantTerm(var5, var4);
      System.out.println("The constant term c of the polynomial is: " + var10);
   }

   private static int calculateConstantTerm(Map<Integer, Integer> var0, int var1) {
      int[] var2 = new int[var1];
      int[] var3 = new int[var1];
      int var4 = 0;

      for(Iterator var5 = var0.entrySet().iterator(); var5.hasNext(); ++var4) {
         Map.Entry var6 = (Map.Entry)var5.next();
         if (var4 >= var1) {
            break;
         }

         var2[var4] = (Integer)var6.getKey();
         var3[var4] = (Integer)var6.getValue();
      }

      int var9 = 0;

      for(int var10 = 0; var10 < var1; ++var10) {
         int var7 = var3[var10];

         for(int var8 = 0; var8 < var1; ++var8) {
            if (var10 != var8) {
               var7 *= (0 - var2[var8]) / (var2[var10] - var2[var8]);
            }
         }

         var9 += var7;
      }

      return var9;
   }
}
