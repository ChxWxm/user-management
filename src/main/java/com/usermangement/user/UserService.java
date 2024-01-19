package com.usermangement.user;

import com.usermangement.mail.MailService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    List<User> users = new ArrayList<>(
            List.of(
                    new User(1, "John", 20, true),
                    new User(2, "Jane", 29, false),
                    new User(3, "Alice", 34, true)
            )
    );
    private final MailService mailService;

    public UserService(MailService mailService) {
        this.mailService = mailService;
    }

    public List<User> getUsers(Optional<Boolean> active) {
        if (active.isPresent()) {
            return users.stream().filter(u -> u.getActive() == active.get()).toList();
        }
        return users;
    }

    public User createUsers(UserRequest request) {
        Optional<Integer> maxId = users.stream().map(User::getId).max(Integer::compareTo);
        int currentId = maxId.orElse(0) + 1;

        //  INFO: my version
        //  User user = new User(users.size() + 1, request.name(), request.age(), true);
        User user = new User(currentId, request.name(), request.age(), true);
        users.add(user);

        //  Sent Email
        mailService.sendMail("dev@gmail.com", "User Created");
        return user;
    }

    public void updateUsers(int id, UserRequest request) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        if (user.isPresent()) {
            User u = user.get();
            u.setName(request.name());
        }
    }

    public void deleteUsers(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}
