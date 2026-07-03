package com.stoicus.app.core.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.stoicus.app.core.data.local.entity.MoodEntry;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MoodEntryDao_Impl implements MoodEntryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MoodEntry> __insertionAdapterOfMoodEntry;

  private final EntityDeletionOrUpdateAdapter<MoodEntry> __updateAdapterOfMoodEntry;

  public MoodEntryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMoodEntry = new EntityInsertionAdapter<MoodEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `mood_entries` (`id`,`moodScore`,`stressLevel`,`energyLevel`,`reflection`,`timestamp`,`date`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MoodEntry entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getMoodScore());
        statement.bindLong(3, entity.getStressLevel());
        statement.bindLong(4, entity.getEnergyLevel());
        statement.bindString(5, entity.getReflection());
        statement.bindLong(6, entity.getTimestamp());
        statement.bindString(7, entity.getDate());
      }
    };
    this.__updateAdapterOfMoodEntry = new EntityDeletionOrUpdateAdapter<MoodEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `mood_entries` SET `id` = ?,`moodScore` = ?,`stressLevel` = ?,`energyLevel` = ?,`reflection` = ?,`timestamp` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MoodEntry entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getMoodScore());
        statement.bindLong(3, entity.getStressLevel());
        statement.bindLong(4, entity.getEnergyLevel());
        statement.bindString(5, entity.getReflection());
        statement.bindLong(6, entity.getTimestamp());
        statement.bindString(7, entity.getDate());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertMoodEntry(final MoodEntry entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMoodEntry.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMoodEntry(final MoodEntry entry,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMoodEntry.handle(entry);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MoodEntry>> getRecentMoodEntries() {
    final String _sql = "SELECT * FROM mood_entries ORDER BY timestamp DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mood_entries"}, new Callable<List<MoodEntry>>() {
      @Override
      @NonNull
      public List<MoodEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final int _cursorIndexOfStressLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "stressLevel");
          final int _cursorIndexOfEnergyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "energyLevel");
          final int _cursorIndexOfReflection = CursorUtil.getColumnIndexOrThrow(_cursor, "reflection");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<MoodEntry> _result = new ArrayList<MoodEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MoodEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            final int _tmpStressLevel;
            _tmpStressLevel = _cursor.getInt(_cursorIndexOfStressLevel);
            final int _tmpEnergyLevel;
            _tmpEnergyLevel = _cursor.getInt(_cursorIndexOfEnergyLevel);
            final String _tmpReflection;
            _tmpReflection = _cursor.getString(_cursorIndexOfReflection);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item = new MoodEntry(_tmpId,_tmpMoodScore,_tmpStressLevel,_tmpEnergyLevel,_tmpReflection,_tmpTimestamp,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<MoodEntry> getMoodByDate(final String date) {
    final String _sql = "SELECT * FROM mood_entries WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mood_entries"}, new Callable<MoodEntry>() {
      @Override
      @Nullable
      public MoodEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final int _cursorIndexOfStressLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "stressLevel");
          final int _cursorIndexOfEnergyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "energyLevel");
          final int _cursorIndexOfReflection = CursorUtil.getColumnIndexOrThrow(_cursor, "reflection");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final MoodEntry _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            final int _tmpStressLevel;
            _tmpStressLevel = _cursor.getInt(_cursorIndexOfStressLevel);
            final int _tmpEnergyLevel;
            _tmpEnergyLevel = _cursor.getInt(_cursorIndexOfEnergyLevel);
            final String _tmpReflection;
            _tmpReflection = _cursor.getString(_cursorIndexOfReflection);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _result = new MoodEntry(_tmpId,_tmpMoodScore,_tmpStressLevel,_tmpEnergyLevel,_tmpReflection,_tmpTimestamp,_tmpDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Float> getAverageMood(final long startTime, final long endTime) {
    final String _sql = "SELECT AVG(moodScore) FROM mood_entries WHERE timestamp BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mood_entries"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Float> getAverageStress(final long startTime, final long endTime) {
    final String _sql = "SELECT AVG(stressLevel) FROM mood_entries WHERE timestamp BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"mood_entries"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
