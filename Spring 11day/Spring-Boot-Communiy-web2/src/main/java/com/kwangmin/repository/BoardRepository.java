package com.kwangmin.repository;

import com.kwangmin.domain.Board;
import com.kwangmin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
