package com.study.service;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.repository.BoardRepository;
import com.study.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 공지사항 서비스 입니다.
 */
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final BoardRepository boardRepository; // 게시글 리포지토리
    private final NoticeRepository noticeRepository; // 공지사항 리포지토리

    /**
     * 공지사항을 등록합니다.
     * 알림으로 체크 된 경우 알림글로 등록합니다.
     * @param form 공지사항 등록폼
     * @return boardId 등록된 공지사항 번호
     */
    public Long register(BoardForm form) {

        BoardDto registerNotice = createRegisterNotice(form);
        boardRepository.insertBoard(registerNotice);

        if (form.isCheckAlarm()) {
            noticeRepository.insertNoticeAlarm(registerNotice.getBoardId());
        }

        return registerNotice.getBoardId();
    };

    /**
     * 알림글로 등록된 공지사항을 최신순으로 limit만큼 조회합니다.
     * 공지사항 목록을 조건으로 조회합니다.
     * 알림글에 공지사항목록을 순서대로 추가하여 알림글 + 공지사항을 리스트로 반환합니다.
     * @param condition 검색조건
     * @return List<BoardDto> 알림글 + 공지사항목록
     */
    public List<BoardDto> findNoticeList(BoardSearchCondition condition) {
        List<BoardDto> noticeList = boardRepository.selectByCondition(condition);

        List<BoardDto> noticeAlarmList = noticeRepository.selectNoticeAlarm(5);

        // 알림글에 공지사항목록을 순서대로 추가합니다.
        noticeAlarmList.addAll(noticeList);

        return noticeAlarmList;
    }

    /**
     * 게시글폼으로 공지사항을 업데이트 합니다.
     * @param form 공지사항 수정폼
     */
    public void update(BoardForm form) {
        BoardDto updateNotice = createUpdateNotice(form);

        // 알림글여부에 따라 알림글을 추가, 삭제 합니다.
        if (form.isCheckAlarm()) {
            noticeRepository.insertNoticeAlarm(updateNotice.getBoardId());
        } else {
            noticeRepository.deleteNoticeAlarm(updateNotice.getBoardId());
        }

        boardRepository.updateBoard(updateNotice);
    }

    /**
     * 공지사항을 삭제합니다.
     * @param boardId 공지사항 번호
     */
    public void delete(Long boardId) {
        boardRepository.deleteBoard(boardId);
    }

    /**
     * 공지사항 수정용 DTO를 생성합니다
     * @param form 공지사항 수정폼
     * @return BoardDto
     */
    private BoardDto createUpdateNotice(BoardForm form) {
        BoardDto updateBoard = new BoardDto();
        updateBoard.setBoardId(form.getBoardId());
        updateBoard.setCategoryId(form.getCategoryId());
        updateBoard.setBoardTitle(form.getBoardTitle());
        updateBoard.setBoardContent(form.getBoardContent());

        return updateBoard;
    }

    /**
     * 공지사항 등록용 DTO를 생성합니다
     * @param form 공지사항 등록폼
     * @return BoardDto
     */
    private BoardDto createRegisterNotice(BoardForm form) {
        BoardDto noticeBoard = new BoardDto();
        noticeBoard.setBoardType(form.getBoardType());
        noticeBoard.setCategoryId(form.getCategoryId());
        noticeBoard.setBoardTitle(form.getBoardTitle());
        noticeBoard.setBoardContent(form.getBoardContent());
        noticeBoard.setUserId(form.getUserId());
        noticeBoard.setUserName(form.getUserName());

        return noticeBoard;
    }
}
