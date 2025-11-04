import java.util.HashMap;

class Solution {

  public String solution(String[] participant, String[] completion) {
    HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
    for (String p : participant) {
      hashmap.merge(p, 1, Integer::sum);
    }
    for (String c : completion) {
      if (hashmap.get(c) == 1) {
        hashmap.remove(c);
      } else {
        hashmap.merge(c, -1, Integer::sum);
      }
    }
    for (String k : hashmap.keySet()) {
      return k;
    }
    return "";
  }
}