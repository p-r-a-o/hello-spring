package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //findAny() -> 하나라도 찾는 것
        //루프를 다 돌면서 조건에 부합하는 맵에서 하나라도 찾아지면 반환
        //끝까지 돌았는데 없으면 Optional에 Null이 포함이 되어서 반환
    }

    @Override public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
