package com.yefan.study.boot.client.controller;

import com.yefan.study.boot.client.bean.Book;
import com.yefan.study.boot.client.enums.ResultCode;
import com.yefan.study.boot.client.response.Response;
import com.yefan.study.boot.client.response.Result;
import com.yefan.study.boot.client.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/queryByPage")
    public Result queryByPage(@RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum, @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {

        if ((pageNum - 1) < 0) {
            return Response.error("页码必须大于等于零");
        }
        if (pageSize <= 0) {
            return Response.error("页数必须大于零");
        }

        Page<Book> bookPage = bookService.queryByPage(pageNum - 1, pageSize);

        if (bookPage == null) {
            return Response.error();
        } else {
            if (bookPage.getContent() == null || bookPage.getContent().size() == 0) {
                return Response.ok(ResultCode.NO_AVABLIE_DATA.getMsg());
            }
        }
        return Response.ok(ResultCode.SUCCESS.getMsg(), bookPage.getContent());
    }

}
