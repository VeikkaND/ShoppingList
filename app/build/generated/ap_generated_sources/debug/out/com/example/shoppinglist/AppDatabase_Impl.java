package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.shoppinglist.daos.ItemDao;
import com.example.shoppinglist.daos.ItemDao_Impl;
import com.example.shoppinglist.daos.ListDao;
import com.example.shoppinglist.daos.ListDao_Impl;
import com.example.shoppinglist.daos.ListItemDao;
import com.example.shoppinglist.daos.ListItemDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ItemDao _itemDao;

  private volatile ListDao _listDao;

  private volatile ListItemDao _listItemDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `List` (`listId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `list_name` TEXT, `done_progress` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Item` (`itemId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `item_name` TEXT, `unit_type` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ListItem` (`listItemId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `list_id` INTEGER NOT NULL, `item_name` TEXT, `item_amount` REAL NOT NULL, `item_unit` TEXT, `done` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c1483ca16eea7371ac795c461427bd9e')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `List`");
        _db.execSQL("DROP TABLE IF EXISTS `Item`");
        _db.execSQL("DROP TABLE IF EXISTS `ListItem`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsList = new HashMap<String, TableInfo.Column>(3);
        _columnsList.put("listId", new TableInfo.Column("listId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsList.put("list_name", new TableInfo.Column("list_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsList.put("done_progress", new TableInfo.Column("done_progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysList = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesList = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoList = new TableInfo("List", _columnsList, _foreignKeysList, _indicesList);
        final TableInfo _existingList = TableInfo.read(_db, "List");
        if (! _infoList.equals(_existingList)) {
          return new RoomOpenHelper.ValidationResult(false, "List(com.example.shoppinglist.entities.List).\n"
                  + " Expected:\n" + _infoList + "\n"
                  + " Found:\n" + _existingList);
        }
        final HashMap<String, TableInfo.Column> _columnsItem = new HashMap<String, TableInfo.Column>(3);
        _columnsItem.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("item_name", new TableInfo.Column("item_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItem.put("unit_type", new TableInfo.Column("unit_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItem = new TableInfo("Item", _columnsItem, _foreignKeysItem, _indicesItem);
        final TableInfo _existingItem = TableInfo.read(_db, "Item");
        if (! _infoItem.equals(_existingItem)) {
          return new RoomOpenHelper.ValidationResult(false, "Item(com.example.shoppinglist.entities.Item).\n"
                  + " Expected:\n" + _infoItem + "\n"
                  + " Found:\n" + _existingItem);
        }
        final HashMap<String, TableInfo.Column> _columnsListItem = new HashMap<String, TableInfo.Column>(6);
        _columnsListItem.put("listItemId", new TableInfo.Column("listItemId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListItem.put("list_id", new TableInfo.Column("list_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListItem.put("item_name", new TableInfo.Column("item_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListItem.put("item_amount", new TableInfo.Column("item_amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListItem.put("item_unit", new TableInfo.Column("item_unit", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListItem.put("done", new TableInfo.Column("done", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesListItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoListItem = new TableInfo("ListItem", _columnsListItem, _foreignKeysListItem, _indicesListItem);
        final TableInfo _existingListItem = TableInfo.read(_db, "ListItem");
        if (! _infoListItem.equals(_existingListItem)) {
          return new RoomOpenHelper.ValidationResult(false, "ListItem(com.example.shoppinglist.entities.ListItem).\n"
                  + " Expected:\n" + _infoListItem + "\n"
                  + " Found:\n" + _existingListItem);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c1483ca16eea7371ac795c461427bd9e", "57c5e082e663a5b497b9100f022b4bff");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "List","Item","ListItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `List`");
      _db.execSQL("DELETE FROM `Item`");
      _db.execSQL("DELETE FROM `ListItem`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ItemDao.class, ItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ListDao.class, ListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ListItemDao.class, ListItemDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public ItemDao itemDao() {
    if (_itemDao != null) {
      return _itemDao;
    } else {
      synchronized(this) {
        if(_itemDao == null) {
          _itemDao = new ItemDao_Impl(this);
        }
        return _itemDao;
      }
    }
  }

  @Override
  public ListDao listDao() {
    if (_listDao != null) {
      return _listDao;
    } else {
      synchronized(this) {
        if(_listDao == null) {
          _listDao = new ListDao_Impl(this);
        }
        return _listDao;
      }
    }
  }

  @Override
  public ListItemDao listItemDao() {
    if (_listItemDao != null) {
      return _listItemDao;
    } else {
      synchronized(this) {
        if(_listItemDao == null) {
          _listItemDao = new ListItemDao_Impl(this);
        }
        return _listItemDao;
      }
    }
  }
}
