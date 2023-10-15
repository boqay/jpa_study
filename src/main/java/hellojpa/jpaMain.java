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
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            em.persist(member);

            //영속
            System.out.println("====== Before ======");
            em.persist(member); //이때 insert되지 않는다.
            System.out.println("====== After ======");

            Member findMember = em.find(Member.class, 101L); //DB에서 조회하지 않고 1차캐시에서 조회
            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            tx.commit(); //이때 실제 insert
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
