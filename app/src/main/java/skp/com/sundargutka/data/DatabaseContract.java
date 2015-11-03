package skp.com.sundargutka.data;

import android.provider.BaseColumns;

/**
 * Created by Sateesh Kumar on 10/25/2015.
 * In this class we are creating the classes for the tables that are required for to create
 * database. One internal class is for one table.
 */
public class DatabaseContract {


    public static final class ChapterEntry implements BaseColumns {
        //Name of the table
        public static final String TABLE_NAME = "chapter";

        //Name of the chapter
        public static final String COLUMN_CHAPTER_NAME = "chapter_name";

        //Column with number of sub chapters;
        public static final String COLUMN_TOTAL_SUB_CHAPTERS = "total_sub_chapters";
    }

    public static final class DetailEntry implements BaseColumns {
        //name of the table
        public static final String TABLE_NAME = "detail";

        //column with the foreign key from the chapter table
        public static final String COLUMN_CH_KEY = "chapter_id";

        //column with the name of the sub chapter
        public static final String COLUMN_SCH_NAME = "sub_chapter_name";

        //column with the details of the sub_chapter
        public static final String COLUMN_SCH_TEXT = "sub_chapter_text";
    }

}
