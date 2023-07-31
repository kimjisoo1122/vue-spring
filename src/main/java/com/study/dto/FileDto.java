package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'file' 테이블 DTO
 */
@Data
public class FileDto {

    private Long fileId; // 파일 번호
    private Long boardId; // 게시글 번호
    private String fileSaveName; // 서버에 저장되는 물리적인 이름
    private String fileOrgName; // 실제 업로드된 파일이름
    private String filePath; // 파일 경로
    private String fileExt; // 파일 확장자
    private long fileSize; // 파일 크기
    private LocalDateTime createDate; // 생성일시

    /* 갤러리 게시판 첨부파일*/
    private String galleryThumbName; // 갤러리 썸네일 이미지

    /**
     * 파일의 실제 저장된 경로를 반환합니다.
     * @return FullPath
     */
    public String getFullPath() {
        return filePath + fileSaveName;
    }
}
