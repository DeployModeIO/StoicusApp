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
import com.stoicus.app.core.data.local.entity.StoicImage;
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
public final class StoicImageDao_Impl implements StoicImageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StoicImage> __insertionAdapterOfStoicImage;

  private final EntityDeletionOrUpdateAdapter<StoicImage> __deletionAdapterOfStoicImage;

  private final EntityDeletionOrUpdateAdapter<StoicImage> __updateAdapterOfStoicImage;

  public StoicImageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStoicImage = new EntityInsertionAdapter<StoicImage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stoic_images` (`id`,`title`,`description`,`imageUrl`,`category`,`philosopher`,`favorited`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicImage entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getImageUrl());
        statement.bindString(5, entity.getCategory());
        if (entity.getPhilosopher() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPhilosopher());
        }
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(7, _tmp);
      }
    };
    this.__deletionAdapterOfStoicImage = new EntityDeletionOrUpdateAdapter<StoicImage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `stoic_images` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicImage entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfStoicImage = new EntityDeletionOrUpdateAdapter<StoicImage>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `stoic_images` SET `id` = ?,`title` = ?,`description` = ?,`imageUrl` = ?,`category` = ?,`philosopher` = ?,`favorited` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicImage entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getImageUrl());
        statement.bindString(5, entity.getCategory());
        if (entity.getPhilosopher() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPhilosopher());
        }
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertImage(final StoicImage image, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfStoicImage.insertAndReturnId(image);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertImages(final List<StoicImage> images,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStoicImage.insert(images);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteImage(final StoicImage image, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfStoicImage.handle(image);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateImage(final StoicImage image, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStoicImage.handle(image);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StoicImage>> getAllImages() {
    final String _sql = "SELECT * FROM stoic_images ORDER BY RANDOM()";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_images"}, new Callable<List<StoicImage>>() {
      @Override
      @NonNull
      public List<StoicImage> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPhilosopher = CursorUtil.getColumnIndexOrThrow(_cursor, "philosopher");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final List<StoicImage> _result = new ArrayList<StoicImage>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicImage _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpPhilosopher;
            if (_cursor.isNull(_cursorIndexOfPhilosopher)) {
              _tmpPhilosopher = null;
            } else {
              _tmpPhilosopher = _cursor.getString(_cursorIndexOfPhilosopher);
            }
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            _item = new StoicImage(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpCategory,_tmpPhilosopher,_tmpFavorited);
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
  public Flow<List<StoicImage>> getImagesByCategory(final String category) {
    final String _sql = "SELECT * FROM stoic_images WHERE category = ? ORDER BY RANDOM()";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_images"}, new Callable<List<StoicImage>>() {
      @Override
      @NonNull
      public List<StoicImage> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPhilosopher = CursorUtil.getColumnIndexOrThrow(_cursor, "philosopher");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final List<StoicImage> _result = new ArrayList<StoicImage>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicImage _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpPhilosopher;
            if (_cursor.isNull(_cursorIndexOfPhilosopher)) {
              _tmpPhilosopher = null;
            } else {
              _tmpPhilosopher = _cursor.getString(_cursorIndexOfPhilosopher);
            }
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            _item = new StoicImage(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpCategory,_tmpPhilosopher,_tmpFavorited);
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
  public Flow<List<StoicImage>> getFavoriteImages() {
    final String _sql = "SELECT * FROM stoic_images WHERE favorited = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_images"}, new Callable<List<StoicImage>>() {
      @Override
      @NonNull
      public List<StoicImage> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPhilosopher = CursorUtil.getColumnIndexOrThrow(_cursor, "philosopher");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final List<StoicImage> _result = new ArrayList<StoicImage>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicImage _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpPhilosopher;
            if (_cursor.isNull(_cursorIndexOfPhilosopher)) {
              _tmpPhilosopher = null;
            } else {
              _tmpPhilosopher = _cursor.getString(_cursorIndexOfPhilosopher);
            }
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            _item = new StoicImage(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpCategory,_tmpPhilosopher,_tmpFavorited);
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
  public Flow<List<StoicImage>> getImagesByPhilosopher(final String philosopher) {
    final String _sql = "SELECT * FROM stoic_images WHERE philosopher = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, philosopher);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_images"}, new Callable<List<StoicImage>>() {
      @Override
      @NonNull
      public List<StoicImage> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPhilosopher = CursorUtil.getColumnIndexOrThrow(_cursor, "philosopher");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final List<StoicImage> _result = new ArrayList<StoicImage>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicImage _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpPhilosopher;
            if (_cursor.isNull(_cursorIndexOfPhilosopher)) {
              _tmpPhilosopher = null;
            } else {
              _tmpPhilosopher = _cursor.getString(_cursorIndexOfPhilosopher);
            }
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            _item = new StoicImage(_tmpId,_tmpTitle,_tmpDescription,_tmpImageUrl,_tmpCategory,_tmpPhilosopher,_tmpFavorited);
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
