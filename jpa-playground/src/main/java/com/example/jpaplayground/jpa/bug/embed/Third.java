package com.example.jpaplayground.jpa.bug.embed;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Third {

    @Embedded
    @Access(AccessType.FIELD)
    private Value name;
}
