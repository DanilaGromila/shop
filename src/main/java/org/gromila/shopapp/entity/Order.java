package org.gromila.shopapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    public Order() {

    }

    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user id='" + user.getId() + '\'' +
                ", order details=" + (orderDetails != null ? orderDetails.size() : 0) +
                ", payments=" + (payments != null ? payments.size() : 0) +
                '}';
    }
}
