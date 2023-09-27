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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService extends ServiceManager<Member, Long> {
    private final MemberRepository repository;
    private final BookService bookService;
    private final IMemberMapper borrowerMapper;
    private final BookMemberService bookMemberService;

    public MemberService(MemberRepository repository, BookService bookService, IMemberMapper borrowerMapper, BookMemberService bookMemberService) {
        super(repository);
        this.repository = repository;
        this.bookService = bookService;
        this.borrowerMapper = borrowerMapper;
        this.bookMemberService = bookMemberService;
    }

    public MemberResponseDto saveMember(MemberSaveRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())) throw new ZebraException(ErrorType.EMAIL_ALREADY_EXISTS);
        Member borrower = borrowerMapper.toMember(dto);
        MemberResponseDto responseDto = borrowerMapper.toDto(save(borrower));
        responseDto.setMessage("ok");
        responseDto.setStatusCode(200);
        return responseDto;
    }

    public BaseResponseDto borrowBook(BorrowBookRequestDto dto) {
        Book book = bookService.findByIsbn(dto.getIsbn());
        Member borrower = findById(dto.getMemberId()).orElseThrow(() -> {
            throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);
        });
        if (book.getCopies() <= 0) return null;
        book.setCopies(book.getCopies() - 1);
        bookService.update(book);
        bookMemberService.saveBookMember(borrower.getId(), book.getId());
        return BaseResponseDto.builder().statusCode(200).message("ok").build();
    }

    public BaseResponseDto returnBook(ReturnBookRequestDto dto) {
        Member borrower=findById(dto.getMemberId()).orElseThrow(()->{throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);});
        Book book= bookService.findByIsbn(dto.getIsbn());
        book.setCopies(book.getCopies()+1);
        bookMemberService.deleteBookMember(borrower.getId(), book.getId());
        return BaseResponseDto.builder().message("ok").statusCode(200).build();
    }

    public MemberResponseDto getMemberByEmail(String email){
        Optional<Member> borrower= repository.findOptionalByEmail(email);
        if (borrower.isEmpty()) throw new ZebraException(ErrorType.BORROWER_NOT_FOUND);
        MemberResponseDto responseDto= borrowerMapper.toDto(borrower.get());
        responseDto.setStatusCode(200);
        responseDto.setMessage("ok");
        return responseDto;
    }


    public GetAllMemberResponseDto getAllMember() {

        List<MemberResponseDto> dtos= findAll().stream().map(borrower -> {
            MemberResponseDto dto= borrowerMapper.toDto(borrower);
            dto.setMessage("ok");
            dto.setStatusCode(200);
            return dto;
        }).toList();

        GetAllMemberResponseDto responseDto= GetAllMemberResponseDto.builder()
                .dtos(dtos)
                .statusCode(200)
                .message("ok")
                .build();
        return responseDto;
    }

    public GetAllMemberResponseDto getAllMember() {
        List<Book_Member> bookMember=bookMemberService.getAllMember();
        List<Member> borrowers= new ArrayList<>();
        for (Book_Member borrower: bookMember){
            Optional<Member> brrwr= findById(borrower.getId());
            borrowers.add(brrwr.get());
        }
        List<MemberResponseDto> dtos= borrowers.stream().map(borrower -> {
            MemberResponseDto responseDto= borrowerMapper.toDto(borrower);
            responseDto.setStatusCode(200);
            responseDto.setMessage("ok");
            return responseDto;
        }).toList();

        return GetAllMemberResponseDto.builder().dtos(dtos).message("ok").statusCode(200).build();
    }


}
