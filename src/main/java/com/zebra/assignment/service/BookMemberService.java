package com.zebra.assignment.service;

import com.zebra.assignment.exception.ErrorType;
import com.zebra.assignment.exception.ZebraException;
import com.zebra.assignment.mapper.IBookMemberMapper;
import com.zebra.assignment.repository.BookMemberRepository;
import com.zebra.assignment.repository.entity.Book_Member;
import com.zebra.assignment.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookMemberService extends ServiceManager<Book_Member,Long> {
    private final BookMemberRepository repository;
    private final IBookMemberMapper bookMemberMapper;

    public BookMemberService(BookMemberRepository repository, IBookMemberMapper bookMemberMapper) {
        super(repository);
        this.repository = repository;
        this.bookMemberMapper = bookMemberMapper;
    }
    public void saveBookMember(Long borrowerId, Long bookId){
        if (borrowerId==null || bookId==null) throw new ZebraException(ErrorType.INTERNAL_SERVER_ERROR,"BookMemberService: save methot");
        Optional<Book_Member> bookMember= repository.findOptionalByBookIdAndMemberId(bookId,borrowerId);
        if (bookMember.isEmpty()){
             Book_Member newBookMember= save(Book_Member.builder()
                    .bookId(bookId)
                    .borrowerId(borrowerId)
                    .build());
        }else throw new RuntimeException("kullanici bu kitabi daha önce almis");

    }


    public void deleteBookMember(Long borrowerId,Long bookId){
        Book_Member bookMember= repository.findOptionalByBookIdAndMemberId(bookId,borrowerId)
                .orElseThrow(()->{throw new ZebraException(ErrorType.DEBT_NOT_FOUND);});
        delete(bookMember);
    }

    public List<Book_Member> getAllMember(){
        return findAll();
    }
    
    
}
