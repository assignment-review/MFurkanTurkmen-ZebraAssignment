package com.zebra.assignment.controller;

import com.zebra.assignment.dto.request.BorrowBookRequestDto;
import com.zebra.assignment.dto.request.MemberSaveRequestDto;
import com.zebra.assignment.dto.request.ReturnBookRequestDto;
import com.zebra.assignment.dto.response.BaseResponseDto;
import com.zebra.assignment.dto.response.GetAllBorrowerResponseDto;
import com.zebra.assignment.dto.response.MemberResponseDto;
import com.zebra.assignment.dto.response.GetAllMemberResponseDto;
import com.zebra.assignment.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/")
    @Operation(summary = "Create a new member")
    public ResponseEntity<MemberResponseDto> saveMember(MemberSaveRequestDto dto){
        return ResponseEntity.ok(memberService.saveMember(dto));
    }
    @PostMapping("/borrow-book")
    @Operation(summary = "Borrow books by entering member ID and book isbn")
    public ResponseEntity<BaseResponseDto> borrowBook(BorrowBookRequestDto dto){
        return ResponseEntity.ok(memberService.borrowBook(dto));
    }
    @PostMapping("/return-book")
    @Operation(summary = "Return book sby entering member ID and book isbn")
    public ResponseEntity<BaseResponseDto> returnBook(ReturnBookRequestDto dto){
        return ResponseEntity.ok(memberService.returnBook(dto));
    }
    @GetMapping("/getmember/{email}")
    @Operation(summary = "Get the member by email")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("email") String email){
        return ResponseEntity.ok(memberService.getMemberByEmail(email));
    }

    @GetMapping("/getallmember")
    @Operation(summary = "get all members")
    public ResponseEntity<GetAllMemberResponseDto> getAllMember(){
        return ResponseEntity.ok(memberService.getAllMember());
    }

    @GetMapping("/getallborrower")
    @Operation(summary = "get all just borrowers")
    public ResponseEntity<GetAllBorrowerResponseDto> getAllBorrower(){
        return ResponseEntity.ok(memberService.getAllBorrower());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete member and his/her borrow. books are taken back")
    public ResponseEntity<BaseResponseDto> deleteBorrower(@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.deleteMember(id));
    }
}
