package khj.md.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TxSampleServiceTests {
	@Autowired
	private TxSampleService txSampleService;
	
	@Test
	public void testAddDatas(){
		//String data = "갓창우";
		//String data = "가을엔 편지를 하겠어요 누구라도 그대가 되어 받아 주세요 낙엽이 쌓이는 날 외로운 여자가 아름다워요";
		//String data = "나 보기가 역겨워 가실 때에는 말없이 고이 보내 드리오리다 영변에 약산 진달래꽃 아름따다 가실 길에 뿌리오리다";
		String data = "효상갓";
		
		int len = data.getBytes().length;
		log.info("@입력하는 바이트수: " + len);
		
		txSampleService.addDatas(data);
	}
}
