package hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "year_of_foundation")
    private int yearOfFoundation;

    @OneToMany(mappedBy = "company")
    private Set<Project> projects;

    public Company() {
    }

    public Company(String companyName, int yearOfFoundation) {
        this.companyName = companyName;
        this.yearOfFoundation = yearOfFoundation;
    }

    public Company(long companyId, String companyName, int yearOfFoundation) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.yearOfFoundation = yearOfFoundation;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", yearOfFoundation=" + yearOfFoundation +
                '}';
    }
}
