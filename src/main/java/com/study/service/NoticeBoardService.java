package com.study.service;

import com.study.dto.NoticeBoardDto;
import com.study.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    // 공지사항을 등록합니다.
    public Long insertNotice(NoticeBoardDto notice) {
        noticeBoardRepository.insertNotice(notice);
        return notice.getNoticeId();
    };

    // 공지사항을 조회합니다.
    public NoticeBoardDto selectNoticeById(Long noticeId) {
        return noticeBoardRepository.selectNoticeById(noticeId);
    }

    // 공지사항을 수정합니다.
    public int updateNotice(NoticeBoardDto notice) {
        return noticeBoardRepository.updateNotice(notice);
    }

    // 공지사항을 삭제합니다.
    public int deleteNotice(Long noticeId) {
        return noticeBoardRepository.deleteNotice(noticeId);
    }
}
