package hanghaeclone8a7.twotead.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code; // 식별번호
    @Column(nullable = false)
    private String name; // 상위직군
}
