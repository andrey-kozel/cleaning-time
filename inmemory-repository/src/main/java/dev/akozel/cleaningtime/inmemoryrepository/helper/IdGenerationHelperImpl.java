package dev.akozel.cleaningtime.inmemoryrepository.helper;

import javax.inject.Named;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CommunityIdGenerationHelperImpl.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
@Named
public class IdGenerationHelperImpl implements IdGenerationHelper {

    private static final AtomicInteger LAST_GENERATED_ID = init();

    private static AtomicInteger init() {
        Long currentTimeInSeconds = Instant.now().getEpochSecond();
        return new AtomicInteger(currentTimeInSeconds.intValue());
    }

    @Override
    public Integer generateId() {
        return LAST_GENERATED_ID.getAndIncrement();
    }
}
