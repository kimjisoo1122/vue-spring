package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.NoticeRepository;
import com.study.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 공지사항 서비스
 */
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final BoardRepository boardRepository;
    private final NoticeRepository noticeRepository;

    /**
     * 공지사항을 등록합니다.
     * 알림으로 체크 된 경우 알림글로 등록합니다.
     *
     * @param form 공지사항 등록폼
     * @return 등록된 공지사항 번호
     */
    public Long register(BoardForm form) {

        BoardDto registerNotice = BoardUtil.createRegisterBoard(form);
        boardRepository.insert(registerNotice);

        if (form.isCheckAlarm()) {
            noticeRepository.insertNoticeAlarm(registerNotice.getBoardId());
        }

        return registerNotice.getBoardId();
    }

    /**
     * 공지사항 목록을 조건으로 조회합니다.
     *
     * @param condition 검색조건
     * @return 공지사항목록
     */
    public List<BoardDto> findNoticeList(BoardSearchCondition condition) {
        return boardRepository.selectByCondition(condition);
    }

    /**
     * 공지사항 중 알림글을 조회합니다.
     * @param limit 알림글 개수
     * @return 알림글 목록
     */
    public List<BoardDto> findAlarmList(int limit) {
        return noticeRepository.selectNoticeAlarm(limit);
    }

    /**
     * 공지사항 번호로 공지사항 상세정보를 조회합니다.
     *
     * @param boardId 공지사항 번호
     * @return 공지사항 상세정보
     */
    public Optional<BoardDto> findNoticeDetail(Long boardId) {
        BoardDto board = new BoardDto();
        board.setBoardId(boardId);
        board.setBoardType(BoardType.NOTICE);

        return Optional.ofNullable(noticeRepository.selectNoticeDetail(board));
    }

    /**
     * 검색조건에 맞는 공지사항 총 개수를 조회합니다.
     *
     * @param condition 검색조건
     * @return 공지사항 총 개수
     */
    public int getTotalCnt(BoardSearchCondition condition) {
        return boardRepository.countByCondition(condition);
    }

    /**
     * 게시글폼으로 공지사항을 업데이트 합니다.
     *
     * @param form 공지사항 수정폼
     */
    public void update(BoardForm form) {
        BoardDto updateNotice = BoardUtil.createUpdateBoard(form);

        // 알림글여부에 따라 알림글을 추가, 삭제 합니다.
        if (form.isCheckAlarm()) {
            noticeRepository.insertNoticeAlarm(updateNotice.getBoardId());
        } else {
            noticeRepository.deleteNoticeAlarm(updateNotice.getBoardId());
        }

        boardRepository.update(updateNotice);
    }

    /**
     * 공지사항을 삭제합니다.
     *
     * @param boardId 공지사항 번호
     */
    public void delete(Long boardId) {
        boardRepository.delete(boardId);
    }
}
