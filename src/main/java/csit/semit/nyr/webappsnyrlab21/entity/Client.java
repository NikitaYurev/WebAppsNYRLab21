package csit.semit.nyr.webappsnyrlab21.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "First name must be provided.")
    private String firstName;

    @Column(nullable = false)
    @NotNull(message = "Last name must be provided.")
    private String secondName;

    @Column(nullable = false)
    @NotNull(message = "Region must be specified.")
    private String region;

    @Column(nullable = false)
    @NotNull(message = "City must be specified.")
    private String city;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Phone must be provided.")
    @Pattern(regexp = "\\+\\d{12}", message = "Phone must follow the pattern +NNNNNNNNNNNN.")
    private String phone;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Email must be provided.")
    @Email(message = "Email should be valid.")
    private String email;
}

