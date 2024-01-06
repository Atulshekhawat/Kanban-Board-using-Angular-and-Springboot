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


}