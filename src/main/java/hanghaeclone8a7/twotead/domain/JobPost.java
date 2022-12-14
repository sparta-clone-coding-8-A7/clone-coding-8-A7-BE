package hanghaeclone8a7.twotead.domain;

import hanghaeclone8a7.twotead.dto.request.JobPostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobPost extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String deadline;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Column(nullable = false)
    private Long jobGroupId;

    @Column(nullable = false)
    private Long jobDetailId;

    @Column
    @Builder.Default
    private int heart = 0; // 좋아요

    @Column
    private String imgUrl;

    @Column(nullable = false)
    @Builder.Default
    private boolean invalid = true; // 마감일 지났으면 false (안보임)

    /*@OneToMany(mappedBy = "jobPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPostImgUrl> imgList;*/

    public void update(JobPostRequestDto jobPostRequestDto){
        this.position = jobPostRequestDto.getPosition();
        this.content = jobPostRequestDto.getContent();
        this.deadline = jobPostRequestDto.getDeadline();

        String imgUrl = "";
        if(jobPostRequestDto.getImgUrlList().size() == 0){
            imgUrl = "https://hanghae7zo.s3.ap-northeast-2.amazonaws.com/basicImage.png";
        } else {
            imgUrl = jobPostRequestDto.getImgUrlList().get(0);
        }

        this.imgUrl = imgUrl;
        this.jobGroupId = jobPostRequestDto.getJobGroupId();
        this.jobDetailId = jobPostRequestDto.getJobDetailId();
    }

    public void heartUpdate(List<Heart> heartList){
        this.heart = heartList.size();
    }
}
