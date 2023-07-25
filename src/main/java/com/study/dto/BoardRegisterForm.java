package com.study.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BoardRegisterForm {

    @NotNull(message = "{categoryid.notnull}")
    private Long categoryId;

    @NotBlank(message = "{writer.notblank}")
    @Size(max = 100, message = "{writer.size}")
    private String writer;

    @NotBlank(message = "{content.notblank}")
    @Size(max = 4000, message = "{content.size}")
    private String content;

    private List<MultipartFile> files;
    private boolean secret;
}
