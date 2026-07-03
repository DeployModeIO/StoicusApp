package com.stoicus.app.core.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.stoicus.app.core.data.local.dao.DailyGoalDao;
import com.stoicus.app.core.data.local.dao.DailyGoalDao_Impl;
import com.stoicus.app.core.data.local.dao.MicroActionDao;
import com.stoicus.app.core.data.local.dao.MicroActionDao_Impl;
import com.stoicus.app.core.data.local.dao.MoodEntryDao;
import com.stoicus.app.core.data.local.dao.MoodEntryDao_Impl;
import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao;
import com.stoicus.app.core.data.local.dao.PhilosophyQuoteDao_Impl;
import com.stoicus.app.core.data.local.dao.RitualSessionDao;
import com.stoicus.app.core.data.local.dao.RitualSessionDao_Impl;
import com.stoicus.app.core.data.local.dao.StoicImageDao;
import com.stoicus.app.core.data.local.dao.StoicImageDao_Impl;
import com.stoicus.app.core.data.local.dao.StoicMusicDao;
import com.stoicus.app.core.data.local.dao.StoicMusicDao_Impl;
import com.stoicus.app.core.data.local.dao.StreakRecordDao;
import com.stoicus.app.core.data.local.dao.StreakRecordDao_Impl;
import com.stoicus.app.core.data.local.dao.UserProfileDao;
import com.stoicus.app.core.data.local.dao.UserProfileDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StoicusDatabase_Impl extends StoicusDatabase {
  private volatile UserProfileDao _userProfileDao;

  private volatile RitualSessionDao _ritualSessionDao;

  private volatile MicroActionDao _microActionDao;

  private volatile MoodEntryDao _moodEntryDao;

  private volatile PhilosophyQuoteDao _philosophyQuoteDao;

  private volatile StreakRecordDao _streakRecordDao;

  private volatile DailyGoalDao _dailyGoalDao;

  private volatile StoicImageDao _stoicImageDao;

  private volatile StoicMusicDao _stoicMusicDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_profiles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `selectedPillars` TEXT NOT NULL, `biggestChallenge` TEXT NOT NULL, `preferredTime` TEXT NOT NULL, `onboardingCompleted` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ritual_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pillar` TEXT NOT NULL, `exerciseType` TEXT NOT NULL, `durationMinutes` INTEGER NOT NULL, `completedAt` INTEGER NOT NULL, `completed` INTEGER NOT NULL, `reflection` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `micro_actions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `actionType` TEXT NOT NULL, `description` TEXT NOT NULL, `completed` INTEGER NOT NULL, `completedAt` INTEGER, `date` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `mood_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `moodScore` INTEGER NOT NULL, `stressLevel` INTEGER NOT NULL, `energyLevel` INTEGER NOT NULL, `reflection` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `date` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `philosophy_quotes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `author` TEXT NOT NULL, `text` TEXT NOT NULL, `source` TEXT NOT NULL, `pillar` TEXT NOT NULL, `favorited` INTEGER NOT NULL, `imageUrl` TEXT, `era` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `streak_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `morningRitualCompleted` INTEGER NOT NULL, `eveningRitualCompleted` INTEGER NOT NULL, `microActionsCompleted` INTEGER NOT NULL, `moodScore` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `daily_goals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `pillar` TEXT NOT NULL, `goalDescription` TEXT NOT NULL, `completed` INTEGER NOT NULL, `completedAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stoic_images` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `category` TEXT NOT NULL, `philosopher` TEXT, `favorited` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stoic_music` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `artist` TEXT NOT NULL, `duration` INTEGER NOT NULL, `audioUrl` TEXT NOT NULL, `category` TEXT NOT NULL, `mood` TEXT NOT NULL, `favorited` INTEGER NOT NULL, `isPlaying` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a0db077df6946b2f7d194b7157614091')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `user_profiles`");
        db.execSQL("DROP TABLE IF EXISTS `ritual_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `micro_actions`");
        db.execSQL("DROP TABLE IF EXISTS `mood_entries`");
        db.execSQL("DROP TABLE IF EXISTS `philosophy_quotes`");
        db.execSQL("DROP TABLE IF EXISTS `streak_records`");
        db.execSQL("DROP TABLE IF EXISTS `daily_goals`");
        db.execSQL("DROP TABLE IF EXISTS `stoic_images`");
        db.execSQL("DROP TABLE IF EXISTS `stoic_music`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUserProfiles = new HashMap<String, TableInfo.Column>(7);
        _columnsUserProfiles.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("selectedPillars", new TableInfo.Column("selectedPillars", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("biggestChallenge", new TableInfo.Column("biggestChallenge", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("preferredTime", new TableInfo.Column("preferredTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("onboardingCompleted", new TableInfo.Column("onboardingCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserProfiles.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserProfiles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserProfiles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserProfiles = new TableInfo("user_profiles", _columnsUserProfiles, _foreignKeysUserProfiles, _indicesUserProfiles);
        final TableInfo _existingUserProfiles = TableInfo.read(db, "user_profiles");
        if (!_infoUserProfiles.equals(_existingUserProfiles)) {
          return new RoomOpenHelper.ValidationResult(false, "user_profiles(com.stoicus.app.core.data.local.entity.UserProfile).\n"
                  + " Expected:\n" + _infoUserProfiles + "\n"
                  + " Found:\n" + _existingUserProfiles);
        }
        final HashMap<String, TableInfo.Column> _columnsRitualSessions = new HashMap<String, TableInfo.Column>(7);
        _columnsRitualSessions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("pillar", new TableInfo.Column("pillar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("exerciseType", new TableInfo.Column("exerciseType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRitualSessions.put("reflection", new TableInfo.Column("reflection", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRitualSessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRitualSessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRitualSessions = new TableInfo("ritual_sessions", _columnsRitualSessions, _foreignKeysRitualSessions, _indicesRitualSessions);
        final TableInfo _existingRitualSessions = TableInfo.read(db, "ritual_sessions");
        if (!_infoRitualSessions.equals(_existingRitualSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "ritual_sessions(com.stoicus.app.core.data.local.entity.RitualSession).\n"
                  + " Expected:\n" + _infoRitualSessions + "\n"
                  + " Found:\n" + _existingRitualSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsMicroActions = new HashMap<String, TableInfo.Column>(6);
        _columnsMicroActions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMicroActions.put("actionType", new TableInfo.Column("actionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMicroActions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMicroActions.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMicroActions.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMicroActions.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMicroActions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMicroActions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMicroActions = new TableInfo("micro_actions", _columnsMicroActions, _foreignKeysMicroActions, _indicesMicroActions);
        final TableInfo _existingMicroActions = TableInfo.read(db, "micro_actions");
        if (!_infoMicroActions.equals(_existingMicroActions)) {
          return new RoomOpenHelper.ValidationResult(false, "micro_actions(com.stoicus.app.core.data.local.entity.MicroAction).\n"
                  + " Expected:\n" + _infoMicroActions + "\n"
                  + " Found:\n" + _existingMicroActions);
        }
        final HashMap<String, TableInfo.Column> _columnsMoodEntries = new HashMap<String, TableInfo.Column>(7);
        _columnsMoodEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("moodScore", new TableInfo.Column("moodScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("stressLevel", new TableInfo.Column("stressLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("energyLevel", new TableInfo.Column("energyLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("reflection", new TableInfo.Column("reflection", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoodEntries.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMoodEntries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMoodEntries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMoodEntries = new TableInfo("mood_entries", _columnsMoodEntries, _foreignKeysMoodEntries, _indicesMoodEntries);
        final TableInfo _existingMoodEntries = TableInfo.read(db, "mood_entries");
        if (!_infoMoodEntries.equals(_existingMoodEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "mood_entries(com.stoicus.app.core.data.local.entity.MoodEntry).\n"
                  + " Expected:\n" + _infoMoodEntries + "\n"
                  + " Found:\n" + _existingMoodEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsPhilosophyQuotes = new HashMap<String, TableInfo.Column>(8);
        _columnsPhilosophyQuotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("author", new TableInfo.Column("author", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("pillar", new TableInfo.Column("pillar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("favorited", new TableInfo.Column("favorited", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhilosophyQuotes.put("era", new TableInfo.Column("era", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhilosophyQuotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPhilosophyQuotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPhilosophyQuotes = new TableInfo("philosophy_quotes", _columnsPhilosophyQuotes, _foreignKeysPhilosophyQuotes, _indicesPhilosophyQuotes);
        final TableInfo _existingPhilosophyQuotes = TableInfo.read(db, "philosophy_quotes");
        if (!_infoPhilosophyQuotes.equals(_existingPhilosophyQuotes)) {
          return new RoomOpenHelper.ValidationResult(false, "philosophy_quotes(com.stoicus.app.core.data.local.entity.PhilosophyQuote).\n"
                  + " Expected:\n" + _infoPhilosophyQuotes + "\n"
                  + " Found:\n" + _existingPhilosophyQuotes);
        }
        final HashMap<String, TableInfo.Column> _columnsStreakRecords = new HashMap<String, TableInfo.Column>(6);
        _columnsStreakRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreakRecords.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreakRecords.put("morningRitualCompleted", new TableInfo.Column("morningRitualCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreakRecords.put("eveningRitualCompleted", new TableInfo.Column("eveningRitualCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreakRecords.put("microActionsCompleted", new TableInfo.Column("microActionsCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreakRecords.put("moodScore", new TableInfo.Column("moodScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreakRecords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStreakRecords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStreakRecords = new TableInfo("streak_records", _columnsStreakRecords, _foreignKeysStreakRecords, _indicesStreakRecords);
        final TableInfo _existingStreakRecords = TableInfo.read(db, "streak_records");
        if (!_infoStreakRecords.equals(_existingStreakRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "streak_records(com.stoicus.app.core.data.local.entity.StreakRecord).\n"
                  + " Expected:\n" + _infoStreakRecords + "\n"
                  + " Found:\n" + _existingStreakRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsDailyGoals = new HashMap<String, TableInfo.Column>(6);
        _columnsDailyGoals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyGoals.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyGoals.put("pillar", new TableInfo.Column("pillar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyGoals.put("goalDescription", new TableInfo.Column("goalDescription", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyGoals.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailyGoals.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailyGoals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailyGoals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDailyGoals = new TableInfo("daily_goals", _columnsDailyGoals, _foreignKeysDailyGoals, _indicesDailyGoals);
        final TableInfo _existingDailyGoals = TableInfo.read(db, "daily_goals");
        if (!_infoDailyGoals.equals(_existingDailyGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "daily_goals(com.stoicus.app.core.data.local.entity.DailyGoal).\n"
                  + " Expected:\n" + _infoDailyGoals + "\n"
                  + " Found:\n" + _existingDailyGoals);
        }
        final HashMap<String, TableInfo.Column> _columnsStoicImages = new HashMap<String, TableInfo.Column>(7);
        _columnsStoicImages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("philosopher", new TableInfo.Column("philosopher", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicImages.put("favorited", new TableInfo.Column("favorited", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStoicImages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStoicImages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStoicImages = new TableInfo("stoic_images", _columnsStoicImages, _foreignKeysStoicImages, _indicesStoicImages);
        final TableInfo _existingStoicImages = TableInfo.read(db, "stoic_images");
        if (!_infoStoicImages.equals(_existingStoicImages)) {
          return new RoomOpenHelper.ValidationResult(false, "stoic_images(com.stoicus.app.core.data.local.entity.StoicImage).\n"
                  + " Expected:\n" + _infoStoicImages + "\n"
                  + " Found:\n" + _existingStoicImages);
        }
        final HashMap<String, TableInfo.Column> _columnsStoicMusic = new HashMap<String, TableInfo.Column>(9);
        _columnsStoicMusic.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("audioUrl", new TableInfo.Column("audioUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("mood", new TableInfo.Column("mood", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("favorited", new TableInfo.Column("favorited", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoicMusic.put("isPlaying", new TableInfo.Column("isPlaying", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStoicMusic = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStoicMusic = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStoicMusic = new TableInfo("stoic_music", _columnsStoicMusic, _foreignKeysStoicMusic, _indicesStoicMusic);
        final TableInfo _existingStoicMusic = TableInfo.read(db, "stoic_music");
        if (!_infoStoicMusic.equals(_existingStoicMusic)) {
          return new RoomOpenHelper.ValidationResult(false, "stoic_music(com.stoicus.app.core.data.local.entity.StoicMusicTrack).\n"
                  + " Expected:\n" + _infoStoicMusic + "\n"
                  + " Found:\n" + _existingStoicMusic);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a0db077df6946b2f7d194b7157614091", "966d0d6da18c0ff427f9c3bf6a43d798");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user_profiles","ritual_sessions","micro_actions","mood_entries","philosophy_quotes","streak_records","daily_goals","stoic_images","stoic_music");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `user_profiles`");
      _db.execSQL("DELETE FROM `ritual_sessions`");
      _db.execSQL("DELETE FROM `micro_actions`");
      _db.execSQL("DELETE FROM `mood_entries`");
      _db.execSQL("DELETE FROM `philosophy_quotes`");
      _db.execSQL("DELETE FROM `streak_records`");
      _db.execSQL("DELETE FROM `daily_goals`");
      _db.execSQL("DELETE FROM `stoic_images`");
      _db.execSQL("DELETE FROM `stoic_music`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RitualSessionDao.class, RitualSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MicroActionDao.class, MicroActionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MoodEntryDao.class, MoodEntryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PhilosophyQuoteDao.class, PhilosophyQuoteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StreakRecordDao.class, StreakRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DailyGoalDao.class, DailyGoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StoicImageDao.class, StoicImageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StoicMusicDao.class, StoicMusicDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public RitualSessionDao ritualSessionDao() {
    if (_ritualSessionDao != null) {
      return _ritualSessionDao;
    } else {
      synchronized(this) {
        if(_ritualSessionDao == null) {
          _ritualSessionDao = new RitualSessionDao_Impl(this);
        }
        return _ritualSessionDao;
      }
    }
  }

  @Override
  public MicroActionDao microActionDao() {
    if (_microActionDao != null) {
      return _microActionDao;
    } else {
      synchronized(this) {
        if(_microActionDao == null) {
          _microActionDao = new MicroActionDao_Impl(this);
        }
        return _microActionDao;
      }
    }
  }

  @Override
  public MoodEntryDao moodEntryDao() {
    if (_moodEntryDao != null) {
      return _moodEntryDao;
    } else {
      synchronized(this) {
        if(_moodEntryDao == null) {
          _moodEntryDao = new MoodEntryDao_Impl(this);
        }
        return _moodEntryDao;
      }
    }
  }

  @Override
  public PhilosophyQuoteDao philosophyQuoteDao() {
    if (_philosophyQuoteDao != null) {
      return _philosophyQuoteDao;
    } else {
      synchronized(this) {
        if(_philosophyQuoteDao == null) {
          _philosophyQuoteDao = new PhilosophyQuoteDao_Impl(this);
        }
        return _philosophyQuoteDao;
      }
    }
  }

  @Override
  public StreakRecordDao streakRecordDao() {
    if (_streakRecordDao != null) {
      return _streakRecordDao;
    } else {
      synchronized(this) {
        if(_streakRecordDao == null) {
          _streakRecordDao = new StreakRecordDao_Impl(this);
        }
        return _streakRecordDao;
      }
    }
  }

  @Override
  public DailyGoalDao dailyGoalDao() {
    if (_dailyGoalDao != null) {
      return _dailyGoalDao;
    } else {
      synchronized(this) {
        if(_dailyGoalDao == null) {
          _dailyGoalDao = new DailyGoalDao_Impl(this);
        }
        return _dailyGoalDao;
      }
    }
  }

  @Override
  public StoicImageDao stoicImageDao() {
    if (_stoicImageDao != null) {
      return _stoicImageDao;
    } else {
      synchronized(this) {
        if(_stoicImageDao == null) {
          _stoicImageDao = new StoicImageDao_Impl(this);
        }
        return _stoicImageDao;
      }
    }
  }

  @Override
  public StoicMusicDao stoicMusicDao() {
    if (_stoicMusicDao != null) {
      return _stoicMusicDao;
    } else {
      synchronized(this) {
        if(_stoicMusicDao == null) {
          _stoicMusicDao = new StoicMusicDao_Impl(this);
        }
        return _stoicMusicDao;
      }
    }
  }
}
