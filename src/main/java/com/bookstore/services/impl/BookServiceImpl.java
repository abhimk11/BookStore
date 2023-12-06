package com.bookstore.services.impl;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Mono<Book> create(Book book) {
        Mono<Book> saved = bookRepository.save(book);
        return saved;
    }

    @Override
    public Flux<Book> getAll() {
        Flux<Book> all = bookRepository.findAll().delayElements(Duration.ofSeconds(2));
        return all;
    }

    @Override
    public Mono<Book> get(int bookId) {
        Mono<Book> byId = bookRepository.findById(bookId);
        return byId;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById(bookId);
        Mono<Book> savedBook = oldBook.flatMap(book1 -> {
            book1.setName(book.getName());
            book1.setAuthor(book.getAuthor());
            book1.setPublisher(book.getPublisher());
            book1.setDescription(book.getDescription());
            return bookRepository.save(book1);
        });
        return savedBook;
    }

    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.findById(bookId).flatMap(
                book -> bookRepository.delete(book)
        );
    }

    @Override
    public Flux<Book> search(String query) {

        return null;
    }
}
