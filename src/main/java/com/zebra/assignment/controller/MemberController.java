package com.zebra.assignment.controller;

import com.zebra.assignment.dto.request.BorrowBookRequestDto;
import com.zebra.assignment.dto.request.MemberSaveRequestDto;
import com.zebra.assignment.dto.request.ReturnBookRequestDto;
import com.zebra.assignment.dto.response.BaseResponseDto;
import com.zebra.assignment.dto.response.MemberResponseDto;
import com.zebra.assignment.dto.response.GetAllMemberResponseDto;
import com.zebra.assignment.dto.response.GetAllMemberResponseDto;
import com.zebra.assignment.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/borrower")
public class MemberController {
    private final MemberService borrowerService;
    @PostMapping("/")
    @Operation(summary = "Create a new member")
    public ResponseEntity<MemberResponseDto> saveMember(MemberSaveRequestDto dto){
        return ResponseEntity.ok(borrowerService.saveMember(dto));
    }
    @PostMapping("/borrow-book")
    public ResponseEntity<BaseResponseDto> borrowBook(BorrowBookRequestDto dto){
        return ResponseEntity.ok(borrowerService.borrowBook(dto));
    }
    @PostMapping("/return-book")
    public ResponseEntity<BaseResponseDto> returnBook(ReturnBookRequestDto dto){
        return ResponseEntity.ok(borrowerService.returnBook(dto));
    }
    @GetMapping("/getmember/{email}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("email") String email){
        return ResponseEntity.ok(borrowerService.getMemberByEmail(email));
    }

    @GetMapping("/getallmember")
    public ResponseEntity<GetAllMemberResponseDto> getAllMember(){
        return ResponseEntity.ok(borrowerService.getAllMember());
    }

    @GetMapping("/getallborrower")
    public ResponseEntity<GetAllMemberResponseDto> getAllMember(){
        return ResponseEntity.ok(borrowerService.getAllMember());
    }
}
