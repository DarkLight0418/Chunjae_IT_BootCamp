package soo;
/**
	�ڹ� ����� ���� Ŭ����
	@author Soo
	@version 1.0
*/
public class P
{
	/**
		PŬ������ ������ 
	*/
	public P(){
	}
	/**
		��� �� �ٹٲ� ���� �ʴ� �޼ҵ�  
		@param str ����� ���ڿ�
	*/
	public static void p(String str){
		System.out.print(str);
	}
	/**
		��� �� �ٹٲ� �ϴ� �޼ҵ�
		@param str ����� ���ڿ�
	*/
	public static void pln(String str){
		System.out.println(str);
	}
}

/*
	(1) ������ 
		> javac -d . P.java
	(2) Ŭ�������� ���� 
		> jar -cvf p.jar soo
	(3) �������� 
		> javadoc -d doc P.java
*/