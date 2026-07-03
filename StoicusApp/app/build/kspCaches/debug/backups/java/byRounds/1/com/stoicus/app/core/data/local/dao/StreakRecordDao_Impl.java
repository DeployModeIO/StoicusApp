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
import com.stoicus.app.core.data.local.entity.StreakRecord;
import java.lang.Class;
import java.lang.Exception;
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
public final class StreakRecordDao_Impl implements StreakRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StreakRecord> __insertionAdapterOfStreakRecord;

  private final EntityDeletionOrUpdateAdapter<StreakRecord> __updateAdapterOfStreakRecord;

  public StreakRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStreakRecord = new EntityInsertionAdapter<StreakRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `streak_records` (`id`,`date`,`morningRitualCompleted`,`eveningRitualCompleted`,`microActionsCompleted`,`moodScore`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StreakRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        final int _tmp = entity.getMorningRitualCompleted() ? 1 : 0;
        statement.bindLong(3, _tmp);
        final int _tmp_1 = entity.getEveningRitualCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp_1);
        statement.bindLong(5, entity.getMicroActionsCompleted());
        statement.bindLong(6, entity.getMoodScore());
      }
    };
    this.__updateAdapterOfStreakRecord = new EntityDeletionOrUpdateAdapter<StreakRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `streak_records` SET `id` = ?,`date` = ?,`morningRitualCompleted` = ?,`eveningRitualCompleted` = ?,`microActionsCompleted` = ?,`moodScore` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StreakRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        final int _tmp = entity.getMorningRitualCompleted() ? 1 : 0;
        statement.bindLong(3, _tmp);
        final int _tmp_1 = entity.getEveningRitualCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp_1);
        statement.bindLong(5, entity.getMicroActionsCompleted());
        statement.bindLong(6, entity.getMoodScore());
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertStreak(final StreakRecord streak,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfStreakRecord.insertAndReturnId(streak);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStreak(final StreakRecord streak,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStreakRecord.handle(streak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StreakRecord>> getRecentStreaks() {
    final String _sql = "SELECT * FROM streak_records ORDER BY date DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"streak_records"}, new Callable<List<StreakRecord>>() {
      @Override
      @NonNull
      public List<StreakRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMorningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "morningRitualCompleted");
          final int _cursorIndexOfEveningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningRitualCompleted");
          final int _cursorIndexOfMicroActionsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "microActionsCompleted");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final List<StreakRecord> _result = new ArrayList<StreakRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StreakRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpMorningRitualCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMorningRitualCompleted);
            _tmpMorningRitualCompleted = _tmp != 0;
            final boolean _tmpEveningRitualCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEveningRitualCompleted);
            _tmpEveningRitualCompleted = _tmp_1 != 0;
            final int _tmpMicroActionsCompleted;
            _tmpMicroActionsCompleted = _cursor.getInt(_cursorIndexOfMicroActionsCompleted);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            _item = new StreakRecord(_tmpId,_tmpDate,_tmpMorningRitualCompleted,_tmpEveningRitualCompleted,_tmpMicroActionsCompleted,_tmpMoodScore);
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
  public Flow<StreakRecord> getStreakByDate(final String date) {
    final String _sql = "SELECT * FROM streak_records WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"streak_records"}, new Callable<StreakRecord>() {
      @Override
      @Nullable
      public StreakRecord call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMorningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "morningRitualCompleted");
          final int _cursorIndexOfEveningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningRitualCompleted");
          final int _cursorIndexOfMicroActionsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "microActionsCompleted");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final StreakRecord _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpMorningRitualCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMorningRitualCompleted);
            _tmpMorningRitualCompleted = _tmp != 0;
            final boolean _tmpEveningRitualCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEveningRitualCompleted);
            _tmpEveningRitualCompleted = _tmp_1 != 0;
            final int _tmpMicroActionsCompleted;
            _tmpMicroActionsCompleted = _cursor.getInt(_cursorIndexOfMicroActionsCompleted);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            _result = new StreakRecord(_tmpId,_tmpDate,_tmpMorningRitualCompleted,_tmpEveningRitualCompleted,_tmpMicroActionsCompleted,_tmpMoodScore);
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
  public Flow<List<StreakRecord>> getCompletedDays() {
    final String _sql = "SELECT * FROM streak_records WHERE morningRitualCompleted = 1 OR eveningRitualCompleted = 1 ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"streak_records"}, new Callable<List<StreakRecord>>() {
      @Override
      @NonNull
      public List<StreakRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfMorningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "morningRitualCompleted");
          final int _cursorIndexOfEveningRitualCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningRitualCompleted");
          final int _cursorIndexOfMicroActionsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "microActionsCompleted");
          final int _cursorIndexOfMoodScore = CursorUtil.getColumnIndexOrThrow(_cursor, "moodScore");
          final List<StreakRecord> _result = new ArrayList<StreakRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StreakRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final boolean _tmpMorningRitualCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMorningRitualCompleted);
            _tmpMorningRitualCompleted = _tmp != 0;
            final boolean _tmpEveningRitualCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEveningRitualCompleted);
            _tmpEveningRitualCompleted = _tmp_1 != 0;
            final int _tmpMicroActionsCompleted;
            _tmpMicroActionsCompleted = _cursor.getInt(_cursorIndexOfMicroActionsCompleted);
            final int _tmpMoodScore;
            _tmpMoodScore = _cursor.getInt(_cursorIndexOfMoodScore);
            _item = new StreakRecord(_tmpId,_tmpDate,_tmpMorningRitualCompleted,_tmpEveningRitualCompleted,_tmpMicroActionsCompleted,_tmpMoodScore);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
