package khj.ct.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import khj.ct.domain.Human;
import khj.ct.domain.HumanList;
import khj.ct.domain.Todo;
import java.util.Date;

@Controller
@RequestMapping("test")
public class TestController {
	@RequestMapping("")
	public void m01() {
		pln("m01()");
	}
	@RequestMapping("base1")
	public void m02() {
		pln("m02() - Get() || Post() || Put() || ... ");
	}
	@RequestMapping(value="base2", method={RequestMethod.GET, RequestMethod.POST})
	public void m03() {
		pln("m03() - Get() || Post()");
	}
	@RequestMapping(value="form", method=RequestMethod.GET)
	public String form() {
		pln("form()");
		return "test/form"; // /WEB-INF/views/test/form.jsp
		
	}
	@RequestMapping(value="param1", method=RequestMethod.GET )
	public void m04(String name) {
		pln("m04() name: " + name);	
	}
	@RequestMapping(value="param2", method=RequestMethod.GET )
	public void m05(@RequestParam("na") String name, int age) {
		pln("m05() name: " + name + ", age: " + age);	
	}
	@RequestMapping(value="param3", method=RequestMethod.GET )
	public void m06(Human dto) {
		pln("m06() dto: " + dto);	
	}
	@RequestMapping(value="param4", method=RequestMethod.GET )
	public void m07(@RequestParam ArrayList<String> names) {
		pln("m07() names: " + names);	
	}
	@RequestMapping(value="param5", method=RequestMethod.GET )
	public void m08(@RequestParam String[] names) {
		pln("m08() names: " + names);	
		for(String name: names) pln("name: " + name);
	}
	@RequestMapping(value="param6", method=RequestMethod.GET )
	public void m09(@RequestParam("ns") ArrayList<String> names) {
		pln("m09() names: " + names);	
	}
	@RequestMapping(value="param7", method=RequestMethod.GET )
	public void m10(HumanList list) {
		pln("m10() list : " + list);	
	}
	@RequestMapping(value="param8", method=RequestMethod.GET )
	public void m11(Human dto, @RequestParam("dp") String dump, int grade) {
		pln("m11 dto: " + dto + ", dump: " + dump + ", grade: " + grade);	
	}
	@RequestMapping(value="param9", method=RequestMethod.GET )
	public void m12(Todo todo) {
		pln("m12() todo: " + todo);	
		Date cdate  = todo.getCdate();
		pln("m12() cdate: " + cdate);
		
		showDate(cdate);
	}
	private void showDate(Date cdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(cdate);
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d1 = cal.get(Calendar.DATE);
		int d2 = cal.get(Calendar.DAY_OF_WEEK);
		String day = "";
		switch(d2) {
			case 1: day="일"; break;
			case 2: day="월"; break;
			case 3: day="화"; break;
			case 4: day="수"; break;
			case 5: day="목"; break;
			case 6: day="금"; break;
			case 7: day="토"; break;
		}
		pln("m12(): " + y+"년 "+(m+1) + "월 " + d1+"일("+day+")");
	}
	
	
	void pln(String str) {
		System.out.println(str);
	}
}
