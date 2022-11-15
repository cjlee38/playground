package study.spring.event.aggregateroot;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MemberRoot extends AbstractAggregateRoot<MemberRoot> {

    @Id
    private Long id;

    public void login(String name) {
        registerEvent(new MemberLoginEvent(name));
    }
}

@Getter
@AllArgsConstructor
class MemberLoginEvent {
    private String name;
}

