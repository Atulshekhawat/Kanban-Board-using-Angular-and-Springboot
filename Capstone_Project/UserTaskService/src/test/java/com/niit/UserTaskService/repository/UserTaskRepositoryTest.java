package com.niit.UserTaskService.repository;

        import com.niit.UserTaskService.domain.Task;
        import com.niit.UserTaskService.domain.User;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
        import org.springframework.test.context.junit.jupiter.SpringExtension;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserTaskRepositoryTest {
    @Autowired
    private UserTaskRepository userTaskRepository;
    private Task task;
    private User user;
    List<Task> taskList;

    @BeforeEach
    void setUp() {
//        task = new Task("1","Demo","To Be Done","Admin","25/12/2023","Low","Testing");
        user = new User("admin@123","Rohit","1234",taskList);
    }

    @AfterEach
    public void tearDown()
    {
        task = null;
        user = null;
        userTaskRepository.deleteAll();
    }
    @Test
    public void registerUserSuccess()
    {
        userTaskRepository.insert(user);
        User user1 = userTaskRepository.findById(user.getUserEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getUserEmail(),user1.getUserEmail());
    }
    @Test
    public void getTaskByUserEmailSuccess()
    {
        userTaskRepository.insert(user);
        User user1 = userTaskRepository.findById(user.getUserEmail()).get();
        assertNotNull(user1);
        assertEquals(user1.getUserEmail(),user.getUserEmail());
    }
    @Test
    public void deleteTaskByUserIDSuccess()
    {
        userTaskRepository.insert(user);
        User user1 = userTaskRepository.findById(user.getUserEmail()).get();
        userTaskRepository.delete(user1);
        assertEquals(Optional.empty(),userTaskRepository.findById(user.getUserEmail()));
    }
}