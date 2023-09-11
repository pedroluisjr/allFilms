package br.com.allfilms.film.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "historic")
public class Historic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historicId")
    private long historicId;

    @Column(name = "review")
    private String review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "movieId")
    private Long movieId;

    @Column(name = "createdAt", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Date updatedAt;
}
