package hibernate.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long skillId;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "level")
    private String level;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.MERGE},
                mappedBy = "skills",
                targetEntity = Developer.class)
    private Set<Developer> developers = new HashSet<>(0);

    public Skill() {
    }

    public Skill(long skillId, String skillName, String level) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.level = level;
    }

    public Skill(String skillName, String level) {
        this.skillName = skillName;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
