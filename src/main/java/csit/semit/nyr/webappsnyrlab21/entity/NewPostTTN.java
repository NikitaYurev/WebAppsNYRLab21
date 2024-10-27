package csit.semit.nyr.webappsnyrlab21.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "newpostttn")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPostTTN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Client receiver;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private int numPoint;

    @Column(nullable = false, length = 20)
    private String kodTTN;

    @Column(nullable = false)
    private LocalDateTime sendTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    public enum DeliveryStatus {
        SENT, RECEIVED, RETURNED
    }
}
