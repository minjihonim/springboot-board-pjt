package org.framework.studyboard;

import org.framework.studyboard.application.dto.BoardItemListResDTO;
import org.framework.studyboard.application.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers

class StudyBoardApplicationTests {

    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        mysql.start();
    }

    // Spring Boot에 동적으로 데이터베이스 정보 주입
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
    }

    @Autowired
    private BoardService boardService;

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
