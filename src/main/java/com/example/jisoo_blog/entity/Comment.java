package com.example.jisoo_blog.entity;

import com.example.jisoo_blog.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", nullable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Column
	private long commentLike;

	@OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
	private List<CommentLike> commentLikes;

	@Column
	private String contents;

	public Comment(Post post, CommentRequestDto requestDto, User user) {
		this.post = post;
		this.user = user;
		this.contents = requestDto.getContents();
	}

	public void update(CommentRequestDto requestDto) {
		this.contents = requestDto.getContents();
	}

	public void addCommentLike() {
		this.commentLike++;
	}

	public void deleteCommentLike() {
		this.commentLike--;
	}
}
