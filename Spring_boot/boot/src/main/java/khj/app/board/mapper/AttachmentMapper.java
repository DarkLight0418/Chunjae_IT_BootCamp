package khj.app.board.mapper;

import java.util.List;
import khj.app.board.domain.Attachment;

public interface AttachmentMapper {
	void insert(Attachment att);
	List<Attachment> findByAttachments(long seq);
	void deleteBySeq(long seq);
}
