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
import com.stoicus.app.core.data.local.entity.RitualSession;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class RitualSessionDao_Impl implements RitualSessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RitualSession> __insertionAdapterOfRitualSession;

  private final EntityDeletionOrUpdateAdapter<RitualSession> __deletionAdapterOfRitualSession;

  public RitualSessionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRitualSession = new EntityInsertionAdapter<RitualSession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ritual_sessions` (`id`,`pillar`,`exerciseType`,`durationMinutes`,`completedAt`,`completed`,`reflection`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RitualSession entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPillar());
        statement.bindString(3, entity.getExerciseType());
        statement.bindLong(4, entity.getDurationMinutes());
        statement.bindLong(5, entity.getCompletedAt());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getReflection());
      }
    };
    this.__deletionAdapterOfRitualSession = new EntityDeletionOrUpdateAdapter<RitualSession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `ritual_sessions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RitualSession entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertSession(final RitualSession session,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRitualSession.insertAndReturnId(session);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSession(final RitualSession session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRitualSession.handle(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RitualSession>> getAllSessions() {
    final String _sql = "SELECT * FROM ritual_sessions ORDER BY completedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ritual_sessions"}, new Callable<List<RitualSession>>() {
      @Override
      @NonNull
      public List<RitualSession> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfExerciseType = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseType");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfReflection = CursorUtil.getColumnIndexOrThrow(_cursor, "reflection");
          final List<RitualSession> _result = new ArrayList<RitualSession>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RitualSession _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final String _tmpExerciseType;
            _tmpExerciseType = _cursor.getString(_cursorIndexOfExerciseType);
            final int _tmpDurationMinutes;
            _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final String _tmpReflection;
            _tmpReflection = _cursor.getString(_cursorIndexOfReflection);
            _item = new RitualSession(_tmpId,_tmpPillar,_tmpExerciseType,_tmpDurationMinutes,_tmpCompletedAt,_tmpCompleted,_tmpReflection);
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
  public Flow<List<RitualSession>> getSessionsByDate(final long date) {
    final String _sql = "SELECT * FROM ritual_sessions WHERE date(completedAt/1000, 'unixepoch') = date(?/1000, 'unixepoch')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ritual_sessions"}, new Callable<List<RitualSession>>() {
      @Override
      @NonNull
      public List<RitualSession> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfExerciseType = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseType");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfReflection = CursorUtil.getColumnIndexOrThrow(_cursor, "reflection");
          final List<RitualSession> _result = new ArrayList<RitualSession>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RitualSession _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final String _tmpExerciseType;
            _tmpExerciseType = _cursor.getString(_cursorIndexOfExerciseType);
            final int _tmpDurationMinutes;
            _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final String _tmpReflection;
            _tmpReflection = _cursor.getString(_cursorIndexOfReflection);
            _item = new RitualSession(_tmpId,_tmpPillar,_tmpExerciseType,_tmpDurationMinutes,_tmpCompletedAt,_tmpCompleted,_tmpReflection);
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
  public Flow<Integer> getSessionCountByPillar(final String pillar) {
    final String _sql = "SELECT COUNT(*) FROM ritual_sessions WHERE pillar = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pillar);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ritual_sessions"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
