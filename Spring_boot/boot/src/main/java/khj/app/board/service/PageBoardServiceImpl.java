package khj.app.board.service;

import jakarta.transaction.Transactional;
import khj.app.board.domain.Attachment;
import khj.app.board.domain.Board;
import khj.app.board.dto.BoardListResult;
import khj.app.board.repository.AttachmentRepository;
import khj.app.board.repository.PageBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class PageBoardServiceImpl implements PageBoardService {
    @Autowired
    private final PageBoardRepository springDataJpaMariaBoardRepository;

    @Autowired
    private final AttachmentRepository attachmentRepository;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        Page<Board> pList = springDataJpaMariaBoardRepository.findByOrderBySeqDesc(pageable);

        return pList;
    }

    @Override
    public Board findBoardDetail(long seq) {
        return springDataJpaMariaBoardRepository.findById(seq).orElse(null);
    }

    @Override
    public BoardListResult getBoardListResult(Pageable pageable) {
        Page<Board> pList = springDataJpaMariaBoardRepository.findByOrderBySeqDesc(pageable);
        int page = pList.getNumber(); //페이지번호
        int size = pList.getSize(); //페이지크기
        long totalCount = pList.getTotalElements(); //글의 총갯수
        int totalPageCount = pList.getTotalPages(); //페이지 총갯수

        System.out.println("#Service page: "+page+", size: "+ size
                + ", totalCount: "+totalCount +", totalPageCount :"+totalPageCount);

        return new BoardListResult(pList, page, size, totalCount, totalPageCount);
    }

    @Value("${file.dir}")
    private String fileDir;

    @Transactional
    @Override
    public Board insertB(Board board,
                         @RequestPart(value="file",required = false) List<MultipartFile> files) throws IOException {
        Board saveBoard = springDataJpaMariaBoardRepository.save(board);

        File dirStore = new File(fileDir);
        if(!dirStore.exists()) dirStore.mkdirs();

        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String saveName = uuid + "_" + file.getOriginalFilename();

                java.nio.file.Path savePath = java.nio.file.Paths.get(fileDir, saveName);
                Files.copy(file.getInputStream(), savePath);

                Attachment attach = new Attachment();
                attach.setBoard(saveBoard);
                attach.setFname(saveName);
                attach.setOfname(file.getOriginalFilename());
                attach.setFsize(file.getSize());
                attach.setContentType(file.getContentType());

                saveBoard.getAttachments().add(attach);
            }
        }

        return springDataJpaMariaBoardRepository.save(saveBoard);
    }

    @Transactional
    @Override
    public void updateB(Board board, List<MultipartFile> files, List<Long> deleteFileIds) throws IOException {
        Board existing = springDataJpaMariaBoardRepository.findById(board.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.."));
        existing.setSubject(board.getSubject());
        existing.setContent(board.getContent());
        //existing.setWriter(board.getWriter());

        if (deleteFileIds != null && !deleteFileIds.isEmpty()) {
            for (Long fileId : deleteFileIds) {
                Attachment attachment = attachmentRepository.findById(fileId)
                        .orElseThrow(() -> new IllegalArgumentException("첨부 파일 없음 id=" + fileId));

                Path path = Paths.get(fileDir, attachment.getFname());
                Files.deleteIfExists(path);

                existing.getAttachments().remove(attachment);
                attachmentRepository.delete(attachment);
            }
        }
        if (files != null && !files.isEmpty()) {
            for(MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String uuid = UUID.randomUUID().toString();
                    String saveName = uuid + "_" + file.getOriginalFilename();

                    java.nio.file.Path savePath = java.nio.file.Paths.get(fileDir, saveName);
                    Files.copy(file.getInputStream(), savePath);

                    Attachment attach = new Attachment();
                    attach.setBoard(existing);
                    attach.setFname(saveName);
                    attach.setOfname(file.getOriginalFilename());
                    attach.setFsize(file.getSize());
                    attach.setContentType(file.getContentType());

                    existing.getAttachments().add(attach);
                }
            }
        }
        springDataJpaMariaBoardRepository.save(existing);
    }

    @Override
    public void deleteB(long seq) {
        springDataJpaMariaBoardRepository.deleteById(seq);
    }


    @Override
    public Page<Board> searchBySubject(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findBySubjectContaining(keyword, pageable);
    }

    @Override
    public Page<Board> searchByContent(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findByContentContaining(keyword, pageable);
    }

    @Override
    public Page<Board> searchByWriter(String keyword, Pageable pageable) {
        return springDataJpaMariaBoardRepository.findByWriterContaining(keyword, pageable);
    }

    @Override
    public ResponseEntity<Resource> getAttachment(Long id) throws IOException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow();
        java.nio.file.Path path = java.nio.file.Paths.get(fileDir, attachment.getFname());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                URLEncoder.encode(attachment.getOfname(), "UTF-8") + "\"")
                .body(resource);
    }
}
