package efub.session.blog.account.controller;

import efub.session.blog.account.domain.Account;
import efub.session.blog.account.dto.response.AccountCommentsResponseDto;
import efub.session.blog.account.service.AccountService;
import efub.session.blog.comment.domain.Comment;
import efub.session.blog.comment.dto.CommentResponseDto;
import efub.session.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// url: /accounts/{accountId}/comments
@RestController
@RequestMapping("/accounts/{accountId}/comments")
@RequiredArgsConstructor
public class AccountCommentController {

    // 의존관계 : AccountCommentController -> CommentService
    private final CommentService commentService;

    // 의존관계 : AccountCommentController -> AccountService
    private final AccountService accountService;

    // 특정 유저의 댓글 목록 조회
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public AccountCommentsResponseDto responseDto(@PathVariable Long accountId) {
        Account writer = accountService.findAccountById(accountId);
        List<Comment> commentList = commentService.findCommentListByWriter(writer);
        return AccountCommentsResponseDto.of(writer.getNickname(), commentList);
    }

}
