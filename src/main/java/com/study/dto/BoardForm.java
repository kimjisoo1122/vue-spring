package com.study.dto;

import com.study.enums.BoardType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BoardForm {

    private Long boardId;

    @NotNull(message = "{categoryid.notnull}")
    private Long categoryId;

    @NotBlank(message = "{title.notblank}")
    @Size(max = 100, message = "{title.size}")
    private String boardTitle;

    @NotBlank(message = "{content.notblank}")
    @Size(max = 4000, message = "{content.size}")
    private String boardContent;

    private String userId;
    private String userName;

    private List<MultipartFile> files;
    private String[] deleteFiles;

    private boolean boardSecret;
    private boolean checkAlarm;

    private BoardType boardType;

    public boolean hasFileInput() {
        return this.boardType == BoardType.GALLERY || this.boardType == BoardType.FREE;
    }

    public boolean hasAlarmInput() {
        return this.boardType == BoardType.NOTICE;
    }
}
