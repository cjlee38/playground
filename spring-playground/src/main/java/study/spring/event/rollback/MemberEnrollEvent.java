package study.spring.event.rollback;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class MemberEnrollEvent {
    private final Long id;
}
