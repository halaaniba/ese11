import com.example.ese11.entities.Student;
import com.example.ese11.repository.StudentRepository;
import com.example.ese11.service.StudentService;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class TestStudentService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentWorking() throws Exception {
        Student student = new Student();
        student.setWorking(true);
        student.setName("Hala");
        student.setSurname("Aniba");

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.changeWorkingStatus(student.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

        Student studentFromFind = studentRepository.findById(studentFromDB.getId()).get();
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull();
        assertThat(studentFromFind.getId()).isEqualTo(studentFromDB.getId());
        assertThat(studentFromService.isWorking()).isTrue();

    }
}

