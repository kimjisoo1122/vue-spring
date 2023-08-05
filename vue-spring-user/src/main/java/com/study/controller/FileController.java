package com.study.controller;

import com.study.dto.FileDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

/**
 * 파일 컨트롤러
 */
@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    /**
     * 게시글의 첨부파일을 조회합니다.
     *
     * @param boardId 게시글번호
     * @return 게시글 첨부파일 목록
     */
    @GetMapping("/board/{boardId}")
    public ResponseEntity<ResponseDto> files(
            @PathVariable("boardId") Long boardId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(fileService.findByBoardId(boardId));

        return ResponseEntity.ok(response);
    }

    /**
     * 파일 다운로드를 처리합니다.
     * @param fileId 파일번호
     * @return 파일을 다운로드 합니다.
     * @throws MalformedURLException 파일경로가 올바르지 않은 경우 발생하는 예외
     * @throws FileNotFoundException 파일을 찾지 못했을 경우 발생하는 예외
     */
    @GetMapping(value = "/{fileId}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<Resource> downloadFile(
            @PathVariable("fileId") Long fileId) throws MalformedURLException, FileNotFoundException {

        FileDto findFile = fileService.findById(fileId);
        UrlResource resource = new UrlResource("file:" + findFile.getFullPath());

        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다.");
        }

        String encodedOriginalFileName = UriUtils.encode(findFile.getFileOrgName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    /**
     * 이미지를 보여줍니다.
     *
     * @param  imgName 이미지파일 이름
     * @throws MalformedURLException 파일경로가 올바르지 않은 경우 발생하는 예외
     */
    @ResponseBody
    @GetMapping("/image/{imgName}")
    public Resource showImage(
            @PathVariable("imgName") String imgName) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getGalleyPath() + imgName);
    }
}
