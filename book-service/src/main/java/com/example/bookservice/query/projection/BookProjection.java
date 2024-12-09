package com.example.bookservice.query.projection;

import com.example.bookservice.command.data.Book;
import com.example.bookservice.command.data.BookRepository;
import com.example.bookservice.query.model.BookResponseModel;
import com.example.bookservice.query.queries.GetAllBookQuery;
import com.example.commonservice.model.BookResponseCommonModel;
import com.example.commonservice.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookProjection {
    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query) {
        List<Book> books = bookRepository.findAll();
        List<BookResponseModel> result =  books.stream().map(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book, bookResponseModel);
            return bookResponseModel;
        }).toList();

        return result;
    }

    @QueryHandler
    public BookResponseCommonModel handle(GetBookDetailQuery query) throws Exception {
        BookResponseCommonModel bookResponseModel = new BookResponseCommonModel();
        Book book = bookRepository.findById(query.getId()).orElseThrow(() -> new Exception("Book not found id: " + query.getId()));
        BeanUtils.copyProperties(book, bookResponseModel);
        return bookResponseModel;
    }

}
