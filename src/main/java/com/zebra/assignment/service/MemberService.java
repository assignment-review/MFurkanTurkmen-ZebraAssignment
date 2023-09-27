package com.zebra.assignment.service;

import com.zebra.assignment.dto.request.BorrowBookRequestDto;
import com.zebra.assignment.dto.request.MemberSaveRequestDto;
import com.zebra.assignment.dto.request.ReturnBookRequestDto;
import com.zebra.assignment.dto.response.*;
import com.zebra.assignment.exception.ErrorType;
import com.zebra.assignment.exception.ZebraException;
import com.zebra.assignment.mapper.IMemberMapper;
import com.zebra.assignment.repository.MemberRepository;
import com.zebra.assignment.repository.entity.Book;
import com.zebra.assignment.repository.entity.Book_Member;
import com.zebra.assignment.repository.entity.Member;
import com.zebra.assignment.utility.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService extends ServiceManager<Member, Long> {
    private final MemberRepository repository;
    private final BookService bookService;
    private final IMemberMapper memberMapper;
    private final BookMemberService bookMemberService;
    Logger logger = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository repository, BookService bookService, IMemberMapper memberMapper, BookMemberService bookMemberService) {
        super(repository);
        this.repository = repository;
        this.bookService = bookService;
        this.memberMapper = memberMapper;
        this.bookMemberService = bookMemberService;
    }

    public MemberResponseDto saveMember(MemberSaveRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())) throw new ZebraException(ErrorType.EMAIL_ALREADY_EXISTS);
        Member member = memberMapper.toMember(dto);
        MemberResponseDto responseDto = memberMapper.toDto(save(member));
        responseDto.setMessage("ok");
        responseDto.setStatusCode(200);
        logger.info("create a new member: {}.",member);
        return responseDto;
    }

    public BaseResponseDto borrowBook(BorrowBookRequestDto dto) {
        Book book = bookService.findByIsbn(dto.getIsbn());
        Member member = findById(dto.getMemberId()).orElseThrow(() -> {
            throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);
        });
        if (book.getCopies() <= 0) return null;
        book.setCopies(book.getCopies() - 1);
        bookService.update(book);
        bookMemberService.saveBookMember(member.getId(), book.getId());
        logger.info("borrow book: member: {}. -> book: {}.",member,book);
        return BaseResponseDto.builder().statusCode(200).message("ok").build();
    }

    public BaseResponseDto returnBook(ReturnBookRequestDto dto) {
        Member member=findById(dto.getMemberId()).orElseThrow(()->{throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);});
        Book book= bookService.findByIsbn(dto.getIsbn());
        book.setCopies(book.getCopies()+1);
        bookMemberService.deleteBookMember(member.getId(), book.getId());
        logger.info("return book: member: {}. -> book: {}.",member,book);
        return BaseResponseDto.builder().message("ok").statusCode(200).build();
    }

    public MemberResponseDto getMemberByEmail(String email){
        Optional<Member> member= repository.findOptionalByEmail(email);
        if (member.isEmpty()) throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);
        MemberResponseDto responseDto= memberMapper.toDto(member.get());
        responseDto.setStatusCode(200);
        responseDto.setMessage("ok");
        logger.info("get member by email: {}.",member);
        return responseDto;
    }


    public GetAllMemberResponseDto getAllMember() {

        List<MemberResponseDto> dtos= findAll().stream().map(member -> {
            MemberResponseDto dto= memberMapper.toDto(member);
            dto.setMessage("ok");
            dto.setStatusCode(200);
            return dto;
        }).toList();

        GetAllMemberResponseDto responseDto= GetAllMemberResponseDto.builder()
                .dtos(dtos)
                .statusCode(200)
                .message("ok")
                .build();
        logger.info("get all member: {}.",dtos);
        return responseDto;
    }

    public GetAllBorrowerResponseDto getAllBorrower() {
        List<Book_Member> bookMember=bookMemberService.getAllMember();
        Set<Member> members= new HashSet<>();

        for (Book_Member book_member: bookMember){
            Optional<Member> member= findById(book_member.getMemberId());
            members.add(member.get());
        }

        List<MemberResponseDto> dtos= members.stream().map(member -> {
            MemberResponseDto responseDto= memberMapper.toDto(member);
            responseDto.setStatusCode(200);
            responseDto.setMessage("ok");
            return responseDto;
        }).toList();

        logger.info("get all borrower: {}.",dtos);
        return GetAllBorrowerResponseDto.builder().dtos(dtos).message("ok").statusCode(200).build();
    }


    public BaseResponseDto deleteMember(Long id) {
        Member member=findById(id).orElseThrow(()->{throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);});
        List<Book_Member> bookMembers= bookMemberService.findByMemberId(member.getId());
        for (Book_Member book_member: bookMembers){
            Optional<Book> book=bookService.findById(book_member.getBookId());
            book.get().setCopies(book.get().getCopies()+1);
            bookService.update(book.get());
            bookMemberService.delete(book_member);
        }
        delete(member);
        return BaseResponseDto.builder().statusCode(200).message("ok").build();
    }
}
