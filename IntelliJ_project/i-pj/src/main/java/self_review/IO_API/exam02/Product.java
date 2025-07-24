package self_review.IO_API.exam02;

/**
 * Product 클래스는 상품 정보를 저장하고 관리하는 객체입니다.
 * 필드로는 상품번호(pno), 이름(name), 가격(price), 재고(stock)를 가지고 있으며,
 * 각 필드에 접근하기 위한 getter와 setter 메서드를 제공합니다.
 */
public class Product {
    // 상품 번호를 저장하는 필드
    private int pno;
    // 상품 이름을 저장하는 필드
    private String name;
    // 상품 가격을 저장하는 필드
    private int price;
    // 상품 재고를 저장하는 필드
    private int stock;

    // Getter: 외부에서 pno 값을 읽기 위한 메서드
    public int getPno() {
        return pno;
    }

    // Setter: 외부에서 pno 값을 설정하기 위한 메서드
    // this.pno는 인스턴스 변수, pno는 메서드의 매개변수
    // this를 붙여서 둘을 구분함
    public void setPno(int pno) {
        this.pno = pno;
    }

    // Getter: 외부에서 name 값을 읽기 위한 메서드
    public String getName() {
        return name;
    }

    // Setter: 외부에서 name 값을 설정하기 위한 메서드
    // this.name은 멤버 필드, name은 전달받은 값
    public void setName(String name) {
        this.name = name;
    }

    // Getter: 외부에서 price 값을 읽기 위한 메서드
    public int getPrice() {
        return price;
    }

    // Setter: 외부에서 price 값을 설정하기 위한 메서드
    public void setPrice(int price) {
        this.price = price;
    }

    // Getter: 외부에서 stock 값을 읽기 위한 메서드
    public int getStock() {
        return stock;
    }

    // Setter: 외부에서 stock 값을 설정하기 위한 메서드
    public void setStock(int stock) {
        this.stock = stock;
    }
}
