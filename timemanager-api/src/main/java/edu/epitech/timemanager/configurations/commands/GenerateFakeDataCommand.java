package edu.epitech.timemanager.configurations.commands;

import com.github.javafaker.Faker;
import edu.epitech.timemanager.domains.models.Clock;
import edu.epitech.timemanager.domains.models.Team;
import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.domains.models.WorkingTime;
import edu.epitech.timemanager.domains.models.enumerations.Role;
import edu.epitech.timemanager.persistence.TeamRepository;
import edu.epitech.timemanager.persistence.UserRepository;
import edu.epitech.timemanager.persistence.WorkingTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenerateFakeDataCommand implements ApplicationListener<ApplicationReadyEvent> {

    private final Faker faker = new Faker(new Locale(Locale.FRANCE.getLanguage()));

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final WorkingTimeRepository workingTimeRepository;
    private final TeamRepository teamRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        long count = userRepository.count();
        if (count > 0) return;

        log.info("Generating fake data...");
        List<User> managers = new ArrayList<>();
        List<User> employees = new ArrayList<>();
        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            log.info("Generating manager {}", i);
            User manager = generateFakeManager();
            if (manager != null)
            managers.add(manager);
        }

        for (int i = 0; i < 100; i++) {
            User employee = generateFakeEmployee();

            if (employee == null) {
                continue;
            }

            employees.add(employee);
            log.info("Employee generated with id = {}", employee.getId());
        }

        for (User manager : managers) {
            log.info("Generating team for manager {}", manager.getUsername());
            int numberOfTeam = faker.number().numberBetween(1, 3);

            for (int i = 0; i < numberOfTeam; i++) {
                teams.add(generateFakeTeam(manager));
            }

            log.info("Generating working times for manager {}", manager.getUsername());
            generateFakeWorkingTimes(manager);
        }

        for (User employee : employees) {
            int teamIndex = faker.number().numberBetween(0, teams.size());
            Team team = teams.get(teamIndex);
            log.info("Set employee ({}) {} in team {}", employee.getId(), employee.getUsername(), team.getName());
            team.addMember(employee);

            log.info("Generating working times for employee ({}) {}", employee.getId(), employee.getUsername());
            generateFakeWorkingTimes(employee);
        }

        log.info("Generate all teams");
        teamRepository.saveAll(teams);

        log.info("Generating fake data done.");
    }

    private User generateFakeUser(Role role) {
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(user.getUsername().toLowerCase() + "@timemanager.com");
        user.setHashedPassword(passwordEncoder.encode("password"));
        user.setRole(role);
        user.setClock(generateFakeClock(user));

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while saving user {}", user.getUsername(), e);
            return null;
        }

        return user;
    }

    private User generateFakeEmployee() {
        return generateFakeUser(Role.EMPLOYEE);
    }

    private User generateFakeManager() {
        return generateFakeUser(Role.MANAGER);
    }

    private void generateFakeWorkingTimes(User user) {
        List<WorkingTime> workingTimes = new ArrayList<>();

        for (int i = 0; i < faker.number().numberBetween(2, 25); i++) {
            workingTimes.add(generateFakeWorkingTime(user));
        }

        workingTimeRepository.saveAll(workingTimes);
    }

    private WorkingTime generateFakeWorkingTime(User user) {
        int day = faker.number().numberBetween(1, 30);
        int hour = faker.number().numberBetween(9, 15);
        int minute = faker.number().numberBetween(0, 59);

        boolean isMorning = hour < 12;
        if (!isMorning && hour < 13) {
            hour = 13;
        }

        Date start = getDate(2021, 11, day, hour, minute, 0);
        Date endLimit = getDate(2021, 11, day, isMorning ? 12 : 17, 0, 0);
        Date end = faker.date().between(start, endLimit);

        return new WorkingTime(start, end, user);
    }

    private Clock generateFakeClock(User user) {
        Clock clock = new Clock();
        clock.setUser(user);
        clock.setEnable(faker.bool().bool());

        int day = faker.number().numberBetween(1, 30);
        int hour = faker.number().numberBetween(9, 16);
        int minute = faker.number().numberBetween(0, 59);

        clock.setEnabledAt(getDate(2021, 11, day, hour, minute, 0));

        return clock;
    }

    private Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Team generateFakeTeam(User manager) {
        Team team = new Team();
        team.setName(faker.name().name());
        team.setDescription(faker.lorem().sentence(16));
        team.addMember(manager);
        return team;
    }
}
