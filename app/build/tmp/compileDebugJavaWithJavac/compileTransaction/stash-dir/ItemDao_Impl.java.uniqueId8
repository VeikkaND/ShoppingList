package com.example.shoppinglist.daos;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.shoppinglist.entities.Item;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemDao_Impl implements ItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Item> __insertionAdapterOfItem;

  private final EntityDeletionOrUpdateAdapter<Item> __deletionAdapterOfItem;

  public ItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItem = new EntityInsertionAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Item` (`itemId`,`item_name`,`unit_type`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.itemId);
        if (value.itemName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.itemName);
        }
        if (value.unitType == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.unitType);
        }
      }
    };
    this.__deletionAdapterOfItem = new EntityDeletionOrUpdateAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Item` WHERE `itemId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.itemId);
      }
    };
  }

  @Override
  public void insertAll(final Item... items) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItem.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Item item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Item[] getAll() {
    final String _sql = "SELECT * FROM item";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "item_name");
      final int _cursorIndexOfUnitType = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_type");
      final Item[] _result = new Item[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Item _item;
        _item = new Item();
        _item.itemId = _cursor.getInt(_cursorIndexOfItemId);
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _item.itemName = null;
        } else {
          _item.itemName = _cursor.getString(_cursorIndexOfItemName);
        }
        if (_cursor.isNull(_cursorIndexOfUnitType)) {
          _item.unitType = null;
        } else {
          _item.unitType = _cursor.getString(_cursorIndexOfUnitType);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String[] getAllNames() {
    final String _sql = "SELECT item_name FROM item";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String[] _result = new String[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getUnit(final String name) {
    final String _sql = "SELECT unit_type FROM item WHERE item_name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Item findById(final int id) {
    final String _sql = "SELECT * FROM item WHERE itemId LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "item_name");
      final int _cursorIndexOfUnitType = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_type");
      final Item _result;
      if(_cursor.moveToFirst()) {
        _result = new Item();
        _result.itemId = _cursor.getInt(_cursorIndexOfItemId);
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _result.itemName = null;
        } else {
          _result.itemName = _cursor.getString(_cursorIndexOfItemName);
        }
        if (_cursor.isNull(_cursorIndexOfUnitType)) {
          _result.unitType = null;
        } else {
          _result.unitType = _cursor.getString(_cursorIndexOfUnitType);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Item findByName(final String name) {
    final String _sql = "SELECT * FROM item WHERE item_name LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
      final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "item_name");
      final int _cursorIndexOfUnitType = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_type");
      final Item _result;
      if(_cursor.moveToFirst()) {
        _result = new Item();
        _result.itemId = _cursor.getInt(_cursorIndexOfItemId);
        if (_cursor.isNull(_cursorIndexOfItemName)) {
          _result.itemName = null;
        } else {
          _result.itemName = _cursor.getString(_cursorIndexOfItemName);
        }
        if (_cursor.isNull(_cursorIndexOfUnitType)) {
          _result.unitType = null;
        } else {
          _result.unitType = _cursor.getString(_cursorIndexOfUnitType);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
