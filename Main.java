import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        File f = new File("C:\\Users\\TD\\MyBatis\\config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(f);
        EmployeeDao ed = new EmployeeDao(factory);
        Employee e = ed.getEmployeeById(113);
        //e.firstName = "Anna";
        //int res = ed.updateEmployee(e);
        List<Employee> eL = ed.getAllEmployees();
        List<Employee> eLJ = ed.getEmployeesByJob(6);
        System.out.println(e.firstName);
    }
}

interface EmployeeMapper {

    @Select(value = "SELECT * FROM employees WHERE employee_id=#{value}")
    Employee getEmployeeById(int id);

    @Select(value = "SELECT * FROM employees WHERE job_id = #{value}")
    List<Employee> getEmployeesByJob(int id);

    @Select(value = "SELECT * FROM employees")
    List<Employee> getAllEmployees();

    @Insert(value = """
            INSERT INTO employees
            (first_name,last_name,email,phone_number,hire_date,job_id,salary,manager_id,department_id)
            VALUES ("#{firstName},#{lastName},#{email},#{phoneNumber},#{hireDate},#{jobId},#{salary},#{managerId},#{departmentId}")""")
    int addEmployee(Employee e);

    @Update(value = "UPDATE employees SET first_name=#{firstName}, last_name=#{lastName} WHERE employee_id =#{id}")
    boolean updateEmployee(Employee e);

    @Delete(value = "DELETE FROM employees WHERE employee_id =#{id}")
    boolean deleteEmployee(int id);
}



@Retention(RetentionPolicy.RUNTIME)
@interface Select {
    String value();
}
@Retention(RetentionPolicy.RUNTIME)
@interface Insert {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Delete {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Update {
    String value();
}
