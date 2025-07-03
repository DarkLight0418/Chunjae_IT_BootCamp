class Figure1 {
    void draw() {
        System.out.println("도형을 그리다");
    }
}

class Circle extends Figure1 {
    void draw() {
        System.out.println("원형을 그리다");
    }
}
class Rectangle extends Figure1 {
    void draw() {
        System.out.println("직사각형을 그리다");
    }
}

class Tri extends Figure1 {
    void draw() {
        System.out.println("삼각형을 그리다");
    }
}


class B {
    public static void main(String[] args) {
        Figure1 f1 = new Circle();
        Figure1 f2 = new Rectangle();
        Figure1 f3 = new Tri();

        f1.draw();
        f2.draw();
        f3.draw();
    }
}


class Figure2 {
    void draw() {
        System.out.println("도형 그리기");
    }
}

class Circle2 extends Figure2 {
    void draw() {
        System.out.println("원형을 그리다");
    }
}

class Rectangle2 extends Figure2 {
    void draw() {
        System.out.println("직사각형을 그리다");
    }
}

class Tri2 extends Figure2 {
    void draw() {
        System.out.println("삼각형을 그리다");
    }
}

class B2 {
    public static void main(String[] args) {
        Figure2 f1 = new Circle2();
        Figure2 f2 = new Rectangle2();
        Figure2 f3 = new Tri2();

        f1.draw();
        f2.draw();
        f3.draw();
    }
}