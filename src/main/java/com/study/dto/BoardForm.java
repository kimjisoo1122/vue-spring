package com.study.dto;

import com.study.enums.BoardType;
import com.study.enums.FormType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 통합게시글 Form DTO
 */
@Data
public class BoardForm {

    @NotNull(message = "{categoryid.notnull}")
    private Long categoryId; // 카테고리 번호

    @NotBlank(message = "{title.notblank}")
    @Size(max = 100, message = "{title.size}")
    private String boardTitle; // 게시글 제목

    @NotBlank(message = "{content.notblank}")
    @Size(max = 4000, message = "{content.size}")
    private String boardContent; // 게시글 내용

    private Long boardId; // 게시글 번호

    private String userId; // 사용자 아이디
    private String userName; // 사용자 이름

    private boolean boardSecret; // 비밀글 여부
    private boolean checkAlarm; // 알림글 여부

    private BoardType boardType; // 게시글 타입
    private FormType formType; // 폼 타임

    private List<MultipartFile> files; // 첨부파일
    private String[] deleteFiles; // 삭제파일


    /**
     * 게시글 폼이 첨부파일을 나타내는지 확인합니다.
     */
    public boolean hasFileInput() {
        return this.boardType == BoardType.GALLERY || this.boardType == BoardType.FREE;
    }

    /**
     * 게시글 폼이 알림글인풋을 나타내는지 확인합니다.
     */
    public boolean hasAlarmInput() {
        return this.boardType == BoardType.NOTICE;
    }
}
