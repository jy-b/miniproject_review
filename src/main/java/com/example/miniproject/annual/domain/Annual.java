package com.example.miniproject.annual.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.miniproject.annual.dto.AnnualRequestDto;
import com.example.miniproject.constant.Category;
import com.example.miniproject.constant.Reason;
import com.example.miniproject.constant.Status;
import com.example.miniproject.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reg")
public class Annual {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Category category;

	@NotBlank
	private String title;
	@NotNull
	private LocalDate startedAt;
	@NotNull
	private LocalDate lastedAt;

	@Enumerated(EnumType.STRING)
	private Reason reason;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime modifiedAt;

	public void updateData(AnnualRequestDto.UpdateDto updateDto) {
		this.title = updateDto.getTitle();
		this.startedAt = LocalDate.parse(updateDto.getStartDate());
		this.lastedAt = LocalDate.parse(updateDto.getEndDate());
		this.reason = Reason.findByName(updateDto.getReason());
	}
}
