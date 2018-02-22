package hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @Column(name = "developer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long developerId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "salary")
    private int salary;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.MERGE},
                targetEntity = Skill.class)
    @JoinTable(name = "developers_skills",
                joinColumns = @JoinColumn(name = "developer_id"),
                inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.MERGE,
                targetEntity = Project.class)
    @JoinTable(name = "developers_projects",
                joinColumns = @JoinColumn(name = "id_developer"),
                inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<Project> projects;

    public Developer() {
    }

    public Developer(String name, int age, String sex, int salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    public Developer(long developerId, String name, int age, String sex, int salary) {
        this.developerId = developerId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "developerId=" + developerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                ", skills=" + skills +
                '}';
    }
}
