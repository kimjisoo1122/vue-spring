package com.study.mapper;

import com.study.dto.NoticeBoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 'notice_board' 테이블 매퍼 입니다.
 */
@Mapper
public interface NoticeBoardMapper {

    int insertNotice(NoticeBoardDto notice);
    NoticeBoardDto selectNoticeById(@Param("noticeId") long noticeId);
    int updateNotice(NoticeBoardDto notice);
    int deleteNotice(@Param("noticeId") long noticeId);
}
