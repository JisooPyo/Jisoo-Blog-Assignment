package com.example.jisoo_blog.service;

import com.example.jisoo_blog.dto.PostRequestDto;
import com.example.jisoo_blog.dto.PostResponseDto;
import com.example.jisoo_blog.entity.Post;
import com.example.jisoo_blog.entity.PostLike;
import com.example.jisoo_blog.entity.User;
import com.example.jisoo_blog.entity.UserRoleEnum;
import com.example.jisoo_blog.repository.PostLikeRepository;
import com.example.jisoo_blog.repository.PostRepository;
import com.example.jisoo_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final UserRepository userRepository;

	// 전체 게시글 조회
	public List<PostResponseDto> getPosts() {
		List<PostResponseDto> postList = postRepository.findAll().stream()
				.map(PostResponseDto::new)
				.collect(Collectors.toList());

		return postList;
	}

	// 선택 게시글 조회
	public PostResponseDto getPostById(Long id) {
		Post post = findPost(id);
		return new PostResponseDto(post);
	}

	// 포스트 작성
	public PostResponseDto createPost(PostRequestDto requestDto, User user) {
		User targetUser = findUser(user.getId());
		Post post = new Post(requestDto);
		post.setUser(targetUser);
		postRepository.save(post);
		return new PostResponseDto(post);
	}

	// 포스트 수정
	@Transactional
	public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
		Post post = findPost(id);
		User targetUser = findUser(user.getId());
		// 게시글 작성자(post.user) 와 요청자(user) 가 같은지 또는 Admin 인지 체크 (아니면 예외발생)
		if (hasPermission(targetUser, post)) {
			post.setTitle(requestDto.getTitle());
			post.setContents(requestDto.getContents());
		} else {
			throw new RejectedExecutionException();
		}
		return new PostResponseDto(post);
	}

	// 포스트 삭제
	public void deletePost(Long id, User user) {
		Post post = findPost(id);
		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, post)) {
			postRepository.delete(post);
		} else {
			throw new RejectedExecutionException();
		}
	}

	////////////////////////////////////////////////////////////////////////
	@Transactional
	// 좋아요 기능
	// 좋아요 추가
	public PostResponseDto addPostLike(Long id, User user) {
		Post post = findPost(id);
		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, post)) {
			throw new RejectedExecutionException("작성자/관리자는 좋아요를 누를 수 없습니다.");
		} else if (findPostLike(targetUser, post) != null) {
			throw new DataIntegrityViolationException("좋아요를 이미 누르셨습니다.");
		} else {
			postLikeRepository.save(new PostLike(targetUser, post));
			post.addPostLike();
			PostResponseDto responseDto = new PostResponseDto(postRepository.save(post));
			return responseDto;
		}
	}

	@Transactional
	// 좋아요 삭제
	public PostResponseDto deletePostLike(Long id, User user) {
		Post post = findPost(id);
		User targetUser = findUser(user.getId());
		if (hasPermission(targetUser, post)) {
			throw new RejectedExecutionException("작성자/관리자는 좋아요를 누를 수 없습니다.");
		} else if (findPostLike(targetUser, post) == null) {
			throw new NoSuchElementException("좋아요를 누르시지 않았습니다.");
		} else {
			postLikeRepository.delete(findPostLike(targetUser, post));
			post.deletePostLike();
			PostResponseDto postResponseDto = new PostResponseDto(postRepository.save(post));
			return postResponseDto;
		}
	}

	////////////////////////////////////////////////////////////////////////

	// id값으로 post 찾기
	public Post findPost(long id) {
		return postRepository.findById(id).orElseThrow(() ->
				new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
		);
	}

	// id값으로 user 찾기
	public User findUser(Long id) {
		return userRepository.findById(id).orElseThrow(() ->
				new IllegalArgumentException("선택한 유저는 존재하지 않습니다.")
		);
	}

	// 좋아요 찾기
	public PostLike findPostLike(User user, Post post) {
		return postLikeRepository.findByUserAndPost(user, post).orElse(null);
	}

	// user의 role이 admin이거나 post의 작성자가 user인가 확인하는 메서드
	public boolean hasPermission(User user, Post post) {
		if (user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().getId().equals(user.getId())) {
			return true;
		} else {
			return false;
		}
	}

}
