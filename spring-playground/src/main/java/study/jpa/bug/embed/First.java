package study.jpa.bug.embed;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * embeddable이 2중이면 property base로 동작한다. -> setter를 필요로 한다. 이를 해결하려면 @Access 어노테이션으로 Field를 주거나, setter를 추가하는 두 가지 방법 중
 * 하나를 선택한다. 에러 메세지는 다음과 같다. Caused by: org.hibernate.PropertyNotFoundException: Could not locate setter method for
 * property [study.jpa.bug.embed.Value#name] 이는 hibernate가 가진 버그이다. 아래를 참고하자.
 * https://stackoverflow.com/questions/18441222/issue-with-jpa-mapping-for-two-nested-embeddable
 * https://discourse.hibernate.org/t/elementcollection-with-nested-embeddable/6821
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class First {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Second> seconds;
}
