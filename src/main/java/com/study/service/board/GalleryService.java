package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.FileDto;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.GalleryRepository;
import com.study.service.FileService;
import com.study.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 갤러리 게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final BoardRepository boardRepository;

    private final FileService fileService;

    /**
     * 갤러리디테일에서 가장 순서가 빠른 썸네일,
     * 게시글이 포함한 갤러리 갯수를 포함한 갤러리게시글 정보를 조회합니다.
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
     * @param form 게시글 등록폼
     * @throws IOException 파일저장예외
     */
    @Transactional
    public void register(BoardForm form) throws IOException {
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
    }

    /**
     * 썸네일을 생성하고 썸네일이름을 반환합니다.
     * @param file 저장된 파일
     * @return thumbName 썸네일 파일이름
     * @throws IOException
     */
    public String createThumbNail(FileDto file) throws IOException {
        String thumbName =  "thumb_" + file.getFileSaveName();
        Thumbnails.of(new File(file.getFullPath()))
                .forceSize(40, 40)
                .toFile(new File(file.getFilePath() + thumbName));

        return thumbName;
    }

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

        // 삭제파일이 있는 경우 삭제합니다.
        if (form.getDeleteFiles() != null) {
            for (Long fileId : form.getDeleteFiles()) {
                fileService.delete(fileId);
            }
        }

        boardRepository.update(BoardUtil.createUpdateBoard(form));
    }

    /**
     * 갤러리게시글, 파일, 썸네일을 삭제합니다.
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        fileService.deleteByBoardId(boardId);
        boardRepository.delete(boardId);
    }
}
