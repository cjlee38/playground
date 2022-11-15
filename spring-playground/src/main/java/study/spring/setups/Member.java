package study.spring.setups;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private List<Locker> locker;

    public Member(String name) {
        this.name = name;
    }

    public Member(Long id, String name, List<Locker> locker) {
        this.id = id;
        this.name = name;
        this.locker = locker;
    }

    public Member(Long id, String name, Locker locker) {
        this.id = id;
        this.name = name;
        this.locker = List.of(locker);
    }

    @Override
    public String toString() {
        return "MemberRoot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
