import java.io.*;

class Input2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void input(String subject){
		p(subject+ ": ");
		try{
			String line = br.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i<0 || i>100)  {
				pln("점수의 유효범위는 0~100점 사이예요");
				input(subject);
			}else {
				pln("유효한 입력점수: " + i);
			}
		}catch(IOException ie){
		}catch(NumberFormatException ne){
			pln("헉.. 숫자의 형태가 아니네요ㅠㅠ");
			input(subject);
		}
	}
	void p(String str){
		System.out.print(str);	
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("사용예> java Input1 국어 영어 수학");
			return;
		}

		Input2 a = new Input2();
		for(int i=0; i<args.length; i++){
			a.input(args[i]);
		}
	}
}
