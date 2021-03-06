/****************************************************************************
  * Code obtained and adapted from: https://ideone.com/oOVWYj
  * 
  * Program Name: StringSimilarity.java
  * 
  * Description: This program calculates the levenshtein edit distance 
  * between twwo strings and returns a number between 0 and 1 to represent 
  * how similar they are. 
  ****************************************************************************/
public class StringSimilarity {
    
    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public StringSimilarity() {
    }
    
    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        int ed = editDistance(longer, shorter);
        
        return (longerLength - ed) / (double) longerLength;
        
    }
    
    // implementation of the Levenshtein Edit Distance
    
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        
        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                                costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        
        return costs[s2.length()];
    }
    
    public static void printSimilarity(String s, String t) {
        System.out.println(String.format(
                                         "%.3f is the similarity between \"%s\" and \"%s\"", similarity(s, t), s, t));
    }

    public static void printeditDistance(String a, String t) {
      System.out.println(editDistance(a, t));
    }
    
    public static void main(String[] args) {
    }
    
}