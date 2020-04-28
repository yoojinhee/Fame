package com.example.fame;

import android.provider.BaseColumns;

public class AlarmCategory {
    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME="AlarmCategory";
        public static final String COLUMN_NAME_HOUR="hour";
        public static final String COLUMN_NAME_MINUTE="minute";
        public static final String COLUMN_NAME_LEVEL="level";
        public static final String COLUMN_NAME_CATEGORY="category";
        public static final String COLUMN_NAME_INPUTCOUNT="inputcount";
//        public static final String COLUMN_NAME_MODE="mode";
        //public static final String COLUMN_NAME_INDEX="index";
    }
}
