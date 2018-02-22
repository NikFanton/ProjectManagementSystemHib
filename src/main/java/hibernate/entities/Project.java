package hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "cost")
    private int cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE},
            mappedBy = "projects",
            targetEntity = Developer.class)
    private Set<Developer> developers;

    public Project() {
    }

    public Project(String projectName, int cost, Customer customer, Company company) {
        this.projectName = projectName;
        this.cost = cost;
        this.customer = customer;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", cost=" + cost +
                ", \n\tcustomer=" + customer +
                ", \n\tcompany=" + company +
                ", \n\tdevelopers=" + developers +
                '}';
    }
}
