package khj.md.fileset;

import java.util.Map;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 파일 다운로드 처리를 담당하는 커스텀 View 클래스
 * - Spring MVC의 AbstractView를 상속받아 구현
 * - Controller에서 "downloadFile"이라는 키로 File 객체를 전달하면,
 *   이 클래스를 통해 브라우저로 파일을 전송해 다운로드 가능
 */
public class FileDownloadViewComment extends AbstractView {

    // 생성자: 뷰의 Content-Type 설정
    public FileDownloadViewComment() {
        // 브라우저에서 다운로드 처리하도록 MIME 타입 지정
        // "application/download"는 일반적인 다운로드 용도, charset은 UTF-8로 설정
        setContentType("application/download;charset=utf-8");
    }

    /**
     * 실제 파일 다운로드 로직을 처리하는 메소드
     * @param model 컨트롤러에서 전달한 데이터 맵
     * @param request 클라이언트의 요청 정보
     * @param response 서버가 클라이언트에 보낼 응답 객체
     */
    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, 
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // 컨트롤러에서 넣어준 "downloadFile" 키의 File 객체 꺼내오기
        File file = (File) model.get("downloadFile");
        
        // 응답의 Content-Type 설정 (다운로드 MIME 타입)
        response.setContentType(getContentType());
        // 응답의 Content-Length 설정 (파일의 크기: 브라우저가 다운로드 진행상황 표시 가능)
        response.setContentLength((int) file.length());
        
        // 브라우저가 "파일 다운로드" 창을 띄우도록 설정
        // 파일 이름은 UTF-8로 인코딩하여 깨짐 방지
        String value = "attachment; filename=" 
                        + java.net.URLEncoder.encode(file.getName(), "utf-8") 
                        + ";";
        response.setHeader("Content-Disposition", value);
        // 전송 인코딩 방식 지정 (바이너리 데이터 전송)
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        // 실제 파일 데이터를 읽어서 클라이언트로 내보내기 위한 스트림 준비
        FileInputStream fis = null; // 파일을 읽는 입력 스트림 (근원지)
        OutputStream os = response.getOutputStream(); // 클라이언트로 전송할 출력 스트림 (목적지)
        
        try {
            // 파일을 읽어와 응답 스트림으로 복사 (Spring 제공 FileCopyUtils 사용)
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, os);
            os.flush(); // 버퍼에 남아 있는 데이터 강제 전송
        } catch (IOException ie) {
            // 다운로드 중 오류 발생 시 예외 처리 (여기서는 단순히 무시)
        } finally {
            // 자원 정리 (스트림 닫기)
            if (fis != null) fis.close();
            if (os != null) os.close();
        }
    }
}
