package org.framework.studyboard;


import org.assertj.core.api.Assertions;
import org.framework.studyboard.application.dto.BoardWritingReqDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class StudyBoardTDDTest {

    @Test
    @DisplayName("게시글 작성시 비밀번호를 등록하면 비밀번호가 암호화 된다")
    void encPasswordTest() throws Exception {
        /// given
        BoardWritingReqDTO reqDTO = new BoardWritingReqDTO();
        String password = "1234";
        reqDTO.setPassword(password);

        // when
        // 비밀빈호 hash 암호화
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        byte[] hashReqDTO = digest.digest(reqDTO.getPassword().getBytes(StandardCharsets.UTF_8));

        // 바이트 배열을 16진수 문자열로 변환
        String hashPassword = HexFormat.of().formatHex(hash);
        String hashPassword2 = HexFormat.of().formatHex(hashReqDTO);

        // then
        Assertions.assertThat(hashPassword2).isEqualTo(hashPassword);
    }
}
