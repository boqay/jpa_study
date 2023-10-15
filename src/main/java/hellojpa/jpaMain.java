package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("jpaname");
            //jpa는 sql개념이 아닌 객체를 사용하기 때문에 CRUD를 해서 반영하는 개념이 아니다.
//            em.persist();
            System.out.println("======================");

            //스냅샷과 Entity를 비교해서 다르면 sql을 쓰기지연 sql저장소로 보낸다.
            tx.commit();
         } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
