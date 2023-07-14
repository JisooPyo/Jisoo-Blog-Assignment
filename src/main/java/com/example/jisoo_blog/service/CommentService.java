package com.example.jisoo_blog.service;

import com.example.jisoo_blog.dto.CommentRequestDto;
import com.example.jisoo_blog.dto.CommentResponseDto;
import com.example.jisoo_blog.entity.*;
import com.example.jisoo_blog.repository.CommentLikeRepository;
import com.example.jisoo_blog.repository.CommentRepository;
import com.example.jisoo_blog.repository.PostRepository;
import com.example.jisoo_blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final CommentLikeRepository commentLikeRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;


	public List<CommentResponseDto> getCommentsByPostId(Long postId) {
		return commentRepository.findAllByPostOrderByCreatedAtDesc(findPost(postId))
				.stream()
				.map(CommentResponseDto::new)
				.toList();
	}

	public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
		User targetUser = findUser(user.getId());
		Post post = findPost(postId);
		Comment comment = new Comment(post, requestDto, targetUser);
		Comment saveComment = commentRepository.save(comment);
		CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
		return commentResponseDto;
	}

	@Transactional
	public CommentResponseDto updateComment(Long postId, Long id, CommentRequestDto requestDto, User user) {
		Comment comment = findComment(id);
		// postId 받은 것과 comment의 postId가 다를 경우
		if (postId != comment.getPost().getId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}
		// 유저의 Role에 따라 경우 나누기
		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, comment)) {
			comment.update(requestDto);
		}
		CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
		return commentResponseDto;
	}

	public void deleteComment(Long postId, Long id, User user) {
		Comment comment = findComment(id);
		if (postId != comment.getPost().getId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}
		// 유저의 Role에 따라 경우 나누기
		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, comment)) {
			commentRepository.deleteById(id);
		}
	}

	////////////////////////////////////////////////////////////////////
	@Transactional
	// 댓글 좋아요
	public CommentResponseDto addCommentLike(Long postId, Long id, User user) {
		Post post = findPost(postId);
		Comment comment = findComment(id);

		if (postId != comment.getPost().getId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}

		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, comment)) {
			throw new RejectedExecutionException("작성자/관리자는 좋아요를 누를 수 없습니다.");
		} else if (findCommentLike(targetUser, comment) != null) {
			throw new DataIntegrityViolationException("좋아요를 이미 누르셨습니다.");
		} else {
			commentLikeRepository.save(new CommentLike(targetUser, comment, post));
			comment.addCommentLike();
			CommentResponseDto responseDto = new CommentResponseDto(commentRepository.save(comment));
			return responseDto;
		}

	}

	// 댓글 좋아요 취소
	public CommentResponseDto deleteCommentLike(Long postId, Long id, User user) {
		Post post = findPost(postId);
		Comment comment = findComment(id);

		if (postId != comment.getPost().getId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}

		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, comment)) {
			throw new RejectedExecutionException("작성자/관리자는 좋아요를 누를 수 없습니다.");
		} else if (findCommentLike(targetUser, comment) == null) {
			throw new DataIntegrityViolationException("좋아요를 이미 취소하셨습니다.");
		} else {
			commentLikeRepository.save(new CommentLike(targetUser, comment, post));
			comment.deleteCommentLike();
			CommentResponseDto responseDto = new CommentResponseDto(commentRepository.save(comment));
			return responseDto;
		}
	}

	////////////////////////////////////////////////////////////////////

	public Post findPost(Long id) {
		return postRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException("선택한 게시글은 존재하지 않습니다."));
	}

	public User findUser(Long id) {
		return userRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException("선택한 유저는 존재하지 않습니다.")
		);
	}

	public Comment findComment(Long id) {
		return commentRepository.findById(id).orElseThrow(() ->
				new EntityNotFoundException("선택한 댓글은 존재하지 않습니다.")
		);
	}

	public boolean hasPermission(User user, Comment comment) {
		if (user.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().getId().equals(user.getId())) {
			return true;
		} else {
			return false;
		}
	}

	public CommentLike findCommentLike(User user, Comment comment) {
		return commentLikeRepository.findByUserAndComment(user, comment).orElse(null);
	}


}
