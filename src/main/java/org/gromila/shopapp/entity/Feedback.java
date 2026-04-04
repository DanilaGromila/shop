package org.gromila.shopapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "stars", nullable = false)
    private Integer stars;
}
