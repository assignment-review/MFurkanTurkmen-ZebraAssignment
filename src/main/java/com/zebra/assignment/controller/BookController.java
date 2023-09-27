package com.zebra.assignment.controller;

        import com.zebra.assignment.dto.request.BookSaveRequestDto;
        import com.zebra.assignment.dto.request.BookUpdateRequestDto;
        import com.zebra.assignment.dto.response.BaseResponseDto;
        import com.zebra.assignment.dto.response.BookResponseDto;
        import com.zebra.assignment.dto.response.GetAllBookResponseDto;
        import com.zebra.assignment.repository.entity.Book;
        import com.zebra.assignment.service.BookService;
        import io.swagger.v3.oas.annotations.Operation;
        import jakarta.validation.Valid;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;

        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    @Operation(summary = "create a new book")
    public ResponseEntity<BookResponseDto> saveBook(@Valid @RequestBody BookSaveRequestDto dto){
        return ResponseEntity.ok(bookService.saveBook(dto));
    }
    @GetMapping("")
    @Operation(summary = "get all books")
    public ResponseEntity<GetAllBookResponseDto> getAllBook(){
        return ResponseEntity.ok(bookService.getAllBook());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete the book with given ID")
    public ResponseEntity<BaseResponseDto> deleteBookById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookService.deleteBookByid(id ));
    }

    @DeleteMapping("/isbn")
    @Operation(summary = "delete the book with given ISBN")
    public ResponseEntity<BaseResponseDto> deleteBookByIsbn(@RequestParam String isbn){
        System.out.println("controller");
        return ResponseEntity.ok(bookService.deleteBookByIsbn(isbn));
    }

    @PutMapping("/")
    @Operation(summary = "Update the book with given id or given ISBN")
    public ResponseEntity<BaseResponseDto> updateBook(@RequestBody BookUpdateRequestDto dto){
        return ResponseEntity.ok(bookService.updateBook(dto));
    }

    @GetMapping("/{isbn}")
    @Operation(summary = "Get the book given ISBN")
    public ResponseEntity<BaseResponseDto> getBookByIsbn(@PathVariable("isbn") String isbn){
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }



}
