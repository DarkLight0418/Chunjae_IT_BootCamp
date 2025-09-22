package khj.app.boot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import khj.app.boot.domain.FileUp;
import khj.app.boot.repository.FileRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @org.springframework.beans.factory.annotation.Value("${file.dir}")
    private String fileDir;
    private final FileRepository fileRepository;

    @Transactional
    @Override
    public long saveFile(MultipartFile mf) throws IOException {
        File dirStore = new File(fileDir);
        if(!dirStore.exists()) dirStore.mkdirs();

        if(mf.isEmpty()){
            return -1L;
        }

        String origName = mf.getOriginalFilename(); //원래 파일 이름
        String uuid = UUID.randomUUID().toString(); //파일 이름으로 쓸 uuid 생성
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension; //uuid와 확장자 결합
        String savedPath = fileDir + savedName; //저장될 파일의 물리적 경로

        FileUp fileUp = FileUp.builder()
                .orgnm(origName)
                .savednm(savedName)
                .savedpath(savedPath)
                .build();
        mf.transferTo(new File(savedPath)); //서버에 파일이 저장

        FileUp savedFile = fileRepository.save(fileUp); //DB에 insert

        return savedFile.getId();
    }

    @Override
    public List<FileUp> getFileUpAll() {
        List<FileUp> fileUps = fileRepository.findAll();
        return fileUps;
    }
    @Override
    public FileUp getFileUp(long id) {
        //Optional<FileUp> op = fileRepository.findById(id);
        //FileUp fileUp = op.orElse(null);
        FileUp fileUp = fileRepository.findById(id).orElse(null);

        return fileUp;
    }
}
