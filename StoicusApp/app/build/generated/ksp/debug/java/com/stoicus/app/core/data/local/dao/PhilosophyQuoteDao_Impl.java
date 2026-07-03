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
import com.stoicus.app.core.data.local.entity.PhilosophyQuote;
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
public final class PhilosophyQuoteDao_Impl implements PhilosophyQuoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PhilosophyQuote> __insertionAdapterOfPhilosophyQuote;

  private final EntityDeletionOrUpdateAdapter<PhilosophyQuote> __updateAdapterOfPhilosophyQuote;

  public PhilosophyQuoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhilosophyQuote = new EntityInsertionAdapter<PhilosophyQuote>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `philosophy_quotes` (`id`,`author`,`text`,`source`,`pillar`,`favorited`,`imageUrl`,`era`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PhilosophyQuote entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getAuthor());
        statement.bindString(3, entity.getText());
        statement.bindString(4, entity.getSource());
        statement.bindString(5, entity.getPillar());
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        statement.bindString(8, entity.getEra());
      }
    };
    this.__updateAdapterOfPhilosophyQuote = new EntityDeletionOrUpdateAdapter<PhilosophyQuote>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `philosophy_quotes` SET `id` = ?,`author` = ?,`text` = ?,`source` = ?,`pillar` = ?,`favorited` = ?,`imageUrl` = ?,`era` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PhilosophyQuote entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getAuthor());
        statement.bindString(3, entity.getText());
        statement.bindString(4, entity.getSource());
        statement.bindString(5, entity.getPillar());
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(6, _tmp);
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        statement.bindString(8, entity.getEra());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertQuote(final PhilosophyQuote quote,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPhilosophyQuote.insertAndReturnId(quote);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateQuote(final PhilosophyQuote quote,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPhilosophyQuote.handle(quote);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<PhilosophyQuote> getRandomQuote() {
    final String _sql = "SELECT * FROM philosophy_quotes ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"philosophy_quotes"}, new Callable<PhilosophyQuote>() {
      @Override
      @Nullable
      public PhilosophyQuote call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfEra = CursorUtil.getColumnIndexOrThrow(_cursor, "era");
          final PhilosophyQuote _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpEra;
            _tmpEra = _cursor.getString(_cursorIndexOfEra);
            _result = new PhilosophyQuote(_tmpId,_tmpAuthor,_tmpText,_tmpSource,_tmpPillar,_tmpFavorited,_tmpImageUrl,_tmpEra);
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
  public Flow<PhilosophyQuote> getQuoteByPillar(final String pillar) {
    final String _sql = "SELECT * FROM philosophy_quotes WHERE pillar = ? ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pillar);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"philosophy_quotes"}, new Callable<PhilosophyQuote>() {
      @Override
      @Nullable
      public PhilosophyQuote call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfEra = CursorUtil.getColumnIndexOrThrow(_cursor, "era");
          final PhilosophyQuote _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpEra;
            _tmpEra = _cursor.getString(_cursorIndexOfEra);
            _result = new PhilosophyQuote(_tmpId,_tmpAuthor,_tmpText,_tmpSource,_tmpPillar,_tmpFavorited,_tmpImageUrl,_tmpEra);
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
  public Flow<List<PhilosophyQuote>> getFavoriteQuotes() {
    final String _sql = "SELECT * FROM philosophy_quotes WHERE favorited = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"philosophy_quotes"}, new Callable<List<PhilosophyQuote>>() {
      @Override
      @NonNull
      public List<PhilosophyQuote> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfPillar = CursorUtil.getColumnIndexOrThrow(_cursor, "pillar");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfEra = CursorUtil.getColumnIndexOrThrow(_cursor, "era");
          final List<PhilosophyQuote> _result = new ArrayList<PhilosophyQuote>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PhilosophyQuote _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final String _tmpText;
            _tmpText = _cursor.getString(_cursorIndexOfText);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpPillar;
            _tmpPillar = _cursor.getString(_cursorIndexOfPillar);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpEra;
            _tmpEra = _cursor.getString(_cursorIndexOfEra);
            _item = new PhilosophyQuote(_tmpId,_tmpAuthor,_tmpText,_tmpSource,_tmpPillar,_tmpFavorited,_tmpImageUrl,_tmpEra);
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
