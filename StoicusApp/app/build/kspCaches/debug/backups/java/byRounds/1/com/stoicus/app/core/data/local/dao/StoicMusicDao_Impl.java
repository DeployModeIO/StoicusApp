package com.stoicus.app.core.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.stoicus.app.core.data.local.entity.StoicMusicTrack;
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
public final class StoicMusicDao_Impl implements StoicMusicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StoicMusicTrack> __insertionAdapterOfStoicMusicTrack;

  private final EntityDeletionOrUpdateAdapter<StoicMusicTrack> __deletionAdapterOfStoicMusicTrack;

  private final EntityDeletionOrUpdateAdapter<StoicMusicTrack> __updateAdapterOfStoicMusicTrack;

  private final SharedSQLiteStatement __preparedStmtOfStopAllTracks;

  private final SharedSQLiteStatement __preparedStmtOfPlayTrack;

  public StoicMusicDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStoicMusicTrack = new EntityInsertionAdapter<StoicMusicTrack>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stoic_music` (`id`,`title`,`artist`,`duration`,`audioUrl`,`category`,`mood`,`favorited`,`isPlaying`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicMusicTrack entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getArtist());
        statement.bindLong(4, entity.getDuration());
        statement.bindString(5, entity.getAudioUrl());
        statement.bindString(6, entity.getCategory());
        statement.bindString(7, entity.getMood());
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isPlaying() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
      }
    };
    this.__deletionAdapterOfStoicMusicTrack = new EntityDeletionOrUpdateAdapter<StoicMusicTrack>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `stoic_music` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicMusicTrack entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfStoicMusicTrack = new EntityDeletionOrUpdateAdapter<StoicMusicTrack>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `stoic_music` SET `id` = ?,`title` = ?,`artist` = ?,`duration` = ?,`audioUrl` = ?,`category` = ?,`mood` = ?,`favorited` = ?,`isPlaying` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoicMusicTrack entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getArtist());
        statement.bindLong(4, entity.getDuration());
        statement.bindString(5, entity.getAudioUrl());
        statement.bindString(6, entity.getCategory());
        statement.bindString(7, entity.getMood());
        final int _tmp = entity.getFavorited() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isPlaying() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfStopAllTracks = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE stoic_music SET isPlaying = 0";
        return _query;
      }
    };
    this.__preparedStmtOfPlayTrack = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE stoic_music SET isPlaying = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTrack(final StoicMusicTrack track,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfStoicMusicTrack.insertAndReturnId(track);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTracks(final List<StoicMusicTrack> tracks,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStoicMusicTrack.insert(tracks);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTrack(final StoicMusicTrack track,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfStoicMusicTrack.handle(track);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTrack(final StoicMusicTrack track,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStoicMusicTrack.handle(track);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object stopAllTracks(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfStopAllTracks.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfStopAllTracks.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object playTrack(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfPlayTrack.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfPlayTrack.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StoicMusicTrack>> getAllTracks() {
    final String _sql = "SELECT * FROM stoic_music ORDER BY title";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_music"}, new Callable<List<StoicMusicTrack>>() {
      @Override
      @NonNull
      public List<StoicMusicTrack> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfIsPlaying = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaying");
          final List<StoicMusicTrack> _result = new ArrayList<StoicMusicTrack>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicMusicTrack _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpMood;
            _tmpMood = _cursor.getString(_cursorIndexOfMood);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final boolean _tmpIsPlaying;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaying);
            _tmpIsPlaying = _tmp_1 != 0;
            _item = new StoicMusicTrack(_tmpId,_tmpTitle,_tmpArtist,_tmpDuration,_tmpAudioUrl,_tmpCategory,_tmpMood,_tmpFavorited,_tmpIsPlaying);
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
  public Flow<List<StoicMusicTrack>> getTracksByCategory(final String category) {
    final String _sql = "SELECT * FROM stoic_music WHERE category = ? ORDER BY title";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_music"}, new Callable<List<StoicMusicTrack>>() {
      @Override
      @NonNull
      public List<StoicMusicTrack> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfIsPlaying = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaying");
          final List<StoicMusicTrack> _result = new ArrayList<StoicMusicTrack>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicMusicTrack _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpMood;
            _tmpMood = _cursor.getString(_cursorIndexOfMood);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final boolean _tmpIsPlaying;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaying);
            _tmpIsPlaying = _tmp_1 != 0;
            _item = new StoicMusicTrack(_tmpId,_tmpTitle,_tmpArtist,_tmpDuration,_tmpAudioUrl,_tmpCategory,_tmpMood,_tmpFavorited,_tmpIsPlaying);
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
  public Flow<List<StoicMusicTrack>> getFavoriteTracks() {
    final String _sql = "SELECT * FROM stoic_music WHERE favorited = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_music"}, new Callable<List<StoicMusicTrack>>() {
      @Override
      @NonNull
      public List<StoicMusicTrack> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfIsPlaying = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaying");
          final List<StoicMusicTrack> _result = new ArrayList<StoicMusicTrack>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicMusicTrack _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpMood;
            _tmpMood = _cursor.getString(_cursorIndexOfMood);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final boolean _tmpIsPlaying;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaying);
            _tmpIsPlaying = _tmp_1 != 0;
            _item = new StoicMusicTrack(_tmpId,_tmpTitle,_tmpArtist,_tmpDuration,_tmpAudioUrl,_tmpCategory,_tmpMood,_tmpFavorited,_tmpIsPlaying);
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
  public Flow<List<StoicMusicTrack>> getTracksByMood(final String mood) {
    final String _sql = "SELECT * FROM stoic_music WHERE mood = ? ORDER BY title";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, mood);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stoic_music"}, new Callable<List<StoicMusicTrack>>() {
      @Override
      @NonNull
      public List<StoicMusicTrack> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfIsPlaying = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaying");
          final List<StoicMusicTrack> _result = new ArrayList<StoicMusicTrack>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoicMusicTrack _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpArtist;
            _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpMood;
            _tmpMood = _cursor.getString(_cursorIndexOfMood);
            final boolean _tmpFavorited;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFavorited);
            _tmpFavorited = _tmp != 0;
            final boolean _tmpIsPlaying;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaying);
            _tmpIsPlaying = _tmp_1 != 0;
            _item = new StoicMusicTrack(_tmpId,_tmpTitle,_tmpArtist,_tmpDuration,_tmpAudioUrl,_tmpCategory,_tmpMood,_tmpFavorited,_tmpIsPlaying);
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
