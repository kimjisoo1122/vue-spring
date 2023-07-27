package com.study.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 'file' 테이블 DTO
 */
@Data
public class FileDto {

    private Long fileId; // 파일번호
    private Long boardId; // 게시글번호
    private String fileSaveName; // 서버에 저장되는 물리적인 이름
    private String fileOrgName; // 실제 업로드된 파일이름
    private String filePath; // 파일경로
    private String fileExt; // 파일확장자
    private long fileSize; // 파일크기
    private LocalDateTime createDate; // 생성일시

    /**
     * 파일의 실제 저장된 경로를 반환합니다.
     * @return FullPath
     */
    public String getFullPath() {
        return filePath + fileSaveName;
    }
}
