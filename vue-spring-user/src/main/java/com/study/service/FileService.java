package com.study.service;

import com.study.dto.FileDto;
import com.study.exception.FileNotAllowedExtException;
import com.study.repository.FileRepository;
import com.study.repository.board.GalleryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 파일을 처리하는 서비스
 */
@Service
@Transactional
public class FileService {

    private final List<String> allowedExtensions;
    private final String FILE_PATH; // 업로드 파일경로
    private final String GALLERY_PATH; // 갤러리 파일경로

    private final FileRepository fileRepository;
    private final GalleryRepository galleryRepository;

    public FileService(
            @Value("${file.upload-folder}") String FILE_PATH,
            @Value("#{'${file.allowed-extensions}'.split(',')}") List<String> allowedExtensions,
            FileRepository fileRepository,
            GalleryRepository galleryRepository) {
        this.FILE_PATH = FILE_PATH;
        this.GALLERY_PATH = FILE_PATH + "gallery/";
        this.allowedExtensions = allowedExtensions;
        this.fileRepository = fileRepository;
        this.galleryRepository = galleryRepository;

        initPathFolder();
    }

    /**
     * Form에서 입력받은 멀티파트파일을 실제 파일로 업로드하고 DTO리스트를 반환합니다.
     * @param multipartFiles 폼에서 전송된 멀티파트파일
     * @return List<FileDto> 파일 DB에 저장하는 DTO 리스트
     * @throws IOException 파일저장에 발생되는 예외
     */
    public List<FileDto> createFileList(MultipartFile[] multipartFiles, String FILE_PATH) throws IOException {
        List<FileDto> files = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = file.getOriginalFilename();
            String fileExt = extractFileExt(fileName);

            validateFileExt(fileExt);

            String formattedFileName = getFormattedFileName(fileExt);
            file.transferTo(new File(FILE_PATH + formattedFileName));

            // 파일 DB 데이터 생성
            FileDto saveFile = new FileDto();
            saveFile.setFileSaveName(formattedFileName);
            saveFile.setFileOrgName(file.getOriginalFilename());
            saveFile.setFileExt(fileExt);
            saveFile.setFileSize(file.getSize());
            saveFile.setFilePath(FILE_PATH);
            files.add(saveFile);
        }

        return files;
    }

    public List<FileDto> createFileList(MultipartFile[] multipartFiles) throws IOException {
        return createFileList(multipartFiles, FILE_PATH);
    }

    /**
     * 갤러리업로드 경로로 파일을 업로드합니다.
     * @param multipartFiles 폼에서 전송된 멀티파트파일
     * @return List<FileDto> 파일 DB에 저장하는 DTO 리스트
     * @throws IOException 파일저장에 발생되는 예외
     */
    public List<FileDto> createGalleryFileList(MultipartFile[] multipartFiles) throws IOException {
        return createFileList(multipartFiles, GALLERY_PATH);
    }


    /**
     * 첨부파일을 등록합니다
     * @param fileDto 파일DTO
     * @return fileId 등록된 파일 번호
     */
    public Long save(FileDto fileDto) {
        fileRepository.insert(fileDto);
        return fileDto.getFileId();
    }

    /**
     * 게시글에 등록된 첨부파일을 조회합니다.
     *
     * @param boardId 게시글 번호
     * @return 게시글에 등록된 첨부파일
     */
    public List<FileDto> findByBoardId(Long boardId) {
        return fileRepository.selectByBoardId(boardId);
    }

    /**
     * 첨부파일을 조회합니다.
     * @param fileId 파일번호
     * @return FileDto 조회한 파일정보 DTO
     */
    public FileDto findById(Long fileId) {
        return fileRepository.selectById(fileId);
    }

    /**
     * 첨부파일과 업로드된 실제파일도 삭제합니다
     * @param fileId 파일번호
     */
    public void delete(Long fileId) {
        FileDto findFile = fileRepository.selectById(fileId);
        File uploadedFile = new File(findFile.getFullPath());
        if (uploadedFile.exists()) {
            uploadedFile.delete();
        }

        deleteThumbFile(fileId);

        fileRepository.delete(fileId);
    }

    /**
     * 게시글에 첨부된 파일을 삭제합니다.
     * @param boardId 게시글번호
     */
    public void deleteByBoardId(Long boardId) {
        fileRepository.selectByBoardId(boardId)
                .forEach(e -> {
                    delete(e.getFileId());
                });
    }

    /**
     * 파일번호로 등록된 썸네일파일을 삭제합니다.
     * @param fileId
     */
    public void deleteThumbFile(Long fileId) {
        String thumbName = galleryRepository.selectThumbNameByFileId(fileId);

        if (StringUtils.hasText(thumbName)) {
            File thumbFile = new File(GALLERY_PATH + thumbName);
            if (thumbFile.exists()) {
                thumbFile.delete();
            }
        }

        galleryRepository.deleteByFileId(fileId);
    }

    /**
     * 파일명을 UUID로 포맷후 확장자를 붇혀 반환합니다.
     * @param ext 파일확장자
     * @return formattedFileNam
     */
    private String getFormattedFileName(String ext) {
        return UUID.randomUUID() + "." + ext;
    }

    /**
     * 파일의 확장자명을 추출합니다.
     * @param fileName 파일이름
     * @return fileExt 파일확장자
     */
    private String extractFileExt(String fileName) {
        int dotIdx = fileName.lastIndexOf(".");
        return fileName.substring(dotIdx + 1);
    }

    /**
     * 파일의 확장자를 검증합니다.
     * @param fileExt 파일확장자
     * @throws FileNotAllowedExtException
     */
    private void validateFileExt(String fileExt) {
        allowedExtensions.stream()
                .filter(fileExt::equals)
                .findAny()
                .orElseThrow(() -> {
                    throw new FileNotAllowedExtException("허용되지 않은 파일확장자 입니다.");
                });
    }

    /**
     * 파일업로드 경로의 폴더를 생성합니다.
     */
    private void initPathFolder() {
        new File(FILE_PATH).mkdirs();
        new File(GALLERY_PATH).mkdirs();
    }

    /**
     * 파일의 업로드경로를 반환합니다.
     * @return filePath
     */
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * 갤러리파일의 업로드경로를 반환합니다.
     * @return galleyPath
     */
    public String getGalleyPath() {
        return GALLERY_PATH;
    }
}
