package com.study.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@Transactional
class NoticeBoardServiceTest {

//    @Autowired
//    NoticeBoardService noticeBoardService;
//
//
//    @Test
//    void testInsertNotice() {
//        NoticeBoardDto notice = new NoticeBoardDto();
//        notice.setCategoryId(1L);
//        notice.setNoticeWriter("admin");
//        notice.setNoticeTitle("Test Title");
//        notice.setNoticeContent("Test Content");
//
//        assertThat(noticeBoardService.register(notice)).isNotNull();
//    }
//
//    @Test
//    void testSelectNoticeById() {
//        NoticeBoardDto notice = new NoticeBoardDto();
//        notice.setCategoryId(1L);
//        notice.setNoticeWriter("admin");
//        notice.setNoticeTitle("Test Title");
//        notice.setNoticeContent("Test Content");
//
//        Long noticeId = noticeBoardService.register(notice);
//        assertThat(noticeBoardService.register(notice)).isNotNull();
//
//        NoticeBoardDto findNotice = noticeBoardService.selectNoticeById(noticeId);
//
//        assertThat(findNotice).isNotNull();
//
//        assertThat(findNotice.getNoticeWriter()).isEqualTo(notice.getNoticeWriter());
//        assertThat(findNotice.getNoticeContent()).isEqualTo(notice.getNoticeContent());
//        assertThat(findNotice.getNoticeTitle()).isEqualTo(notice.getNoticeTitle());
//    }
//
//    @Test
//    void testUpdateNotice() {
//        NoticeBoardDto notice = new NoticeBoardDto();
//        notice.setCategoryId(1L);
//        notice.setNoticeWriter("admin");
//        notice.setNoticeTitle("Test Title");
//        notice.setNoticeContent("Test Content");
//
//        Long noticeId = noticeBoardService.register(notice);
//
//        assertThat(noticeId).isNotNull();
//
//        NoticeBoardDto findNotice = noticeBoardService.selectNoticeById(noticeId);
//
//        findNotice.setNoticeWriter("admin");
//        findNotice.setNoticeContent("Update Content");
//        findNotice.setNoticeTitle("Update Title");
//
//        noticeBoardService.updateNotice(findNotice);
//
//        NoticeBoardDto findUpdatedNotice = noticeBoardService.selectNoticeById(noticeId);
//
//        assertThat(findUpdatedNotice.getNoticeWriter()).isEqualTo(findNotice.getNoticeWriter());
//        assertThat(findUpdatedNotice.getNoticeContent()).isEqualTo(findNotice.getNoticeContent());
//        assertThat(findUpdatedNotice.getNoticeTitle()).isEqualTo(findNotice.getNoticeTitle());
//    }
//
//    @Test
//    void testDeleteNotice() {
//        NoticeBoardDto notice = new NoticeBoardDto();
//        notice.setCategoryId(1L);
//        notice.setNoticeWriter("admin");
//        notice.setNoticeTitle("Test Title");
//        notice.setNoticeContent("Test Content");
//
//        Long noticeId = noticeBoardService.register(notice);
//
//        noticeBoardService.deleteNotice(noticeId);
//
//        NoticeBoardDto findNotice = noticeBoardService.selectNoticeById(noticeId);
//
//        assertThat(findNotice).isNull();
//    }
}