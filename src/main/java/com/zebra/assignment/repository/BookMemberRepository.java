package com.zebra.assignment.repository;

import com.zebra.assignment.repository.entity.Book_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMemberRepository extends JpaRepository<Book_Member,Long> {
    Optional<Book_Member> findOptionalByBookIdAndMemberId(Long bookId,Long memberId);
    List<Book_Member> findByMemberId(Long memberId);
    boolean existsByBookId(Long bookId);
}
