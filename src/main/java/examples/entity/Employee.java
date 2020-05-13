package examples.entity;

import examples.domain.Age;
import examples.domain.Salary;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.seasar.doma.Column;
import org.seasar.doma.Domain;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Transient;
import org.seasar.doma.Version;

@Entity(listener = EmployeeListener.class)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "EMPLOYEE_SEQ")
  Integer id;

  String name;

  Age age;

  Salary salary;

  @Column(name = "JOB_TYPE")
  JobType jobType;

  LocalDate hiredate;

  @Column(name = "DEPARTMENT_ID")
  Integer departmentId;

  @Version
  @Column(name = "VERSION")
  Integer version;

  Timestamp insertTimestamp;

  Timestamp updateTimestamp;

  @Transient Department department;

  @OriginalStates Employee originalStates;

  /* Add enum domain field */
  @Column(name = "HOGE_TYPE")
  @HogeAnnotation
  HogeType hogeType;

  @Target({ElementType.TYPE_USE})
  public @interface HogeAnnotation {}

  public HogeType getHogeType() {
    return hogeType;
  }

  public void setHogeType(HogeType hogeType) {
    this.hogeType = hogeType;
  }

  @Domain(valueType = Integer.class, factoryMethod = "of")
  public enum HogeType {
    A(1);

    private final Integer value;

    private HogeType(Integer value) {
      this.value = value;
    }

    public static HogeType of(Integer value) {
      for (HogeType hogeType : HogeType.values()) {
        if (hogeType.value.equals(value)) {
          return hogeType;
        }
      }
      throw new IllegalArgumentException();
    }

    public Integer getValue() {
      return value;
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Age getAge() {
    return age;
  }

  public void setAge(Age age) {
    this.age = age;
  }

  public Salary getSalary() {
    return salary;
  }

  public void setSalary(Salary salary) {
    this.salary = salary;
  }

  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  public LocalDate getHiredate() {
    return hiredate;
  }

  public void setHiredate(LocalDate hiredate) {
    this.hiredate = hiredate;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
  }

  public Timestamp getInsertTimestamp() {
    return insertTimestamp;
  }

  public void setInsertTimestamp(Timestamp insertTimestamp) {
    this.insertTimestamp = insertTimestamp;
  }

  public Timestamp getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(Timestamp updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", age="
        + age
        + ", salary="
        + salary
        + ", jobType="
        + jobType
        + ", hiredate="
        + hiredate
        + ", departmentId="
        + departmentId
        + ", version="
        + version
        + ", insertTimestamp="
        + insertTimestamp
        + ", updateTimestamp="
        + updateTimestamp
        + ", department="
        + department
        + '}';
  }
}
