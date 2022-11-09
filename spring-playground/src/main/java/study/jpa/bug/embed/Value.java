package study.jpa.bug.embed;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
@Access(AccessType.FIELD) // 이게 필요함.
public class Value {

    @Column(name = "name")
    private String name;
}
