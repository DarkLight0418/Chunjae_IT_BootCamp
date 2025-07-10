package day15_IO_Part2;

public class T {
    String str = "현영갓 60";
    String name;

    int rate;

    void cut1() {
        int idx = str.indexOf(" ");
        name = str.substring(0, idx);  // 이상 미만 (앞에 꺼 포함, 뒤에 꺼 제외)
        String rateStr = str.substring(idx);  // 이상 (시작점만 주어지고 끝까지 잘라내는 거)

        name = name.trim();
        rateStr = rateStr.trim();
        rate = Integer.parseInt(rateStr);

        show();
    }

    void cut2() {
        String items[] = str.split(" ");
        if(items.length == 2) {
            name = items[0];
            name = name.trim();
            String rateStr = items[1];
            rateStr = rateStr.trim();
            rate = Integer.parseInt(rateStr);
        }
        show();
    }

    void show() {
        System.out.println("name: " + name);
        System.out.println("rate: " + rate);
    }

    public static void main(String[] args) {
        T t1 = new T();
        //t1.cut1();
        t1.cut2();
    }
}
