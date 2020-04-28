package com.example.fame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstane;
    private static final int DB_VERSION=1;
    private static String DB_PATH = "";
    private static final String DB_NAME ="sqlite_fame.db";
    private final Context mContext;
    private SQLiteDatabase mDataBase;
    private SQLiteDatabase DB;
    boolean mDataBaseExist;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }
    public static DBHelper getInstance(Context context){
        if(sInstane==null){
            sInstane=new DBHelper(context);
        }
        return sInstane;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    public void createDataBase() throws IOException
    {
        //데이터베이스가 없으면 asset폴더에서 복사해온다.
        mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)//존재X
        {
          this.getReadableDatabase();
          this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e("","createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    ///data/data/your package/databases/Da Name <-이 경로에서 데이터베이스가 존재하는지 확인한다
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //assets폴더에서 데이터베이스를 복사한다.
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    //데이터베이스를 열어서 쿼리를 쓸수있게만든다.
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }


}