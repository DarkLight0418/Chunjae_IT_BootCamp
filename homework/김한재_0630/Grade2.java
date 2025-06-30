import java.util.*;

class Grade2 {
    int gradeTotal = 0;
    int average = 0;
    
    Map<String, Integer> gradeScores = new HashMap<>();

    int getTotal(int x) {
        gradeTotal += x;
        System.out.println("총점 : " +  gradeTotal);
        return gradeTotal;
    }

    void getAverage(int total, int subjectNum) {
        average = total / subjectNum; 
    }
    
    public static void main(String[] args) {
        Grade2 g = new Grade2();
        
        g.getTotal(0);
    }
}