package dev.akozel.cleaningtime.core.common.time;

import javax.inject.Named;
import java.util.Date;

/**
 * TimeServiceImpl.
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Named
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    @Override
    public Date getCurrentTmeFromNow(Long millis) {
        return new Date(System.currentTimeMillis() + millis);
    }
}
