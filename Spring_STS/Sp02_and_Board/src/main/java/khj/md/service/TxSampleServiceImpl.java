package khj.md.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import khj.md.mapper.TxSample1Mapper;
import khj.md.mapper.TxSample2Mapper;

@Log4j
@Service
public class TxSampleServiceImpl implements TxSampleService {
	@Autowired
	private TxSample1Mapper txSample1Mapper;
	@Autowired
	private TxSample2Mapper txSample2Mapper;
	
	@Transactional  // 트랜잭션으로 아래 코드들이 묶임!!!!
	@Override
	public void addDatas(String data) {
		log.info("#TxSampleServiceImpl addDatas() - Step1");
		txSample1Mapper.insertCol1(data); //DML1
		log.info("#TxSampleServiceImpl addDatas() - Step2");
		txSample2Mapper.insertCol2(data); //DML2	
		log.info("#TxSampleServiceImpl addDatas() - Step3");
	}
}
