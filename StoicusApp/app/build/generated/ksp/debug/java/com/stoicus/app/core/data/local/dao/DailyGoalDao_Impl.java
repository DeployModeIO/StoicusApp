package com.stoicus.app.core.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.stoicus.app.core.data.local.entity.DailyGoal;
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
public final class DailyGoalDao_Impl implements DailyGoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyGoal> __insertionAdapterOfDailyGoal;

  private final EntityDeletionOrUpdateAdapter<DailyGoal> __updateAdapterOfDailyGoal;

  public DailyGoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyGoal = new EntityInsertionAdapter<DailyGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_goals` (`id`,`date`,`pillar`,`goalDescription`,`completed`,`completedAt`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyGoal entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        statement.bindString(3, entity.getPillar());
        statement.bindString(4, entity.getGoalDescription());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCompletedAt());
        }
      }
    };
    this.__updateAdapterOfDailyGoal = new EntityDeletionOrUpdateAdapter<DailyGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `daily_goals` SET `id` = ?,`date` = ?,`pillar` = ?,`goalDescription` = ?,`completed` = ?,`completedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyGoal entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        statement.bindString(3, entity.getPillar());
        statement.bindString(4, entity.getGoalDescription());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCompletedAt());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertGoal(final DailyGoal goal, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDailyGoal.insertAndReturnId(goal);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateGoal(final DailyGoal goal, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDailyGoal.handle(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyGoal>> getGoalsByDate(final String date) {
    final String _sql = "SELECT * FROM daily_goals WHERE date = ? ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_goals"}, new Callable<List<DailyGoal>>() {
      @Override
      @NonNull
      public List<DailyGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfGoalDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "goalDescription");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<DailyGoal> _result = new ArrayList<DailyGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final String _tmpGoalDescription;
            _tmpGoalDescription = _cursor.getString(_cursorIndexOfGoalDescription);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new DailyGoal(_tmpId,_tmpDate,_tmpPillar,_tmpGoalDescription,_tmpCompleted,_tmpCompletedAt);
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
  public Flow<List<DailyGoal>> getCompletedGoalsByDate(final String date) {
    final String _sql = "SELECT * FROM daily_goals WHERE date = ? AND completed = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_goals"}, new Callable<List<DailyGoal>>() {
      @Override
      @NonNull
      public List<DailyGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfGoalDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "goalDescription");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<DailyGoal> _result = new ArrayList<DailyGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final String _tmpGoalDescription;
            _tmpGoalDescription = _cursor.getString(_cursorIndexOfGoalDescription);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new DailyGoal(_tmpId,_tmpDate,_tmpPillar,_tmpGoalDescription,_tmpCompleted,_tmpCompletedAt);
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
