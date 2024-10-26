package csit.semit.nyr.webappsnyrlab21.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "new_post_ttn")
public class NewPostTTN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Client receiver;

    @Column(name = "manager", nullable = false)
    private String manager;

    @Column(name = "num_point", nullable = false)
    private int numPoint;

    @Column(name = "kod_ttn", nullable = false, length = 20)
    private String kodTTN;

    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeliveryStatus status;

    // Enum for Delivery Status
    public enum DeliveryStatus {
        SENT,
        RECEIVED,
        RETURNED
    }

    // Constructors
    public NewPostTTN() {}

    public NewPostTTN(Client receiver, String manager, int numPoint, String kodTTN, LocalDateTime sendTime, DeliveryStatus status) {
        this.receiver = receiver;
        this.manager = manager;
        this.numPoint = numPoint;
        this.kodTTN = kodTTN;
        this.sendTime = sendTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getNumPoint() {
        return numPoint;
    }

    public void setNumPoint(int numPoint) {
        this.numPoint = numPoint;
    }

    public String getKodTTN() {
        return kodTTN;
    }

    public void setKodTTN(String kodTTN) {
        this.kodTTN = kodTTN;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "NewPostTTN{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", manager='" + manager + '\'' +
                ", numPoint=" + numPoint +
                ", kodTTN='" + kodTTN + '\'' +
                ", sendTime=" + sendTime +
                ", status=" + status +
                '}';
    }
}