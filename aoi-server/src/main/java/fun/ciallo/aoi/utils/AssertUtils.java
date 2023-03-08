package fun.ciallo.aoi.utils;

import fun.ciallo.aoi.common.AoiException;
import fun.ciallo.aoi.common.ApiStatus;

public class AssertUtils {
    public static void isNull(Object o, ApiStatus status) {
        if (null != o) {
            throw new AoiException(status);
        }
    }

    public static void notNull(Object o, ApiStatus status) {
        if (null == o) {
            throw new AoiException(status);
        }
    }

    public static void isTrue(boolean b, ApiStatus status) {
        if (!b) {
            throw new AoiException(status);
        }
    }

    public static void notTrue(boolean b, ApiStatus status) {
        if (b) {
            throw new AoiException(status);
        }
    }

    public static void equals(Object o1, Object o2, ApiStatus status) {
        if (!o1.equals(o2)) {
            throw new AoiException(status);
        }
    }

    public static void notEquals(Object o1, Object o2, ApiStatus status) {
        if (o1.equals(o2)) {
            throw new AoiException(status);
        }
    }
}
