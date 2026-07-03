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
import com.stoicus.app.core.data.local.entity.MicroAction;
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
public final class MicroActionDao_Impl implements MicroActionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MicroAction> __insertionAdapterOfMicroAction;

  private final EntityDeletionOrUpdateAdapter<MicroAction> __deletionAdapterOfMicroAction;

  private final EntityDeletionOrUpdateAdapter<MicroAction> __updateAdapterOfMicroAction;

  public MicroActionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMicroAction = new EntityInsertionAdapter<MicroAction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `micro_actions` (`id`,`actionType`,`description`,`completed`,`completedAt`,`date`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MicroAction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getActionType());
        statement.bindString(3, entity.getDescription());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getCompletedAt());
        }
        statement.bindString(6, entity.getDate());
      }
    };
    this.__deletionAdapterOfMicroAction = new EntityDeletionOrUpdateAdapter<MicroAction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `micro_actions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MicroAction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMicroAction = new EntityDeletionOrUpdateAdapter<MicroAction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `micro_actions` SET `id` = ?,`actionType` = ?,`description` = ?,`completed` = ?,`completedAt` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MicroAction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getActionType());
        statement.bindString(3, entity.getDescription());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getCompletedAt());
        }
        statement.bindString(6, entity.getDate());
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertAction(final MicroAction action,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMicroAction.insertAndReturnId(action);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAction(final MicroAction action,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMicroAction.handle(action);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAction(final MicroAction action,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMicroAction.handle(action);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MicroAction>> getActionsByDate(final String date) {
    final String _sql = "SELECT * FROM micro_actions WHERE date = ? ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"micro_actions"}, new Callable<List<MicroAction>>() {
      @Override
      @NonNull
      public List<MicroAction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<MicroAction> _result = new ArrayList<MicroAction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MicroAction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpActionType;
            _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
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
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item = new MicroAction(_tmpId,_tmpActionType,_tmpDescription,_tmpCompleted,_tmpCompletedAt,_tmpDate);
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
  public Flow<List<MicroAction>> getCompletedActionsByDate(final String date) {
    final String _sql = "SELECT * FROM micro_actions WHERE date = ? AND completed = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"micro_actions"}, new Callable<List<MicroAction>>() {
      @Override
      @NonNull
      public List<MicroAction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<MicroAction> _result = new ArrayList<MicroAction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MicroAction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpActionType;
            _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
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
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item = new MicroAction(_tmpId,_tmpActionType,_tmpDescription,_tmpCompleted,_tmpCompletedAt,_tmpDate);
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
