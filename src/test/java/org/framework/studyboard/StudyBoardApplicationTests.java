package org.framework.studyboard;

import org.framework.studyboard.application.dto.BoardItemListResDTO;
import org.framework.studyboard.application.dto.BoardWritingReqDTO;
import org.framework.studyboard.application.dto.BoardWritingResDTO;
import org.framework.studyboard.application.service.BoardService;
import org.framework.studyboard.global.common.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class StudyBoardApplicationTests {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        mysql.start();
    }

    // Spring Boot에 동적으로 데이터베이스 정보 주입
    // SpringBootTest 어노테이션 적용 후 테스트 실행 시 기본적으로 application.properties 를 로드함
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);

        // Hibernate가 테이블을 자동으로 생성하도록 설정
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.jpa.properties.hibernate.format_sql", () -> "true");
        registry.add("spring.jpa.show-sql", () -> "true");
        registry.add("spring.jpa.database", () -> "mysql");  // JPA Database를 MySQL로 지정
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.MySQL8Dialect");
    }

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시글 작성 API")
    void insertBoardContent() throws Exception {
        /// given
        BoardWritingReqDTO reqDTO = new BoardWritingReqDTO();
        reqDTO.setSubject("테스트글");
        reqDTO.setContent("테스트입니다.");
        reqDTO.setWriter("홍길동");
        reqDTO.setPassword("1234");

        // when
        BoardWritingResDTO result = boardService.insertBoardWriting(reqDTO);

        // then
        assertThat(result.getCode()).isEqualTo(ErrorCode.SUCCESS.getCode());
    }

    @Test
    @DisplayName("게시판 목록 조회 테스트")
    void getBoardListTest() throws Exception {
        // given
        int page = 1;

        // when
        List<BoardItemListResDTO> result = boardService.getBoardItemList(page);

        // then
        assertThat(result).hasSize(0);
    }

}
