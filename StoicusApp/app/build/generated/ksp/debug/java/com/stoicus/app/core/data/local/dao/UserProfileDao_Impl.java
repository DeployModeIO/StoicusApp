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
import com.stoicus.app.core.data.local.converters.Converters;
import com.stoicus.app.core.data.local.entity.UserProfile;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfile> __insertionAdapterOfUserProfile;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<UserProfile> __updateAdapterOfUserProfile;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfile = new EntityInsertionAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profiles` (`id`,`name`,`selectedPillars`,`biggestChallenge`,`preferredTime`,`onboardingCompleted`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfile entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.fromStringList(entity.getSelectedPillars());
        statement.bindString(3, _tmp);
        statement.bindString(4, entity.getBiggestChallenge());
        statement.bindString(5, entity.getPreferredTime());
        final int _tmp_1 = entity.getOnboardingCompleted() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        statement.bindLong(7, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfUserProfile = new EntityDeletionOrUpdateAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profiles` SET `id` = ?,`name` = ?,`selectedPillars` = ?,`biggestChallenge` = ?,`preferredTime` = ?,`onboardingCompleted` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfile entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.fromStringList(entity.getSelectedPillars());
        statement.bindString(3, _tmp);
        statement.bindString(4, entity.getBiggestChallenge());
        statement.bindString(5, entity.getPreferredTime());
        final int _tmp_1 = entity.getOnboardingCompleted() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertProfile(final UserProfile profile,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserProfile.insertAndReturnId(profile);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProfile(final UserProfile profile,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserProfile.handle(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProfile> getProfile() {
    final String _sql = "SELECT * FROM user_profiles LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profiles"}, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSelectedPillars = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedPillars");
          final int _cursorIndexOfBiggestChallenge = CursorUtil.getColumnIndexOrThrow(_cursor, "biggestChallenge");
          final int _cursorIndexOfPreferredTime = CursorUtil.getColumnIndexOrThrow(_cursor, "preferredTime");
          final int _cursorIndexOfOnboardingCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "onboardingCompleted");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final List<String> _tmpSelectedPillars;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfSelectedPillars);
            _tmpSelectedPillars = __converters.toStringList(_tmp);
            final String _tmpBiggestChallenge;
            _tmpBiggestChallenge = _cursor.getString(_cursorIndexOfBiggestChallenge);
            final String _tmpPreferredTime;
            _tmpPreferredTime = _cursor.getString(_cursorIndexOfPreferredTime);
            final boolean _tmpOnboardingCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfOnboardingCompleted);
            _tmpOnboardingCompleted = _tmp_1 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new UserProfile(_tmpId,_tmpName,_tmpSelectedPillars,_tmpBiggestChallenge,_tmpPreferredTime,_tmpOnboardingCompleted,_tmpCreatedAt);
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
