class C {
    void m1(){
        char c = 'A';
        int i = 1;
        if(c == 'A'){
            System.out.println("1");
            if(i==0){
                System.out.println("1-1");
            }else{
                System.out.println("1-2");
            }
        }else{
            System.out.println("2");
        }
    }

    void m2(){ 
        char c = 'A';
        int i = 1;

		switch(c){
			case 'A': 
				System.out.println("1");
				switch(i) {
					case 0: System.out.println("1-1"); break;
					default: System.out.println("1-2");    
				}
				break;
				default: System.out.println("2");
			}
		}

		void p(String str) {
			System.out.println(str);
		}
		public static void main(String[] args) {
			C c = new C();
			//c.m1();
			c.m2();
	}
}