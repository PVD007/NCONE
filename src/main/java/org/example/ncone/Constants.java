package org.example.ncone;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {

    public static final class DataBinding {
        public static final class TimeRange extends Range<Long> {
            public Range<Long> toDefault() {
                return new Range<>(getMin(), getMax());
            }
        }

        public static final class TimeRangeList extends ArrayList<TimeRange> {
        }

        public static final class Map {
            public static final class Default extends HashMap<String, Object> {
            }
        }
    }

}
