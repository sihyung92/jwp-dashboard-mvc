package com.techcourse.repository;

import com.techcourse.domain.Account;
import com.techcourse.domain.User;
import com.techcourse.exception.UserRuntimeException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryUserRepository {

    private static final Map<Long, User> database = new ConcurrentHashMap<>();
    private static final Map<Account, Long> indexTable = new ConcurrentHashMap<>();
    private static final AtomicLong id = new AtomicLong(0);

    private InMemoryUserRepository() {
    }

    static {
        final User user = new User(id.incrementAndGet(), new Account("gugu"), "password",
            "hkkang@woowahan.com");
        save(user);
    }

    public static void save(User user) {
        final User identifiedUser = user.newInstanceWithId(id.incrementAndGet());
        if (!Objects.isNull(indexTable.get(user.account()))) {
            throw new UserRuntimeException("존재하는 계정명 입니다.");
        }

        if (!Objects.isNull(database.get(identifiedUser.id()))) {
            throw new UserRuntimeException("존재하는 Primary Key 입니다.");
        }

        database.put(identifiedUser.id(), identifiedUser);
        indexTable.put(identifiedUser.account(), identifiedUser.id());
    }

    public static Optional<User> findByAccount(String account) {
        Long userId = indexTable.get(new Account(account));
        if (Objects.isNull(userId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(database.get(userId));
    }
}
