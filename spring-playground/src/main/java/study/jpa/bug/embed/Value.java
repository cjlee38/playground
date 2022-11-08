package study.jpa.bug.embed;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
@Access(AccessType.FIELD) // 이게 필요함.
public class Value {

    private String name;
}
