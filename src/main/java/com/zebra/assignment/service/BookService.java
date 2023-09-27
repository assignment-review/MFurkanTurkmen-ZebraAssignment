package com.zebra.assignment.service;

import com.zebra.assignment.dto.request.BookSaveRequestDto;
import com.zebra.assignment.dto.request.BookUpdateRequestDto;
import com.zebra.assignment.dto.response.BaseResponseDto;
import com.zebra.assignment.dto.response.BookResponseDto;
import com.zebra.assignment.dto.response.GetAllBookResponseDto;
import com.zebra.assignment.repository.entity.Book;
import com.zebra.assignment.exception.ErrorType;
import com.zebra.assignment.exception.ZebraException;
import com.zebra.assignment.mapper.IBookMapper;
import com.zebra.assignment.repository.BookRepository;
import com.zebra.assignment.utility.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService extends ServiceManager<Book, Long> {
    private final BookRepository repository;
    private final IBookMapper bookMapper;
    private final BookMemberService bookMemberService;
    Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository repository, IBookMapper bookMapper, BookMemberService bookMemberService) {
        super(repository);
        this.repository = repository;
        this.bookMapper = bookMapper;
        this.bookMemberService = bookMemberService;
    }

    public BookResponseDto saveBook(BookSaveRequestDto dto) {
        if (repository.existsByIsbn(dto.getIsbn())) throw new ZebraException(ErrorType.BOOK_ALREADY_EXISTS);
        Book book = save(bookMapper.toBook(dto));
        BookResponseDto responseDto = bookMapper.toDto(book);
        responseDto.setMessage("ok");
        responseDto.setStatusCode(200);
        logger.info("create a new book: {}.", book);
        return responseDto;
    }

    public GetAllBookResponseDto getAllBook() {
        List<BookResponseDto> dtos = findAll().stream().map(book -> {
            BookResponseDto dto = bookMapper.toDto(book);
            dto.setStatusCode(200);
            dto.setMessage("ok");
            return dto;
        }).toList();
        logger.info("get all book: {}.", dtos);
        return GetAllBookResponseDto.builder().books(dtos).message("ok").statusCode(200).build();
    }

    public BookResponseDto updateBook(BookUpdateRequestDto dto) {
        Book book = null;
        if ((dto.getId() != null)) {
            book = findById(dto.getId()).orElseThrow(() -> {
                throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ID);
            });
        } else if ((dto.getIsbn() != null)) {
            book = repository.findOptionalByIsbn(dto.getIsbn()).orElseThrow(() -> {
                throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ISBN);
            });
        }
        if (book == null) throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ID_ISBN);
        Book updateBook = bookMapper.toBook(dto);
        updateBook.setIsbn(book.getIsbn());
        updateBook.setId(book.getId());
        update(updateBook);
        logger.info("update book: {}.", updateBook);
        BookResponseDto responseDto = bookMapper.toDto(updateBook);
        responseDto.setMessage("ok");
        responseDto.setStatusCode(200);
        return responseDto;
    }

    public BaseResponseDto deleteBookByid(Long id) {
        if (findById(id).isEmpty()) throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ID);
        boolean isBorrowed = bookMemberService.existsByBookId(id);
        if (isBorrowed) throw new ZebraException(ErrorType.BOOK_CANNOT_BE_DELETED);
        deleteById(id);
        logger.info("deleted book with id {}", id);
        return BaseResponseDto.builder().statusCode(200).message("ok").build();
    }
    public BaseResponseDto deleteBookByIsbn(String isbn) {
        System.out.println("controller1");
        Book book = findByIsbn(isbn);
        System.out.println("controlle2");

        boolean isBorrowed = bookMemberService.existsByBookId(book.getId());
        System.out.println("controller3");

        if (isBorrowed) throw new ZebraException(ErrorType.BOOK_CANNOT_BE_DELETED);
        System.out.println("controlle4");

        delete(book);
        System.out.println("controller5");

        logger.info("delete book: {}.", book);
        return BaseResponseDto.builder().statusCode(200).message("ok").build();
    }

    public BaseResponseDto getBookByIsbn(String isbn) {
        Book book = repository.findOptionalByIsbn(isbn).orElseThrow(() -> {
            throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ISBN);
        });
        BaseResponseDto dto = bookMapper.toDto(book);
        dto.setStatusCode(200);
        dto.setMessage("ok");
        logger.info("get book by ISBN: {}. ", dto);
        return dto;
    }




    public Book findByIsbn(String isbn) {
        Optional<Book> book = repository.findOptionalByIsbn(isbn);
        if (book.isEmpty()) throw new ZebraException(ErrorType.BOOK_NOT_FOUND_ISBN);
        return book.get();
    }



}
