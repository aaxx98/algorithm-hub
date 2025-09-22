class Solution {
  public int solution(int[] schedules, int[][] timelogs, int startday) {
    int answer = 0;
    for (int i = 0; i < schedules.length; i++) { // 직원 i에 대해
      int today = startday % 7;
      int schedule_minute = schedules[i] / 100 * 60 + schedules[i] % 100;
      int count = 0;
      for (int k = 0; k < 7; k++) { // k일 확인
        if (today == 0 || today == 6) {
          today = (today + 1) % 7;
          continue;
        }
        int hour = timelogs[i][k] / 100;
        int minute = timelogs[i][k] % 100;
        int total_minute = hour * 60 + minute;
        if (schedule_minute + 10 >= total_minute) {
          count++;
        }
        today = (today + 1) % 7;
      }
      if (count == 5) {
        answer++;
      }
    }
    return answer;
  }
}