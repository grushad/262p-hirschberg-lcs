package project6;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class LCS {

  public String find(String A, String B) {
    // Implement the Hirschberg LCS algorithm
    int m = A.length(), n = B.length();
    if (m == 0)
      return "";
    if (m == 1) {
      for (int j = 0; j < n; j++) {
        if (A.charAt(0) == B.charAt(j)) {          
          return A;
        }        
      }
      return "";
    }
    int i = m / 2;
    String a1 = A.substring(0, i);
    String a2 = A.substring(i);

    int[] L1 = func(a1, B);
    int[] L2 = func(new StringBuilder(a2).reverse().toString(), new StringBuilder(B).reverse().toString());
    int M = 0, k = 0;
    for (int j = 0; j <= n; j++) {
      if (L1[j] + L2[n - j] > M){
        M = L1[j] + L2[n - j];
        k = j;
      }
    }

    String b1 = B.substring(0, k);
    String b2 = B.substring(k);
    return find(a1, b1) + find(a2, b2);
  }
  
  int[] func(String A, String B) {
    int m = A.length(), n = B.length();

    int[] prev = new int[n + 1];
    int[] curr = new int[n + 1];
    for(int i = 1; i <= m; i++){
      for(int j = 1; j <= n; j++){
        if(A.charAt(i - 1) == B.charAt(j - 1))
          curr[j] = 1 + prev[j - 1];
        else
          curr[j] = 0 + Math.max(prev[j], curr[j - 1]);
      }
      prev = (int[])(curr.clone());
    }
    return prev;
  }
}
