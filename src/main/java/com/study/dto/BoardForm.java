package com.study.dto;

import com.study.enums.BoardType;
import com.study.enums.FormType;
import lombok.Data;
import org.springframework.http.MediaType;
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

    private List<MultipartFile> saveFiles; // 저장할 파일목록
    private Long[] deleteFiles; // 삭제할 파일목록

    /* 폼으로 전송하는 데이터 */
    private List<CategoryDto> categoryList; // 카테고리 목록
    private List<FileDto> fileList; // 게시글에 등록된 파일 목록
    private List<ReplyDto> replyList; // 게시글에 등록된 댓글 목록

    /**
     * 갤러리, 자유게시판은 파일첨부가 존재합니다.
     */
    public boolean hasFileInput() {
        return boardType == BoardType.GALLERY || boardType == BoardType.FREE;
    }

    /**
     * 게시글 폼이 알림글인풋을 나타내는지 확인합니다.
     */
    public boolean hasAlarmInput() {
        return boardType == BoardType.NOTICE;
    }

    /**
     * 게시글 폼의 게시글타입과 폼타입을 설정합니다.
     * @param boardType 게시글타입
     * @param formType 폼타입
     */
    public void setBoardFormType(BoardType boardType, FormType formType) {
        this.boardType = boardType;
        this.formType = formType;
    }

    /**
     * @return 수정폼의 경우에만 삭제버튼이 존재합니다.
     */
    public boolean hasDeleteBtn() {
        return formType == FormType.UPDATE;
    }

    /**
     * @return 자유게시판의 수정폼일 경우 댓글을 사용할 수 있습니다.
     */
    public boolean hasReply() {
        return formType == FormType.UPDATE && boardType == BoardType.FREE;
    }

    /**
     * @return 파일인풋이 존재하는 자유게시판, 갤러리게시판인 경우 multipart/form-data
     * 그 외의 게시판은 application/x-www-form-urlencoded
     */
    public String getEncType() {
        return hasFileInput()
                ? MediaType.MULTIPART_FORM_DATA_VALUE
                : MediaType.APPLICATION_FORM_URLENCODED_VALUE;
    }
}
