package org.framework.studyboard.infrastructure.persistence;

import org.framework.studyboard.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
