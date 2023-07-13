package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.ApiResponseDto;
import com.example.jisoo_blog.dto.CommentRequestDto;
import com.example.jisoo_blog.dto.CommentResponseDto;
import com.example.jisoo_blog.security.UserDetailsImpl;
import com.example.jisoo_blog.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/JisooBlog")
public class CommentController {
	private final CommentService commentService;

	// 선택한 게시글에 대한 모든 댓글 조회
	@GetMapping("/view/{postId}/comments")
	public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(@PathVariable Long postId) {
		List<CommentResponseDto> responseDtos = commentService.getCommentsByPostId(postId);
		return ResponseEntity.ok().body(responseDtos);
	}

	// 댓글 작성
	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<ApiResponseDto> createComment(@PathVariable Long postId,
														@RequestBody CommentRequestDto requestDto,
														@AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			CommentResponseDto responseDto = commentService.createComment(postId, requestDto, userDetails.getUser());
			return ResponseEntity.ok().body(responseDto);
		} catch (EntityNotFoundException notFoundException) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponseDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value()));
		}
	}

	// 댓글 수정
	@PutMapping("/post/{postId}/comment/{id}")
	public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long postId,
														@PathVariable Long id,
														@RequestBody CommentRequestDto requestDto,
														@AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			CommentResponseDto result = commentService.updateComment(postId,id,requestDto,userDetails.getUser());
			return ResponseEntity.ok().body(result);
		} catch(EntityNotFoundException notFoundException){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponseDto(notFoundException.getMessage(),HttpStatus.NOT_FOUND.value()));
		} catch(RejectedExecutionException rejectedExecutionException){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponseDto(rejectedExecutionException.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	// 댓글 삭제
	@DeleteMapping("/post/{postId}/comment/{id}")
	public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long postId,
														@PathVariable Long id,
														@AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			commentService.deleteComment(postId,id,userDetails.getUser());
			return ResponseEntity.ok().body(new ApiResponseDto("해당 댓글의 삭제를 완료했습니다.", HttpStatus.OK.value()));
		} catch(EntityNotFoundException notFoundException){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponseDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value()));
		} catch ( RejectedExecutionException rejectedExecutionException){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponseDto(rejectedExecutionException.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}
}
