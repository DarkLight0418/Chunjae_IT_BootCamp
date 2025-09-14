package khj.board.mapper;

import java.util.List;
import khj.board.domain.Attachment;

public interface AttachmentMapper {
	void insert(Attachment att);
	List<Attachment> findByAttachments(long seq);
	void deleteBySeq(long seq);
}
