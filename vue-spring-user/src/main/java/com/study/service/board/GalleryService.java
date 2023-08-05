package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.FileDto;
import com.study.enums.BoardType;
import com.study.repository.FileRepository;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.GalleryRepository;
import com.study.service.FileService;
import com.study.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 갤러리 게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final BoardRepository boardRepository;

    private final FileService fileService;
    private final FileRepository fileRepository;

    /**
     * 갤러리디테일에서 가장 순서가 빠른 썸네일,
     * 게시글이 포함한 갤러리 갯수를 포함한 갤러리게시글 정보를 조회합니다.
     *
     * @param condition 검색조건
     * @return List<BoardDto>
     */
    public List<BoardDto> findGalleryList(BoardSearchCondition condition) {
        return galleryRepository.selectByCondition(condition);
    }

    /**
     * 갤러리 게시글을 등록합니다.
     * 첨부파일이 존재하면 등록합니다.
     * 썸네일을 등록하고 갤러리디테일을 등록합니다.
     *
     * @param form 게시글 등록폼
     * @throws IOException 파일저장예외
     */
    @Transactional
    public Long register(BoardForm form) throws IOException {
        BoardDto galleryBoard = BoardUtil.createRegisterBoard(form);

        boardRepository.insert(galleryBoard);
        Long registeredBoardId = galleryBoard.getBoardId();

        // 파일을 생성하고 첨부파일을 등록합니다.
        // 썸네일을 생성하고 갤러리디테일을 등록합니다.
        if (form.getSaveFiles() != null) {
            List<FileDto> files = fileService.createGalleryFileList(form.getSaveFiles());

            for (FileDto file : files) {
                file.setBoardId(registeredBoardId);
                Long fileId = fileService.save(file);

                String thumbName = createThumbNail(file);

                galleryBoard.setFileId(fileId);
                galleryBoard.setGalleryImgName(file.getFileSaveName());
                galleryBoard.setGalleryThumbName(thumbName);

                galleryRepository.insertGalleryDetail(galleryBoard);
            }
        }

        return galleryBoard.getBoardId();
    }

    /**
     * 썸네일을 생성하고 썸네일이름을 반환합니다.
     *
     * @param file 저장된 파일
     * @return thumbName 썸네일 파일이름
     * @throws IOException 첨부파일 저장 중 발생한 예외
     */
    public String createThumbNail(FileDto file) throws IOException {
        String thumbName =  "thumb_" + file.getFileSaveName();
        Thumbnails.of(new File(file.getFullPath()))
                .forceSize(40, 40)
                .toFile(new File(file.getFilePath() + thumbName));

        return thumbName;
    }

    /**
     * 게시글을 수정합니다.
     * 첨부파일 -> 저장
     * 삭제파일 -> 삭제
     *
     * @param form 수정 폼
     * @throws IOException 첨부파일 저장 중 발생한 예외
     */

    public void update(BoardForm form) throws IOException {
        // 첨부파일이 있는 경우 추가합니다.
        if (form.getSaveFiles() != null) {
            List<FileDto> files = fileService.createGalleryFileList(form.getSaveFiles());

            for (FileDto file : files) {
                file.setBoardId(form.getBoardId());
                Long fileId = fileService.save(file);

                String thumbName = createThumbNail(file);

                BoardDto galleryBoard = new BoardDto();
                galleryBoard.setBoardId(form.getBoardId());
                galleryBoard.setFileId(fileId);
                galleryBoard.setGalleryImgName(file.getFileSaveName());
                galleryBoard.setGalleryThumbName(thumbName);

                galleryRepository.insertGalleryDetail(galleryBoard);
            }
        }

        /**
         * 1.갤러리 디테일을 삭제합니다.
         * 2.파일을 삭제합니다.
         */
        if (form.getDeleteFiles() != null) {
            for (Long fileId : form.getDeleteFiles()) {
                deleteDetailByFileId(fileId);
                fileService.deleteById(fileId);
            }
        }

        boardRepository.update(BoardUtil.createUpdateBoard(form));
    }

    /**
     * 갤러리게시글, 파일, 썸네일을 삭제합니다.
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        List<FileDto> fileList = fileService.findByBoardId(boardId);
        for (FileDto file : fileList) {
            deleteDetailByFileId(file.getFileId());
            fileService.deleteById(file.getFileId());
        }
        boardRepository.delete(boardId);
    }

    /**
     * 검색조건에 맞는 갤러리게시글 총 개수를 조회합니다.
     *
     * @param condition 검색조건
     * @return 갤러리게시글 총 개수
     */
    public int getTotalCnt(BoardSearchCondition condition) {
        return boardRepository.countByCondition(condition);
    }

    /**
     * 갤러리 상세정보를 조회합니다.
     *
     * @param boardId 갤러리 번호
     * @return 갤러리 상세 정보
     */
    public Optional<BoardDto> findGallery(Long boardId) {
        BoardDto board = new BoardDto();
        board.setBoardId(boardId);
        board.setBoardType(BoardType.GALLERY);

        return Optional.ofNullable(boardRepository.selectById(board));
    }

    /**
     * 디테일에 등록된 썸네일 경로를 조회하여 삭제 후
     * 디테일 정보를 삭제합니다.
     *
     * @param fileId 파일번호
     */
    public void deleteDetailByFileId(Long fileId) {
        deleteThumbFile(fileId);
        galleryRepository.deleteDetailByFileId(fileId);
    }

    /**
     * 파일번호로 등록된 썸네일 업로드파일을 삭제합니다.
     * @param fileId 썸네일이 등록된 파일번호
     */
    public void deleteThumbFile(Long fileId) {
        String thumbName = galleryRepository.selectThumbNameByFileId(fileId);

        if (StringUtils.hasText(thumbName)) {
            File thumbFile = new File(fileService.getGalleyPath() + thumbName);
            if (thumbFile.exists()) {
                thumbFile.delete();
            }
        }
    }

    /**
     * 갤러리게시글 첨부파일을 조회합니다.
     *
     * @param boardId 갤러리게시글 번호
     * @return 갤러리게시글 첨부파일 목록
     */
    public List<FileDto> findGalleryFileList(Long boardId) {
        return fileRepository.selectGalleryFile(boardId);
    }
}
