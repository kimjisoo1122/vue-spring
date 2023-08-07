package com.study.dto;

import com.study.enums.BoardType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 통합게시글 Form DTO
 */
@Data
public class BoardForm {

    /* 검증값 */
    @NotBlank(message = "{title.notblank}")
    @Size(max = 100, message = "{title.size}")
    private String boardTitle; // 게시글 제목

    @NotBlank(message = "{content.notblank}")
    @Size(max = 4000, message = "{content.size}")
    private String boardContent; // 게시글 내용

     /* 폼 공통 */
    private Long categoryId; // 카테고리 번호
    private Long boardId; // 게시글 번호
    private String userId; // 사용자 아이디
    private String userName; // 사용자 이름
    private BoardType boardType; // 게시글 타입



    /* 특정게시판 전용 */

    /* 문의게시판 */
    private boolean qnaSecret; // 비밀글 여부

    /* 공지사항 */
    private boolean checkAlarm; // 알림글 여부

    /* 자유게시판, 갤러리게시판 멀티파트 */
    private MultipartFile[] saveFiles; // 저장할 파일목록
    private Long[] deleteFiles; // 삭제할 파일목록
}
