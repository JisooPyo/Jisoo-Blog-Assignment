package com.example.jisoo_blog.entity;

import com.example.jisoo_blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Column
	private String contents;

	@Column
	private long postLike;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	public Post(PostRequestDto requestDto) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
	}

	public void addPostLike() {
		this.postLike++;
	}

	public void deletePostLike() {
		this.postLike--;
	}
}
