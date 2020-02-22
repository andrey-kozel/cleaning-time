package dev.akozel.cleaningtime.core.common.time;

import java.util.Date;

/**
 * TimeService. Abstraction for working with dates
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public interface TimeService {

    Date getCurrentTime();

    Date getCurrentTmeFromNow(Long millis);

}
