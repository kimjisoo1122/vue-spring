package com.study.service.board;

import com.study.dto.BoardDto;
import com.study.dto.BoardForm;
import com.study.dto.BoardSearchCondition;
import com.study.dto.FileDto;
import com.study.enums.BoardType;
import com.study.repository.FileRepository;
import com.study.repository.board.BoardRepository;
import com.study.repository.board.FreeRepository;
import com.study.service.FileService;
import com.study.util.BoardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 자유게시판 서비스
 */
@Service
@RequiredArgsConstructor
public class FreeService {

    private final BoardRepository boardRepository;
    private final FreeRepository freeRepository;
    private final FileRepository fileRepository;

    private final FileService fileService;

    /**
     * 자유게시글을 등록합니다.
     *
     * @param form 자유 게시글 등록 폼
     * @return 등록된 게시글의 고유 번호
     * @throws IOException 첨부파일 저장 중 발생한 예외
     */
    @Transactional
    public Long register(BoardForm form) throws IOException {
        BoardDto registerBoard = BoardUtil.createRegisterBoard(form);
        boardRepository.insert(registerBoard);
        Long registeredBoardId = registerBoard.getBoardId();

        // 파일을 생성하고 첨부파일을 등록합니다.
        if (form.getSaveFiles() != null) {
            List<FileDto> files = fileService.createFileList(form.getSaveFiles());

            for (FileDto file : files) {
                file.setBoardId(registeredBoardId);
                fileService.save(file);
            }
        }

        return registeredBoardId;
    }

    /**
     * 자유게시글 목록을 조회합니다.
     *
     * @param condition 검색 조건
     * @return 자유게시글 목록
     */
    public List<BoardDto> findFreeList(BoardSearchCondition condition) {
        return freeRepository.selectFreeListByCondition(condition);
    }

    /**
     * 검색조건에 맞는 자유게시글 총 개수를 조회합니다.
     *
     * @param condition 검색조건
     * @return 자유 게시글 총 개수
     */
    public int getTotalCnt(BoardSearchCondition condition) {
        return boardRepository.countByCondition(condition);
    }

    /**
     * 자유게시글 폼을 조회합니다.
     *
     * @param boardId 자유 게시글 번호
     * @return 자유 게시글 폼
     */
    public BoardForm findFreeForm(Long boardId) {
        BoardDto board = new BoardDto();
        board.setBoardId(boardId);
        board.setBoardType(BoardType.FREE);

        return boardRepository.selectForm(board);
    }

    /**
     * 자유게시글을 업데이트합니다.
     * 첨부파일 -> 저장
     * 삭제파일 -> 삭제
     *
     * @param form 수정 폼
     * @throws IOException 첨부파일 저장 중 발생한 예외
     */
    @Transactional
    public void update(BoardForm form) throws IOException {
        // 첨부파일이 있는 경우 추가합니다.
        if (form.getSaveFiles() != null) {
            List<FileDto> files = fileService.createFileList(form.getSaveFiles());
            for (FileDto file : files) {
                file.setBoardId(form.getBoardId());
                fileRepository.insert(file);
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
     * 자유게시글, 첨부파일을 삭제합니다.
     *
     * @param boardId 게시글번호
     */
    public void delete(Long boardId) {
        fileService.deleteByBoardId(boardId);
        boardRepository.delete(boardId);
    }
}
