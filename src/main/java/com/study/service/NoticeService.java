package com.study.service;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.enums.BoardType;
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
     * 알림으로 체크된 경우 알림글로 등록합니다.
     * @param form 등록폼
     * @return boardId 등록된 공지사항 번호
     */
    public Long register(BoardForm form) {

        BoardDto noticeBoard = createNoticeBoard(form);

        boardRepository.insertBoard(noticeBoard);

        if (form.isCheckAlarm()) {
            noticeRepository.insertNoticeAlarm(noticeBoard.getBoardId());
        }

        return noticeBoard.getBoardId();
    };

    private BoardDto createNoticeBoard(BoardForm form) {

        BoardDto noticeBoard = new BoardDto();
        noticeBoard.setBoardType(BoardType.NOTICE);
        noticeBoard.setCategoryId(form.getCategoryId());
        noticeBoard.setBoardTitle(form.getBoardTitle());
        noticeBoard.setBoardContent(form.getBoardContent());
        noticeBoard.setUserId(form.getUserId());
        noticeBoard.setUserName(form.getUserName());

        return noticeBoard;
    }

    public List<BoardDto> findNoticeList(BoardSearchCondition condition) {

        List<BoardDto> boardList = boardRepository.selectByCondition(condition);

        List<BoardDto> noticeAlarmList = noticeRepository.selectNoticeAlarm(5);

        noticeAlarmList.addAll(boardList);

        return noticeAlarmList;
    }

//
//    // 공지사항을 조회합니다.
//    public NoticeDto selectNoticeById(Long noticeId) {
//        return noticeRepository.selectNoticeById(noticeId);
//    }
//
//    // 공지사항을 수정합니다.
//    public int updateNotice(NoticeDto notice) {
//        return noticeRepository.updateNotice(notice);
//    }
//
//    // 공지사항을 삭제합니다.
//    public int deleteNotice(Long noticeId) {
//        return noticeRepository.deleteNotice(noticeId);
//    }
}
