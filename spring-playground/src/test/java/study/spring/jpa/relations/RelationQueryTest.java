package study.spring.jpa.relations;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import study.spring.setups.Member;
import study.spring.setups.MemberRepository;
import study.spring.setups.Team;
import study.spring.setups.TeamRepository;
import study.util.EntityManagerUtils;

@DataJpaTest
@Import(EntityManagerUtils.class)
public class RelationQueryTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManagerUtils manager;

    @Test
    void 양방향인경우() {
        // given
        Team team = new Team(
                null,
                "team-name",
                List.of(new Member("member-A"), new Member("member-B"))
        );
        teamRepository.save(team);
        manager.init();
        // when
        /*
        select
            team0_.id as id1_1_0_,
            team0_.name as name2_1_0_
        from
            team team0_
        where
            team0_.id=?
        */
        Team findTeam = teamRepository.findById(team.getId()).orElseThrow();
        // then
        /*
        Hibernate:
        select
            members0_.team_id as team_id3_0_0_,
            members0_.id as id1_0_0_,
            members0_.id as id1_0_1_,
            members0_.name as name2_0_1_,
            members0_.team_id as team_id3_0_1_
        from
            member members0_
        where
            members0_.team_id=?
         */
        for (Member member : findTeam.getMembers()) {
            System.out.println("member = " + member);
        }
    }

    @Test
    void OneToMany_단방향_리스트인경우() {
        // given
        Team team = new Team(
                null,
                "team-name",
                List.of(new Member("member-A"), new Member("member-B"))
        );
        teamRepository.save(team);
        manager.init();
        // when
        /*
        Hibernate:
            select
                team0_.id as id1_1_0_,
                team0_.name as name2_1_0_
            from
                team team0_
            where
                team0_.id=?
        */
        Team findTeam = teamRepository.findById(team.getId()).orElseThrow();
        // then
        /*
        Hibernate:
        select
        members0_.team_id as team_id1_2_0_,
                members0_.members_id as members_2_2_0_,
        member1_.id as id1_0_1_,
                member1_.name as name2_0_1_
        from
        team_members members0_
        inner join
        member member1_
        on members0_.members_id = member1_.id
        where
        members0_.team_id =?
         */
        for (Member member : findTeam.getMembers()) {
            System.out.println("member = " + member);
        }
    }

    @Test
    void OneToMany_단방향_셋인경우() {
        // given
        Team team = new Team(
                null,
                "team-name",
                List.of()
//                Set.of(new Member("member-A"), new Member("member-B"))
        );
        teamRepository.save(team);
        manager.init();
        // when
        /*
        Hibernate:
            select
                team0_.id as id1_1_0_,
                team0_.name as name2_1_0_
            from
                team team0_
            where
                team0_.id=?
        */
        Team findTeam = teamRepository.findById(team.getId()).orElseThrow();
        // then
        /*
        Hibernate:
        select
        members0_.team_id as team_id1_2_0_,
                members0_.members_id as members_2_2_0_,
        member1_.id as id1_0_1_,
                member1_.name as name2_0_1_
        from
        team_members members0_
        inner join
        member member1_
        on members0_.members_id = member1_.id
        where
        members0_.team_id =?
         */
        for (Member member : findTeam.getMembers()) {
            System.out.println("member = " + member);
        }
    }
}
