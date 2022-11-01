package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //트랙잭션 단위로 움직임
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try { //code영역

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("곽은지");
            book.setPrice(15000);

            em.persist(book);

            /*
            Member member = new Member();
            member.setName("곽은지");
            em.persist(member);

            Item item1 = new Item();
            item1.setName("아메리카노");
            item1.setPrice(2000);
            em.persist(item1);

            Item item2 = new Item();
            item2.setName("샌드위치");
            item2.setPrice(3000);
            em.persist(item2);

            Order order1 = new Order();
            order1.addMember(member);
            em.persist(order1);

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setItem(item1);
            orderItem1.addOrder(order1);
            em.persist(orderItem1);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setItem(item2);
            orderItem2.addOrder(order1);
            em.persist(orderItem2);

            em.flush();
            em.clear();

            List<Order> orders = member.getOrders();
            for (Order order : orders) {
                System.out.println("-----------------------------------");
                System.out.println("주문자 : " + order.getMember().getName());
                List<OrderItem> orderItems = order.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    System.out.println(orderItem.getItem().getName() + " => " + orderItem.getItem().getPrice() + "원");
                }
            }
            */

            tx.commit();    //DB에 쿼리 날림
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
