package khj.md.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import khj.md.domain.Address;
import khj.md.service.AddressAjaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/rest_addr")
@RequiredArgsConstructor  // 반드시 초기화가 필요한 멤버 변수를 초기화해줌
public class AddressRestController {

	private final AddressAjaxService addressAjaxService;
	
	// (1) Create (테스트를 위한 get 요청으로 처리)
	/*
	 	전송 예시
	 	http://localhost:8080/rest_addr/create1?name=가1&addr=나1
		http://localhost:8080/rest_addr/create1.json?name=가2&addr=나2
		http://localhost:8080/rest_addr/create1.xml?name=가3&addr=나3
	 */
	@GetMapping("/create1")  // 자바스크립트 객체는 dto만
	public void create1(Address address) {	// 파라미터를 jsObj 로 받는 경우
		log.info("@AddressRestController create1() address: " + address);
		addressAjaxService.insertS(address);
	}
    //http://127.0.0.1:8080/rest_addr/create1?name=가1&addr=나1
    //http://127.0.0.1:8080/rest_addr/create1.json?name=가2&addr=나2
    //http://127.0.0.1:8080/rest_addr/create1.xml?name=가3&addr=나3
	
	/*
	 	전송 예시 (POST 요청의 BODY 내에 작성)
	 	{"name" : "나는", "addr" : "바보다"}
	 */
	
	@PostMapping("/create2")  // JSON은 @RequestBody 쓰기
	public ResponseEntity<?> create2(@RequestBody Address address) {	// 파라미터를 JSON 으로 받는 경우
		log.info("@AddressRestController create2() address: " + address);
		addressAjaxService.insertS(address);
		return ResponseEntity.ok(address);
	}

    //(2) Read 
    @GetMapping("read")
    public List<Address> read(){
        List<Address> list = addressAjaxService.listS();
        return list;
    }
    //http://127.0.0.1:8080/rest_addr/read 또는 read.xml
    //http://127.0.0.1:8080/rest_addr/read.json
    
    @GetMapping("read/{seq}")
    public Address read(@PathVariable long seq){
        Address address = addressAjaxService.selectBySeqS(seq);
        return address;
    }
    //http://127.0.0.1:8080/rest_addr/read/3
    //http://127.0.0.1:8080/rest_addr/read/3.xml
    //http://127.0.0.1:8080/rest_addr/read/3.json
    
    @GetMapping("read/search/{na}")
    public List<Address> read(@PathVariable String na){
        List<Address> list  = addressAjaxService.selectByNameS(na);
        return list;
    }
    //http://127.0.0.1:8080/rest_addr/read/search/홍
    //http://127.0.0.1:8080/rest_addr/read/search/홍.xml
    //http://127.0.0.1:8080/rest_addr/read/search/홍.json
    
    //(3) Delete
    @DeleteMapping("delete/{seq}")
    public void delete(@PathVariable long seq) {
        addressAjaxService.deleteS(seq);
    }
    //http://127.0.0.1:8080/rest_addr/delete/90
    //http://127.0.0.1:8080/rest_addr/delete/91.xml
    //http://127.0.0.1:8080/rest_addr/delete/92.json
}



